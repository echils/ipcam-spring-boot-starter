package com.github.ipcam.entity.onvif.digest.fromhttpclient;

/**
 * ParseException
 *
 * @author echils
 * @see "https://mvnrepository.com/artifact/com.burgstaller/okhttp-digest"
 */
public class ParseException extends RuntimeException {

    private static final long serialVersionUID = -7288819855864183578L;

    public ParseException() {
    }

    public ParseException(String message) {
        super(message);
    }

}
