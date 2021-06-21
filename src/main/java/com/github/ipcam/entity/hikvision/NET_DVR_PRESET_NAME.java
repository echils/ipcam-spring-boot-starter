package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;


/**
 * NET_DVR_PRESET_NAME
 *
 * @author echils
 */
public class NET_DVR_PRESET_NAME extends Structure {

    /**
     * Structure size
     */
    public int dwSize;

    /**
     * Preset point number
     */
    public short wPresetNum;

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes1 = new byte[2];

    /**
     * Preset point name
     */
    public byte[] byName = new byte[32];

    /**
     * Horizontal parameter, only support to obtain, if the data obtained is greater than 360 by default minus 360
     */
    public short wPanPos;

    /**
     * Vertical parameter, only support to obtain, if the data obtained is greater than 360 by default minus 360
     */
    public short wTiltPos;

    /**
     * Variable parameter, only support to obtain, if the data obtained is greater than 360 subtract 360 by default
     */
    public short wZoomPos;

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes = new byte[58];

}
