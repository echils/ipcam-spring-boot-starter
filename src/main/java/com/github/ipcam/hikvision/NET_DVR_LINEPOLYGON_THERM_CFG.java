package com.github.ipcam.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_LINEPOLYGON_THERM_CFG
 *
 * @author echils
 */
public class NET_DVR_LINEPOLYGON_THERM_CFG extends Structure {

    /**
     * The highest temperature
     */
    public float fMaxTemperature;

    /**
     * The lowest temperature
     */
    public float fMinTemperature;

    /**
     * The average temperature
     */
    public float fAverageTemperature;

    /**
     * Temperature difference
     */
    public float fTemperatureDiff;

    /**
     * Area coordinates (in effect when rule demarcation type is box/line)
     */
    public NET_VCA_POLYGON struRegion = new NET_VCA_POLYGON();

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes = new byte[32];

}
