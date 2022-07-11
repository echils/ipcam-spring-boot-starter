package com.github.ipcam.entity.onvif;

import com.github.ipcam.entity.onvif.modes.OnvifMediaProfile;

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
     *
     * @param mediaProfile {@link com.github.ipcam.entity.onvif.command.GetMediaProfilesCommand}
     */
    String content(OnvifMediaProfile mediaProfile);


    /**
     * Parse the response and wrap the result
     *
     * @param response the request value
     */
    T parse(String response) throws Exception;


}
