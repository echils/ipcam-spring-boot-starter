package com.github.ipcam.support;


import com.github.ipcam.entity.exception.CameraNotSupportException;
import com.github.ipcam.entity.infos.NVRChannelInfo;

import java.util.List;

/**
 * ICameraNVRSupport
 *
 * @author echils
 */
public interface ICameraNVRSupport {

    /**
     * Get the camera information of each ip channel in the NVR
     *
     * @return {@link NVRChannelInfo}
     */
    default List<NVRChannelInfo> getChannelInfo() {
        throw new CameraNotSupportException();
    }

}
