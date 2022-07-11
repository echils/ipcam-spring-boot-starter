package com.github.ipcam.entity.onvif.digest.fromhttpclient;

/**
 * HeaderElement
 *
 * @author echils
 * @see "https://mvnrepository.com/artifact/com.burgstaller/okhttp-digest"
 */
public interface HeaderElement {

    String getName();

    String getValue();

    NameValuePair[] getParameters();

    NameValuePair getParameterByName(String var1);

    int getParameterCount();

    NameValuePair getParameter(int var1);
}
