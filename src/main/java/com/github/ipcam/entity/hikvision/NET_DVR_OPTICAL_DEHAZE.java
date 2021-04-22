package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_OPTICAL_DEHAZE
 *
 * @author echils
 * @since 2020-03-19 13:42
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
