package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_SMARTIR_PARAM
 *
 * @author echils
 */
public class NET_DVR_SMARTIR_PARAM extends Structure {

    /**
     * Smart IR mode: 0-automatic, 1-manual
     */
    public byte byMode;

    /**
     * Infrared distance level (the higher the level, the farther the infrared distance) :
     * 1~100, default :50, can be modified in manual mode
     */
    public byte byIRDistance;

    /**
     * Near light distance level, value range: 1~100
     */
    public byte byShortIRDistance;

    /**
     * High beam distance grade, value range: 1~100
     */
    public byte byLongIRDistance;

}
