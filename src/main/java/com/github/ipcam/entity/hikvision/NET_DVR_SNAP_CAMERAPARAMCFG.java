package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_SNAP_CAMERAPARAMCFG
 *
 * @author echils
 */
public class NET_DVR_SNAP_CAMERAPARAMCFG extends Structure {

    /**
     * Wide dynamic mode: 0- off, 1- digital wide dynamic, 2- wide dynamic state
     */
    public byte byWDRMode;

    /**
     * Wide dynamic switching mode: 0- forcibly enabled, 1- by time enabled, 2- by brightness enabled
     */
    public byte byWDRType;

    /**
     * Wide dynamic level, index 0~6 corresponding to levels 1~7, default index 2 (i.e., level 3)
     */
    public byte byWDRLevel;

    /**
     * Keep
     */
    public byte byRes1;

    /**
     * Start wide dynamic time
     */
    public NET_DVR_TIME_EX struStartTime = new NET_DVR_TIME_EX();

    /**
     * End wide dynamic time
     */
    public NET_DVR_TIME_EX struEndTime = new NET_DVR_TIME_EX();

    /**
     * Luminance threshold for day and night conversion, range: 0~100, default: 50
     */
    public byte byDayNightBrightness;

    /**
     * Memory color enhancement enabler: 0- off, 1- on
     */
    public byte byMCEEnabled;

    /**
     * Memory color enhancement strength, value range: 0~100, default value: 50
     */
    public byte byMCELevel;

    /**
     * Automatic contrast enable: 0- off, 1- on
     */
    public byte byAutoContrastEnabled;

    /**
     * Automatic comparison level, value range: 0~100, default value: 50
     */
    public byte byAutoContrastLevel;

    /**
     * DETAIL ENHANCEMENT ENABLES: 0- OFF, 1- ON
     */
    public byte byLSEDetailEnabled;

    /**
     * Detail enhancement level, range: 0~100, default: 50
     */
    public byte byLSEDetailLevel;

    /**
     * License plate enhancement enabler: 0- off, 1- on
     */
    public byte byLPDEEnabled;

    /**
     * License plate enhancement level, value range: 0~100, default value: 50
     */
    public byte byLPDELevel;

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes = new byte[35];

}

