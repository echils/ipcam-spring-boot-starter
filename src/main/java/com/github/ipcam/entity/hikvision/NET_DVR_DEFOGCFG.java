package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_DEFOGCFG
 *
 * @author echils
 */
public class NET_DVR_DEFOGCFG extends Structure {

    /**
     * Fog penetration mode: 0- not enabled, 1- automatic mode, 2- normally on mode
     */
    public byte byMode;

    /**
     * Fog penetration level, value range: 0~100
     */
    public byte byLevel;

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes = new byte[6];

}
