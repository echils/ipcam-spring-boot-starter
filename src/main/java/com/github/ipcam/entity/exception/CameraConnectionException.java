package com.github.ipcam.entity.exception;


/**
 * CameraConnectionException
 *
 * @author echils
 * @since 2021-04-11 15:07:00
 */
public class CameraConnectionException extends RuntimeException {

    public CameraConnectionException() {
    }

    public CameraConnectionException(Throwable cause) {
        super(cause);
    }

    public CameraConnectionException(String message) {
        super(message);
    }

    public CameraConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
