package com.github.ipcam.pool;

import com.github.ipcam.ICameraConnection;
import com.github.ipcam.exception.CameraConnectionException;
import org.apache.commons.pool2.KeyedPooledObjectFactory;
import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.apache.commons.pool2.impl.GenericKeyedObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CameraConnectionPool
 *
 * @author echils
 */
public class CameraConnectionPool<T> extends GenericKeyedObjectPool<T, ICameraConnection> {


    private static final Logger logger = LoggerFactory.getLogger(CameraConnectionPool.class);


    /**
     * Create a new <code>GenericKeyedObjectPool</code> using defaults from
     * {@link GenericKeyedObjectPoolConfig}.
     *
     * @param factory the factory to be used to create entries
     */
    public CameraConnectionPool(KeyedPooledObjectFactory<T, ICameraConnection> factory) {
        super(factory);
    }


    /**
     * Create a new <code>GenericKeyedObjectPool</code> using a specific
     * configuration.
     *
     * @param factory the factory to be used to create entries
     * @param config  The configuration to use for this pool instance. The
     *                configuration is used by value. Subsequent changes to
     *                the configuration object will not be reflected in the
     *                pool.
     */
    public CameraConnectionPool(CameraConnectionPoolFactory<T> factory, GenericKeyedObjectPoolConfig config) {
        super(factory, config);
        logger.info("factory finder:{}", factory.getCameraSupplier().getClass());
        logger.info("pool config:{}", config);
    }


    /**
     * Get connection of network connection
     * {@link #getMaxWaitMillis()})</code>.
     * <p>
     * {@inheritDoc}
     *
     * @param key
     */
    @Override
    public ICameraConnection borrowObject(T key) {
        try {
            return super.borrowObject(key);
        } catch (Exception e) {
            throw new CameraConnectionException("borrow connection failed.", e);
        }
    }


    /**
     * Borrows an object from the sub-pool associated with the given key using
     * the specified waiting time which only applies if
     * {@link #getBlockWhenExhausted()} is true.
     * <p>
     * If there is one or more idle instances available in the sub-pool
     * associated with the given key, then an idle instance will be selected
     * based on the value of {@link #getLifo()}, activated and returned.  If
     * activation fails, or {@link #getTestOnBorrow() testOnBorrow} is set to
     * <code>true</code> and validation fails, the instance is destroyed and the
     * next available instance is examined.  This continues until either a valid
     * instance is returned or there are no more idle instances available.
     * <p>
     * If there are no idle instances available in the sub-pool associated with
     * the given key, behavior depends on the {@link #getMaxTotalPerKey()
     * maxTotalPerKey}, {@link #getMaxTotal() maxTotal}, and (if applicable)
     * {@link #getBlockWhenExhausted()} and the value passed in to the
     * <code>borrowMaxWaitMillis</code> parameter. If the number of instances checked
     * out from the sub-pool under the given key is less than
     * <code>maxTotalPerKey</code> and the total number of instances in
     * circulation (under all keys) is less than <code>maxTotal</code>, a new
     * instance is created, activated and (if applicable) validated and returned
     * to the caller. If validation fails, a <code>NoSuchElementException</code>
     * will be thrown.
     * <p>
     * If the associated sub-pool is exhausted (no available idle instances and
     * no capacity to create new ones), this method will either block
     * ({@link #getBlockWhenExhausted()} is true) or throw a
     * <code>NoSuchElementException</code>
     * ({@link #getBlockWhenExhausted()} is false).
     * The length of time that this method will block when
     * {@link #getBlockWhenExhausted()} is true is determined by the value
     * passed in to the <code>borrowMaxWait</code> parameter.
     * <p>
     * When <code>maxTotal</code> is set to a positive value and this method is
     * invoked when at the limit with no idle instances available under the requested
     * key, an attempt is made to create room by clearing the oldest 15% of the
     * elements from the keyed sub-pools.
     * <p>
     * When the pool is exhausted, multiple calling threads may be
     * simultaneously blocked waiting for instances to become available. A
     * "fairness" algorithm has been implemented to ensure that threads receive
     * available instances in request arrival order.
     *
     * @param key                 pool key
     * @param borrowMaxWaitMillis The time to wait in milliseconds for an object
     *                            to become available
     * @return object instance from the keyed pool
     */
    @Override
    public ICameraConnection borrowObject(T key, long borrowMaxWaitMillis) {
        try {
            return super.borrowObject(key, borrowMaxWaitMillis);
        } catch (Exception e) {
            throw new CameraConnectionException("borrow connection failed.", e);
        }
    }


    /**
     * Returns an object to a keyed sub-pool.
     * <p>
     * If {@link #getMaxIdlePerKey() maxIdle} is set to a positive value and the
     * number of idle instances under the given key has reached this value, the
     * returning instance is destroyed.
     * <p>
     * If {@link #getTestOnReturn() testOnReturn} == true, the returning
     * instance is validated before being returned to the idle instance sub-pool
     * under the given key. In this case, if validation fails, the instance is
     * destroyed.
     *
     * @param key              pool key
     * @param cameraConnection instance to return to the keyed pool
     * @throws IllegalStateException if an object is returned to the pool that
     *                               was not borrowed from it or if an object is
     *                               returned to the pool multiple times
     */
    @Override
    public void returnObject(T key, ICameraConnection cameraConnection) {
        if (cameraConnection != null) {
            super.returnObject(key, cameraConnection);
        }
    }

}
