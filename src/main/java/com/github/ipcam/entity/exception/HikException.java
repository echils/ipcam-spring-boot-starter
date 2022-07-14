package com.github.ipcam.entity.exception;

/**
 * HikException
 *
 * @author echils
 */
public class HikException extends CameraDriverException {

    public HikException() {
    }

    public HikException(String message) {
        super(message);
    }

    public HikException(Throwable cause) {
        super(cause);
    }


}
