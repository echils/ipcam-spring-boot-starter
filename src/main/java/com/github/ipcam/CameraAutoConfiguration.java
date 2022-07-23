package com.github.ipcam;

import com.github.ipcam.pool.CameraConnectionPool;
import com.github.ipcam.pool.CameraConnectionPoolConfig;
import com.github.ipcam.pool.CameraConnectionPoolFactory;
import com.github.ipcam.pool.CameraConnectionPoolProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * CameraAutoConfiguration
 *
 * @author echils
 */
@Configuration
@ConditionalOnBean(ICameraSupplier.class)
@EnableConfigurationProperties(CameraConnectionPoolProperties.class)
public class CameraAutoConfiguration {


    @Bean
    @Scope(value = "singleton")
    public <T> CameraConnectionPool<T> netCameraConnectionPool(ICameraSupplier<T> supplier,
                                                               CameraConnectionPoolProperties properties) {
        CameraConnectionPoolFactory<T> cameraConnectionPoolFactory = new CameraConnectionPoolFactory<>(supplier, properties);
        CameraConnectionPoolConfig config = new CameraConnectionPoolConfig();
        config.setLifo(properties.isLifo());
        config.setMaxWaitMillis(properties.getMaxWaitMillis());
        config.setMinEvictableIdleTimeMillis(properties.getMinEvictableIdleTimeMillis());
        config.setEvictorShutdownTimeoutMillis(properties.getEvictorShutdownTimeoutMillis());
        config.setNumTestsPerEvictionRun(properties.getNumTestsPerEvictionRun());
        config.setTestOnCreate(properties.isTestOnCreate());
        config.setTestOnBorrow(properties.isTestOnBorrow());
        config.setTestOnReturn(properties.isTestOnReturn());
        config.setTestWhileIdle(properties.isTestWhileIdle());
        config.setTimeBetweenEvictionRunsMillis(properties.getTimeBetweenEvictionRunsMillis());
        config.setBlockWhenExhausted(properties.isBlockWhenExhausted());
        return new CameraConnectionPool(cameraConnectionPoolFactory, config);
    }


}
