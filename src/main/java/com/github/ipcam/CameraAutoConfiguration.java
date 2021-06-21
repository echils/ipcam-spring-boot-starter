package com.github.ipcam;

import com.github.ipcam.pool.CameraConnectionPool;
import com.github.ipcam.pool.CameraConnectionPoolConfig;
import com.github.ipcam.pool.CameraConnectionPoolFactory;
import com.github.ipcam.pool.CameraConnectionPoolProperties;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Autowired
    private CameraConnectionPoolProperties networkConnectionPoolProperties;


    @Bean
    @Scope(value = "singleton")
    public CameraConnectionPool netCameraConnectionPool(ICameraSupplier supplier) {
        CameraConnectionPoolFactory cameraConnectionPoolFactory = new CameraConnectionPoolFactory(supplier);
        CameraConnectionPoolConfig config = new CameraConnectionPoolConfig();
        config.setLifo(networkConnectionPoolProperties.isLifo());
        config.setFairness(networkConnectionPoolProperties.isFairness());
        config.setMaxWaitMillis(networkConnectionPoolProperties.getMaxWaitMillis());
        config.setMinEvictableIdleTimeMillis(networkConnectionPoolProperties.getMinEvictableIdleTimeMillis());
        config.setSoftMinEvictableIdleTimeMillis(networkConnectionPoolProperties.getSoftMinEvictableIdleTimeMillis());
        config.setEvictorShutdownTimeoutMillis(networkConnectionPoolProperties.getEvictorShutdownTimeoutMillis());
        config.setNumTestsPerEvictionRun(networkConnectionPoolProperties.getNumTestsPerEvictionRun());
        config.setTestOnCreate(networkConnectionPoolProperties.isTestOnCreate());
        config.setTestOnBorrow(networkConnectionPoolProperties.isTestOnBorrow());
        config.setTestOnReturn(networkConnectionPoolProperties.isTestOnReturn());
        config.setTestWhileIdle(networkConnectionPoolProperties.isTestWhileIdle());
        config.setTimeBetweenEvictionRunsMillis(networkConnectionPoolProperties.getTimeBetweenEvictionRunsMillis());
        config.setBlockWhenExhausted(networkConnectionPoolProperties.isBlockWhenExhausted());
        config.setJmxEnabled(networkConnectionPoolProperties.isJmxEnable());
        return new CameraConnectionPool(cameraConnectionPoolFactory, config);
    }

}
