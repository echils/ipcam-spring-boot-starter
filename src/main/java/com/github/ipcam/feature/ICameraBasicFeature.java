package com.github.ipcam.feature;


import com.github.ipcam.entity.infos.CameraInfo;

import java.util.List;

/**
 * ICameraBasicSupport
 *
 * @author echils
 */
public interface ICameraBasicFeature extends ICameraDriverFeature {


    /**
     * Get basic info of the camera
     *
     * @param channel the channel of camera
     */
    CameraInfo getBasicInfo(String channel);


    /**
     * Get the ip channel info of the camera
     */
    List<String> getChannels();


    /**
     * Change the password of the camera
     *
     * @param channel     the channel of camera
     * @param newPassword the new password of camera
     */
    void changePassword(String channel, String newPassword);


}
