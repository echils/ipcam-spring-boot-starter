package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_CORRIDOR_MODE_CCD
 *
 * @author echils
 * @since 2020-03-19 13:42
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
