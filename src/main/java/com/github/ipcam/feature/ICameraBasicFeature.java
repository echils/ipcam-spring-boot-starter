package com.github.ipcam.feature;


import com.github.ipcam.entity.CameraInfo;

import java.util.List;

/**
 * ICameraBasicFeature
 *
 * @author echils
 */
public interface ICameraBasicFeature {


    /**
     * Get basic info of the camera
     */
    CameraInfo getBasicInfo();


    /**
     * Get the ip channel info of the camera
     */
    List<String> getChannels();


}
