package com.github.ipcam.feature;


import com.github.ipcam.entity.NVRChannelInfo;
import com.github.ipcam.entity.exception.CameraNotSupportException;

import java.util.List;

/**
 * ICameraNVRFeature
 *
 * @author echils
 */
public interface ICameraNVRFeature {

    /**
     * Get the camera information of each ip channel in the NVR
     *
     * @return {@link NVRChannelInfo}
     */
    default List<NVRChannelInfo> getChannelInfo() {
        throw new CameraNotSupportException();
    }


    /**
     * Get the camera information of each ip channel in the NVR
     *
     * @return {@link NVRChannelInfo}
     */
    default List<NVRChannelInfo> getDiskInfo() {
        throw new CameraNotSupportException();
    }

}
