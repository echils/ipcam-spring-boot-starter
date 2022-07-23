package com.github.ipcam.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_PIRIS_PARAM
 *
 * @author echils
 */
public class NET_DVR_PIRIS_PARAM extends Structure {

    /**
     * P-IRIS mode: 0-automatic, 1-manual
     */
    public byte byMode;

    /**
     * Infrared aperture size level (the higher the level, the larger the aperture) :
     * 1~100, default :50, can be modified in manual mode
     */
    public byte byPIrisAperture;

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes = new byte[6];

}
