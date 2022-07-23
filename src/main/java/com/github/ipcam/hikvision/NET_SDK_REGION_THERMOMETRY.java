package com.github.ipcam.hikvision;

import com.sun.jna.Structure;

/**
 * NET_SDK_REGION_THERMOMETRY
 *
 * @author echils
 */
public class NET_SDK_REGION_THERMOMETRY extends Structure {

    /**
     * Maximum temperature, accurate to one decimal place (-40-1000 ° C), (floating point +100) *10
     */
    public float fMaxTemperature;

    /**
     * Minimum temperature, accurate to one decimal place (-40-1000 ° C), (floating point +100) *10
     */
    public float fMinTemperature;

    /**
     * Average temperature, accurate to one decimal place (-40-1000 ° C), (floating point +100) *10
     */
    public float fAverageTemperature;

    /**
     * Temperature difference, accurate to one decimal place (-40-1000 ° C), (floating point +100) *10
     */
    public float fTemperatureDiff;

    /**
     * Area, line (takes effect when rule demarcation type is "1-box" or "2-line"
     */
    public NET_VCA_POLYGON struRegion;

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes = new byte[20];

}
