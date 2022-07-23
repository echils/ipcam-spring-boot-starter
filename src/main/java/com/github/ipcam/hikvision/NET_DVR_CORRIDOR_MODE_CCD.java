package com.github.ipcam.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_CORRIDOR_MODE_CCD
 *
 * @author echils
 */
public class NET_DVR_CORRIDOR_MODE_CCD extends Structure {

    /**
     * Rotation enabled or not: 0- Not enabled, 1- Enable
     */
    public byte byEnableCorridorMode;

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes = new byte[11];

}
