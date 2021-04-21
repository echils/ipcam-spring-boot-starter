package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_LOW_LIGHT_CFG
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_LOW_LIGHT_CFG extends Structure {

    /**
     * Structure size
     */
    public int dwSize;

    public byte byLowLightLimt;

    public byte byLowLightLimtLevel;

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes = new byte[66];

}
