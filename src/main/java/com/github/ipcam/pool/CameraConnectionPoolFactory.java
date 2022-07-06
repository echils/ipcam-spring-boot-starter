package com.github.ipcam.pool;

import com.github.ipcam.DefaultCameraConnectionFactory;
import com.github.ipcam.ICameraConnection;
import com.github.ipcam.ICameraConnectionFactory;
import com.github.ipcam.ICameraSupplier;
import com.github.ipcam.entity.NetworkCamera;
import com.github.ipcam.entity.exception.CameraConnectionException;
import org.apache.commons.pool2.BaseKeyedPooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectState;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * CameraConnectionPoolFactory
 *
 * @author echils
 */
public class CameraConnectionPoolFactory extends BaseKeyedPooledObjectFactory<String, ICameraConnection> {


    private static final Logger logger = LoggerFactory.getLogger(CameraConnectionPoolFactory.class);

    private static final Map<String, NetworkCamera> networkCameraPool = new ConcurrentHashMap<>();

    private ICameraSupplier cameraSupplier;

    private ICameraConnectionFactory cameraConnectionFactory;

    @Autowired
    private CameraConnectionPoolProperties connectionPoolProperties;

    public CameraConnectionPoolFactory(ICameraSupplier supplier) {
        if (supplier == null) {
            throw new NullPointerException(String.format("%s can't be null", ICameraSupplier.class.getSimpleName()));
        }
        this.cameraSupplier = supplier;
        this.cameraConnectionFactory = new DefaultCameraConnectionFactory();
    }

    public ICameraSupplier getCameraSupplier() {
        return cameraSupplier;
    }

    /**
     * Get a camera connection from pool
     *
     * @param key camera identification
     * @return camera connection
     * @throws Exception
     */
    @Override
    public ICameraConnection create(String key) throws Exception {
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
     * @throws Exception
     */
    @Override
    public void destroyObject(String key, PooledObject<ICameraConnection> pooledObject) throws Exception {
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
    public boolean validateObject(String key, PooledObject<ICameraConnection> pooledObject) {
        if (!pooledObject.getObject().isRobust()) {
            return false;
        }
        if (pooledObject.getState() == PooledObjectState.EVICTION) {
            logger.info("Network camera connection pool factory start to validate of the key:{}", key);
            long now = new Date().getTime();
            if (now - pooledObject.getLastBorrowTime() > connectionPoolProperties.getMaxIdleMillis()) {
                if (now - pooledObject.getLastReturnTime() > connectionPoolProperties.getMaxIdleMillis()) {
                    logger.info("The camera：{} connection has not been used for a long time." +
                            " Now it is time to release the resource", key);
                    return false;
                }
            }
        }
        return true;
    }

}
