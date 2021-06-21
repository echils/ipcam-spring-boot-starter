package com.github.ipcam;

import com.github.ipcam.entity.exception.CameraConnectionException;

/**
 * ICameraCloseable
 *
 * @author echils
 */
public interface ICameraCloseable extends AutoCloseable {


    /**
     * network camera general close connection
     *
     * @throws CameraConnectionException see {@link CameraConnectionException}
     */
    void close() throws CameraConnectionException;

}
