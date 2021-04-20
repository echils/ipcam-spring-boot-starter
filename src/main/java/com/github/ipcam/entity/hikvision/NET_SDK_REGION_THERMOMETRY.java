package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_SDK_REGION_THERMOMETRY
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_SDK_REGION_THERMOMETRY extends Structure {

    public float fMaxTemperature;

    public float fMinTemperature;

    public float fAverageTemperature;

    public float fTemperatureDiff;

    public NET_VCA_POLYGON struRegion;

    public byte[] byRes = new byte[20];

}
