package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;


/**
 * NET_SDK_POINT_THERMOMETRY
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_SDK_POINT_THERMOMETRY extends Structure {

    public float fPointTemperature;
    public NET_VCA_POINT struPoint;
    public byte[] byRes = new byte[20];
}
