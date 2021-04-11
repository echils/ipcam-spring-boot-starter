package com.github.ipcam.pool;

import org.apache.commons.pool2.impl.GenericKeyedObjectPoolConfig;

/**
 * CameraConnectionPoolConfig
 *
 * @author echils
 * @since 2020-03-23 16:46
 */
public class CameraConnectionPoolConfig extends GenericKeyedObjectPoolConfig {

    public CameraConnectionPoolConfig() {
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        super.toStringAppendFields(builder);
        return "CameraConnectionPoolConfig{" + builder.toString() + "}";
    }

}
