package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;


/**
 * NET_SDK_POINT_THERMOMETRY
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_SDK_POINT_THERMOMETRY extends Structure {

    /**
     * Point temperature measurement The current temperature, which takes effect when the calibration type is 0- point.
     * Accurate to one decimal place (-40-1000 ° C), (floating point +100) *10
     */
    public float fPointTemperature;

    /**
     * Point temperature measurement coordinates (effective when the rule calibration type is "0-point")
     */
    public NET_VCA_POINT struPoint;

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes = new byte[20];

}
