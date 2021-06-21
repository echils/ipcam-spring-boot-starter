package com.github.ipcam.entity.xmeye;


import com.sun.jna.Structure;

/**
 * SDK_DEVICETYPE
 *
 * @author echils
 */
public class SDK_DEVICETYPE extends Structure {

    /**
     * Common DVR devices
     */
    public int SDK_DEVICE_TYPE_DVR;

    /**
     * NVS equipment
     */
    public int SDK_DEVICE_TYPE_NVS;

    /**
     * IPC equipment
     */
    public int SDK_DEVICE_TYPE_IPC;

    /**
     * Hybrid DVR
     */
    public int SDK_DEVICE_TYPE_HVR;

    /**
     * Intelligent DVR
     */
    public int SDK_DEVICE_TYPE_IVR;

    /**
     * Car DVR
     */
    public int SDK_DEVICE_TYPE_MVR;

    /**
     * Common DR devices
     */
    public int SDK_DEVICE_TYPE_NR;

}
