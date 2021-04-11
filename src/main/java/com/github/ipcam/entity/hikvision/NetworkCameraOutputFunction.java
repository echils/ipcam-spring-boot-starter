package com.github.ipcam.entity.hikvision;

/**
 * NetworkCameraOutputFunction
 *
 * @author echils
 * @since 2020-03-19 16:55
 */
@FunctionalInterface
public interface NetworkCameraOutputFunction<T> {

    /**
     * data stream output
     *
     * @param t
     */
    void output(T t);
}
