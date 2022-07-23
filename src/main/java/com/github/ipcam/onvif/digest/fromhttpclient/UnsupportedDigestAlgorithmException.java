package com.github.ipcam.onvif.digest.fromhttpclient;

/**
 * UnsupportedDigestAlgorithmException
 *
 * @author echils
 * @see "https://mvnrepository.com/artifact/com.burgstaller/okhttp-digest"
 */
public class UnsupportedDigestAlgorithmException extends IllegalStateException {

    public UnsupportedDigestAlgorithmException(String detailMessage) {
        super(detailMessage);
    }

}
