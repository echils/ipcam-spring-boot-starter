package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;


/**
 * NET_VCA_POLYGON
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_VCA_POLYGON extends Structure {
    public int dwPointNum;
    public NET_VCA_POINT[] struPos = new NET_VCA_POINT[NetworkCameraContext.VCA_MAX_POLYGON_POINT_NUM];
}
