package com.github.ipcam.hikvision;

import com.sun.jna.Structure;

/**
 * NET_VCA_POINT
 *
 * @author echils
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
