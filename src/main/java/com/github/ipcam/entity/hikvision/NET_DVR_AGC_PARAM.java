package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_AGC_PARAM
 *
 * @author echils
 */
public class NET_DVR_AGC_PARAM extends Structure {

    /**
     * 1- Normal scene, 2- Strong light scene, 3- Manual mode
     */
    public byte bySceneType;

    /**
     * Brightness level, value range: [1,100], effective in manual mode
     */
    public byte byLightLevel;

    /**
     * Gain level, value range: [1,100], effective in manual mode
     */
    public byte byGainLevel;

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes = new byte[5];

}
