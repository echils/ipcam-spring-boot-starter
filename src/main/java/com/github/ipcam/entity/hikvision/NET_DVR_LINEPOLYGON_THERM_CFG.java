package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_LINEPOLYGON_THERM_CFG
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_LINEPOLYGON_THERM_CFG extends Structure {

    public float fMaxTemperature;

    public float fMinTemperature;

    public float fAverageTemperature;

    public float fTemperatureDiff;

    public NET_VCA_POLYGON struRegion = new NET_VCA_POLYGON();

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes = new byte[32];

}
