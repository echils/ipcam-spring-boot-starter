package com.github.ipcam.pool;

import org.apache.commons.pool2.impl.GenericKeyedObjectPoolConfig;

/**
 * CameraConnectionPoolConfig
 *
 * @author echils
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
