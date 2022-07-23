package com.github.ipcam.hikvision;

import com.github.ipcam.entity.comm.STRUCTURE_CONTEXT;
import com.sun.jna.Structure;


/**
 * NET_VCA_POLYGON
 *
 * @author echils
 */
public class NET_VCA_POLYGON extends Structure {

    /**
     * Valid points (greater than or equal to 3). If 3 points are on a line, it is considered as invalid area, and if
     * the lines cross, it is considered as invalid area
     */
    public int dwPointNum;

    /**
     * The polygon boundary point, the maximum value is 10
     */
    public NET_VCA_POINT[] struPos = new NET_VCA_POINT[STRUCTURE_CONTEXT.VCA_MAX_POLYGON_POINT_NUM];

}
