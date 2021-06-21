package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_WDR
 *
 * @author echils
 */
public class NET_DVR_WDR extends Structure {

    /**
     * Wide dynamic whether enabled, 0- not enabled, 1- enabled, 2- automatic
     */
    public byte byWDREnabled;

    /**
     * 0-F
     */
    public byte byWDRLevel1;

    /**
     * 0-F
     */
    public byte byWDRLevel2;

    /**
     * 0-100
     */
    public byte byWDRContrastLevel;

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes = new byte[16];

}
