package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_VCA_POINT
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_VCA_POINT extends Structure {

    /**
     * X axis coordinates, value range [0.001,1]
     */
    public float fX;

    /**
     * Y axis coordinates, value range [0.001,1]
     */
    public float fY;

}
