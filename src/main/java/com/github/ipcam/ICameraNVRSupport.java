package com.github.ipcam;


import com.github.ipcam.entity.NVRChannelInfo;

import java.util.List;

/**
 * ICameraNVRSupport
 *
 * @author echils
 * @since 2020-07-13 11:13
 */
public interface ICameraNVRSupport {

    /**
     * Get the camera information of each IP channel in the NVR
     *
     * @return {@link NVRChannelInfo}
     */
    List<NVRChannelInfo> getChannelInfo();

}
