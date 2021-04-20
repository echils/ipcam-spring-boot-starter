package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_CORRIDOR_MODE_CCD
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_CORRIDOR_MODE_CCD extends Structure {

    public byte byEnableCorridorMode;

    public byte[] byRes = new byte[11];

}
