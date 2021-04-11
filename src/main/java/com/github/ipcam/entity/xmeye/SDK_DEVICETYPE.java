package com.github.ipcam.entity.xmeye;


import com.sun.jna.Structure;

/**
 * SDK_DEVICETYPE
 *
 * @author echils
 * @since 2018-11/-9 10:58
 */
public class SDK_DEVICETYPE extends Structure {

    public int SDK_DEVICE_TYPE_DVR;    ///< 普通DVR设备

    public int SDK_DEVICE_TYPE_NVS;    ///< NVS设备

    public int SDK_DEVICE_TYPE_IPC;    ///< IPC设备

    public int SDK_DEVICE_TYPE_HVR;    ///<混合dvr

    public int SDK_DEVICE_TYPE_IVR;    ///<智能dvr

    public int SDK_DEVICE_TYPE_MVR;    ///<车载dvr

    public int SDK_DEVICE_TYPE_NR;
}
