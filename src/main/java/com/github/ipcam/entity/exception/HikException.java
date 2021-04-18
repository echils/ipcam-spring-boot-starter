package com.github.ipcam.entity.exception;

/**
 * HikException
 *
 * @author echils
 * @since 2021-04-11 15:07:00
 */
public class HikException extends RuntimeException {

    public HikException() {
    }

    public HikException(String message) {
        super(message);
    }

    public HikException(Throwable cause) {
        super(cause);
    }


}
