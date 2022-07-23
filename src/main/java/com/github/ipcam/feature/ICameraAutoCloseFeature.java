package com.github.ipcam.feature;

import com.github.ipcam.exception.CameraConnectionException;

/**
 * ICameraAutoCloseFeature
 *
 * @author echils
 */
public interface ICameraAutoCloseFeature extends AutoCloseable {


    /**
     * Network camera general close connection
     *
     * @throws CameraConnectionException see {@link CameraConnectionException}
     */
    void close() throws CameraConnectionException;

}
