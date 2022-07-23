package com.github.ipcam.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_ELECTRONICSTABILIZATION
 *
 * @author echils
 */
public class NET_DVR_ELECTRONICSTABILIZATION extends Structure {

    /**
     * Electronic anti-shake enablement: 0- not enabled, 1- enabled
     */
    public byte byEnable;

    /**
     * Electronic anti-shaking level, value range: 0~100
     */
    public byte byLevel;

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes = new byte[6];

}
