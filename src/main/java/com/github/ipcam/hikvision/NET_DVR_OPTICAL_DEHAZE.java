package com.github.ipcam.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_OPTICAL_DEHAZE
 *
 * @author echils
 */
public class NET_DVR_OPTICAL_DEHAZE extends Structure {

    /**
     * Enable: 0- optical fog penetration is not enabled, 1- optical fog penetration is enabled
     */
    public byte byEnable;

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes = new byte[7];

}
