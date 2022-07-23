package com.github.ipcam.exception;

/**
 * CameraDriverException
 *
 * @author echils
 */
public class CameraDriverException extends RuntimeException {

    public CameraDriverException() {
    }

    public CameraDriverException(String message) {
        super(message);
    }

    public CameraDriverException(String message, Throwable cause) {
        super(message, cause);
    }

    public CameraDriverException(Throwable cause) {
        super(cause);
    }

    public CameraDriverException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
