package com.github.ipcam.support;


import com.github.ipcam.entity.CameraInfo;
import com.github.ipcam.entity.reference.StreamTypeEnum;

import java.util.List;

/**
 * ICameraBasicSupport
 *
 * @author echils
 * @since 2020-03-27 16:26
 */
public interface ICameraBasicSupport {


    /**
     * Login to the camera get the resources with default config
     *
     * @param ip       The ip of camera
     * @param port     The port of camera
     * @param username The username of camera
     * @param password The password of camera
     */
    long login(String ip, int port, String username, String password);


    /**
     * Login into network camera with waitTime and tryTime
     *
     * @param ip       The ip of camera
     * @param port     The port of camera
     * @param username The username of camera
     * @param password The password of camera
     * @param waitTime The waitTime of camera
     * @param tryTime  The tryTime of camera
     */
    long login(String ip, int port, String username, String password, int waitTime, int tryTime);


    /**
     * Logout from camera anc release camera resources
     */
    void logout();


    /**
     * Get the ip channel info of the camera
     */
    List<String> getChannels();


    /**
     * Get the preview handle of the camera,
     * video stream data will not be retrieved by this function
     *
     * @param channel    the channel of camera
     * @param streamType {@link StreamTypeEnum }
     */
    long preview(String channel, StreamTypeEnum streamType);


    /**
     * Release the preview resources
     *
     * @param channel the channel of camera
     */
    void release(String channel);


    /**
     * Change the password of the camera
     *
     * @param channel     the channel of camera
     * @param newPassword the new password of camera
     */
    void changePassword(String channel, String newPassword);


    /**
     * Get basic info of the camera
     *
     * @param channel the channel of camera
     * @return
     */
    CameraInfo getBasicInfo(String channel);
}
