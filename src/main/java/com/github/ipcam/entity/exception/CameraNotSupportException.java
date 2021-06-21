package com.github.ipcam.entity.exception;


/**
 * CameraNotSupportException
 *
 * @author echils
 */
public class CameraNotSupportException extends CameraConnectionException {

    public CameraNotSupportException() {
    }

    public CameraNotSupportException(Throwable cause) {
        super(cause);
    }

    public CameraNotSupportException(String message) {
        super(message);
    }

    public CameraNotSupportException(String message, Throwable cause) {
        super(message, cause);
    }
}
