package com.github.ipcam.pool;

import com.github.ipcam.ICameraConnection;
import com.github.ipcam.ICameraConnectionFactory;
import com.github.ipcam.ICameraSupplier;
import com.github.ipcam.connection.DefaultCameraConnectionFactory;
import com.github.ipcam.entity.NetworkCamera;
import com.github.ipcam.exception.CameraConnectionException;
import org.apache.commons.pool2.BaseKeyedPooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectState;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * CameraConnectionPoolFactory
 *
 * @author echils
 */
public class CameraConnectionPoolFactory<T> extends BaseKeyedPooledObjectFactory<T, ICameraConnection> {


    private static final Logger logger = LoggerFactory.getLogger(CameraConnectionPoolFactory.class);

    private final Map<T, NetworkCamera> networkCameraPool = new ConcurrentHashMap<>();

    private ICameraSupplier<T> cameraSupplier;

    private ICameraConnectionFactory cameraConnectionFactory;

    private CameraConnectionPoolProperties connectionPoolProperties;

    public CameraConnectionPoolFactory(ICameraSupplier<T> supplier,
                                       CameraConnectionPoolProperties connectionPoolProperties) {
        if (supplier == null) {
            throw new NullPointerException(String.format("%s can't be null", ICameraSupplier.class.getSimpleName()));
        }
        this.cameraSupplier = supplier;
        this.connectionPoolProperties = connectionPoolProperties;
        this.cameraConnectionFactory = new DefaultCameraConnectionFactory();
    }

    public ICameraSupplier<T> getCameraSupplier() {
        return cameraSupplier;
    }

    /**
     * Get a camera connection from pool
     *
     * @param key camera identification
     * @return camera connection
     */
    @Override
    public ICameraConnection create(T key) throws Exception {
        logger.info("Start to create connection of network camera with key:{}", key);
        NetworkCamera networkCamera = networkCameraPool.computeIfAbsent(key, k -> cameraSupplier.apply(k));
        if (networkCamera == null || networkCamera.getDriverType() == null) {
            logger.error("network camera is null where key:{}", key);
            throw new CameraConnectionException("network camera is null");
        }
        ICameraConnection cameraConnection
                = cameraConnectionFactory.create(networkCamera);
        logger.info("Create connection of network camera success");
        return cameraConnection;
    }


    /**
     * Wrap the connection instance of network camera
     *
     * @param cameraConnection the connection if camera
     * @return camera connection
     */
    @Override
    public PooledObject<ICameraConnection> wrap(ICameraConnection cameraConnection) {
        return new DefaultPooledObject<>(cameraConnection);
    }


    /**
     * Destroy the connection instance of network camera
     *
     * @param key          the key used when selecting the object
     * @param pooledObject a {@code PooledObject} wrapping the instance to be validated
     */
    @Override
    public void destroyObject(T key, PooledObject<ICameraConnection> pooledObject) throws Exception {
        super.destroyObject(key, pooledObject);
        networkCameraPool.remove(key);
        cameraConnectionFactory.destroy(pooledObject.getObject());
    }


    /**
     * Ensures that the instance is safe to be returned by the pool.
     * <p>
     * The default implementation always returns {@code true}.
     *
     * @param key          the key used when selecting the object
     * @param pooledObject a {@code PooledObject} wrapping the instance to be validated
     * @return always <code>true</code> in the default implementation
     */
    @Override
    public boolean validateObject(T key, PooledObject<ICameraConnection> pooledObject) {
        if (!pooledObject.getObject().isRobust()) return false;
        if (pooledObject.getState() == PooledObjectState.EVICTION) {
            logger.info("Network camera connection pool factory start to validate of the key:{}", key);
            long now = System.currentTimeMillis();
            long maxEvictableIdleTimeMillis = connectionPoolProperties.getMaxEvictableIdleTimeMillis();
            if ((now - pooledObject.getLastBorrowTime()) > maxEvictableIdleTimeMillis &&
                    (now - pooledObject.getLastReturnTime()) > maxEvictableIdleTimeMillis) {
                logger.info("The camera：{} connection has not been used for a long time." +
                        " Now it is time to release the resource", key);
                return false;
            }
        }
        return true;

    }

}
