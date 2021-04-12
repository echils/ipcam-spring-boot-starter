package com.github.ipcam;


import com.github.ipcam.entity.reference.StreamTypeEnum;

import java.util.List;

/**
 * ICameraBasicFeature
 *
 * @author echils
 * @since 2020-03-27 16:26
 */
public interface ICameraBasicFeature {


    /**
     * login to the camera get the resources with default config
     *
     * @param ip       The ip of camera
     * @param port     The port of camera
     * @param username The username of camera
     * @param password The password of camera
     */
    long login(String ip, int port, String username, String password);


    /**
     * release camera resources
     */
    void logout();


    /**
     * get the ip channel info of the camera
     */
    List<String> getChannel();


    /**
     * get the preview handle of the camera,
     * video stream data will not be retrieved by this function
     *
     * @param channel    the channel of camera
     * @param streamType {@link StreamTypeEnum }
     */
    long preview(String channel, StreamTypeEnum streamType);


    /**
     * release the preview resources
     *
     * @param channel the channel of camera
     */
    void release(String channel);

    /**
     * change the password of the camera
     *
     * @param channel     the channel of camera
     * @param newPassword the new passord of camera
     */
    void changePassword(String channel, String newPassword);

}
