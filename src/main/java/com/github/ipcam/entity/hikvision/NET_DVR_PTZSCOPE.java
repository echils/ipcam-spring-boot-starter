package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_PTZSCOPE
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_PTZSCOPE extends Structure {

    public short panPosMin;

    public short panPosMax;

    public short tiltPosMin;

    public short tiltPosMax;

    public short zoomPosMin;

    public short zoomPosMax;

}
