package com.github.ipcam.entity.hikvision;

import com.github.ipcam.entity.comm.StructureContext;
import com.sun.jna.Structure;


/**
 * NET_VCA_POLYGON
 *
 * @author echils
 * @since 2020-03-19 13:42
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
    public NET_VCA_POINT[] struPos = new NET_VCA_POINT[StructureContext.VCA_MAX_POLYGON_POINT_NUM];

}
