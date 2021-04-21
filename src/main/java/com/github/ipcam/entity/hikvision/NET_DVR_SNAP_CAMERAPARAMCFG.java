package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_SNAP_CAMERAPARAMCFG
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_SNAP_CAMERAPARAMCFG extends Structure {

    public byte byWDRMode;

    public byte byWDRType;

    public byte byWDRLevel;

    public byte byRes1;

    public NET_DVR_TIME_EX struStartTime = new NET_DVR_TIME_EX();

    public NET_DVR_TIME_EX struEndTime = new NET_DVR_TIME_EX();

    public byte byDayNightBrightness;

    public byte byMCEEnabled;

    public byte byMCELevel;

    public byte byAutoContrastEnabled;

    public byte byAutoContrastLevel;

    public byte byLSEDetailEnabled;

    public byte byLSEDetailLevel;

    public byte byLPDEEnabled;

    public byte byLPDELevel;

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes = new byte[35];

}

