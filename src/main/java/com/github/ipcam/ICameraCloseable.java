package com.github.ipcam;

import com.github.ipcam.entity.exception.CameraConnectionException;

/**
 * ICameraCloseable
 *
 * @author echils
 * @since 2020-03-19 18:08
 */
public interface ICameraCloseable extends AutoCloseable {


    /**
     * network camera general close connection
     *
     * @throws CameraConnectionException
     */
    void close() throws CameraConnectionException;

}
