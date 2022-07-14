package com.github.ipcam.entity.exception;

/**
 * OnvifException
 *
 * @author echils
 */
public class OnvifException extends CameraDriverException {

    public OnvifException() {
    }

    public OnvifException(String message) {
        super(message);
    }

    public OnvifException(Throwable cause) {
        super(cause);
    }

}
