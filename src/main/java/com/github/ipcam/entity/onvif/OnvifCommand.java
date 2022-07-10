package com.github.ipcam.entity.onvif;

/**
 * OnvifCommand
 *
 * @author echils
 */
public interface OnvifCommand<T> {


    /**
     * The request URI
     */
    String uri();


    /**
     * The content of request xml
     */
    String content();


    /**
     * Parse the response and wrap the result
     *
     * @param response the request value
     */
    T parse(String response) throws Exception;


}
