package com.github.ipcam.pool;

import lombok.Data;
import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * CameraConnectionPoolProperties
 *
 * @author echils
 * @since 2020-04-01 15:03
 */
@Data
@ConfigurationProperties("network.camera.pool")
public class CameraConnectionPoolProperties {

    /**
     * The default value for the {@code lifo} configuration attribute.
     *
     * @see GenericObjectPool#getLifo()
     * @see GenericKeyedObjectPool#getLifo()
     */
    private boolean lifo = true;

    /**
     * The default value for the {@code fairness} configuration attribute.
     *
     * @see GenericObjectPool#getFairness()
     * @see GenericKeyedObjectPool#getFairness()
     */
    private boolean fairness = false;

    /**
     * The default value for the {@code maxWait} configuration attribute.
     *
     * @see GenericObjectPool#getMaxWaitMillis()
     * @see GenericKeyedObjectPool#getMaxWaitMillis()
     */
    private long maxWaitMillis = -1L;

    /**
     * The default value for the {@code minEvictableIdleTimeMillis}
     * configuration attribute.
     *
     * @see GenericObjectPool#getMinEvictableIdleTimeMillis()
     * @see GenericKeyedObjectPool#getMinEvictableIdleTimeMillis()
     */
    private long minEvictableIdleTimeMillis =
            1000L * 60L * 25L;

    /**
     * The default value for the {@code softMinEvictableIdleTimeMillis}
     * configuration attribute.
     *
     * @see GenericObjectPool#getSoftMinEvictableIdleTimeMillis()
     * @see GenericKeyedObjectPool#getSoftMinEvictableIdleTimeMillis()
     */
    private long softMinEvictableIdleTimeMillis = -1;

    /**
     * The default value for {@code evictorShutdownTimeoutMillis} configuration
     * attribute.
     *
     * @see GenericObjectPool#getEvictorShutdownTimeoutMillis()
     * @see GenericKeyedObjectPool#getEvictorShutdownTimeoutMillis()
     */
    private long evictorShutdownTimeoutMillis =
            10L * 1000L;

    /**
     * The default value for the {@code numTestsPerEvictionRun} configuration
     * attribute.
     *
     * @see GenericObjectPool#getNumTestsPerEvictionRun()
     * @see GenericKeyedObjectPool#getNumTestsPerEvictionRun()
     */
    private int numTestsPerEvictionRun = 3;

    /**
     * The default value for the {@code testOnCreate} configuration attribute.
     *
     * @see GenericObjectPool#getTestOnCreate()
     * @see GenericKeyedObjectPool#getTestOnCreate()
     * @since 2.2
     */
    private boolean testOnCreate = false;

    /**
     * The default value for the {@code testOnBorrow} configuration attribute.
     *
     * @see GenericObjectPool#getTestOnBorrow()
     * @see GenericKeyedObjectPool#getTestOnBorrow()
     */
    private boolean testOnBorrow = false;

    /**
     * The default value for the {@code testOnReturn} configuration attribute.
     *
     * @see GenericObjectPool#getTestOnReturn()
     * @see GenericKeyedObjectPool#getTestOnReturn()
     */
    private boolean testOnReturn = true;

    /**
     * The default value for the {@code testWhileIdle} configuration attribute.
     *
     * @see GenericObjectPool#getTestWhileIdle()
     * @see GenericKeyedObjectPool#getTestWhileIdle()
     */
    private boolean testWhileIdle = true;

    /**
     * The default value for the {@code timeBetweenEvictionRunsMillis}
     * configuration attribute.
     *
     * @see GenericObjectPool#getTimeBetweenEvictionRunsMillis()
     * @see GenericKeyedObjectPool#getTimeBetweenEvictionRunsMillis()
     */
    private long timeBetweenEvictionRunsMillis = 1000 * 60 * 20L;

    /**
     * The default value for the {@code blockWhenExhausted} configuration
     * attribute.
     *
     * @see GenericObjectPool#getBlockWhenExhausted()
     * @see GenericKeyedObjectPool#getBlockWhenExhausted()
     */
    private boolean blockWhenExhausted = true;

    /**
     * The default value for enabling JMX for pools created with a configuration
     * instance.
     */
    private boolean jmxEnable = false;

    /**
     * The connection max idle time
     */
    private long maxIdleMillis = 1000 * 60 * 5L;

}
