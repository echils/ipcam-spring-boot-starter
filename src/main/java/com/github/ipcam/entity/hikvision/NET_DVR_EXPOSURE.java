package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_EXPOSURE
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_EXPOSURE extends Structure {

    public byte byExposureMode;

    public byte byAutoApertureLevel;

    public byte[] byRes = new byte[2];

    public int dwVideoExposureSet;

    public int dwExposureUserSet;

    public int dwRes;

}
