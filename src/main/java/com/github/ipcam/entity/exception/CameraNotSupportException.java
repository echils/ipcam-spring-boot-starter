package com.github.ipcam.entity.exception;


/**
 * CameraNotSupportException
 *
 * @author echils
 * @since 2021-04-11 15:07:00
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
