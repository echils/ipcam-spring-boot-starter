package com.github.ipcam.support;


import com.github.ipcam.entity.NVRChannelInfo;
import com.github.ipcam.entity.exception.CameraNotSupportException;

import java.util.List;

/**
 * ICameraNVRSupport
 *
 * @author echils
 * @since 2020-07-13 11:13
 */
public interface ICameraNVRSupport {

    /**
     * Get the camera information of each ip channel in the NVR
     *
     * @return {@link NVRChannelInfo}
     */
   default List<NVRChannelInfo> getChannelInfo(){
       throw new CameraNotSupportException();
   }

}
