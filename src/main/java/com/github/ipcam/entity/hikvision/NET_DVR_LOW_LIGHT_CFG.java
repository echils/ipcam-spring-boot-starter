package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_LOW_LIGHT_CFG
 *
 * @author echils
 */
public class NET_DVR_LOW_LIGHT_CFG extends Structure {

    /**
     * Structure size
     */
    public int dwSize;

    /**
     * Low illumination electronic shutter: 0- off, 1- on
     */
    public byte byLowLightLimt;

    /**
     * Ball machine low illumination electronic shutter grade: 0- Close, 1- Slow Shutter * 2,2 - Slow Shutter * 3,
     * 3 - Slow Shutter * 4,4 - Slow Shutter * 6,5 - Slow Shutter * 8,6 - Slow Shutter * 12,7 - Slow Shutter * 16,
     * 8 - Slow Shutter * 24,9 - Slow Shutter * 32,10 - Slow Shutter *48, 11-Slow Shutter *64, 12-Slow Shutter *96,
     * 13- Slow shutter * 128,14 - Slow shutter * 256,15 - Slow shutter *512
     */
    public byte byLowLightLimtLevel;

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes = new byte[66];

}
