package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_EXPOSURE
 *
 * @author echils
 */
public class NET_DVR_EXPOSURE extends Structure {

    /**
     * 0- manual exposure, 1- automatic exposure
     */
    public byte byExposureMode;

    /**
     * Automatic aperture sensitivity, value range: 0~10
     */
    public byte byAutoApertureLevel;

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes = new byte[2];

    /**
     * Customize video exposure time (in us), which is the slowest exposure value for automatic exposure
     */
    public int dwVideoExposureSet;

    /**
     * Customize exposure time. When applied to intelligent traffic cameras and CCD mode, it means capture shutter speed, (us)
     */
    public int dwExposureUserSet;

    /**
     * Keep
     */
    public int dwRes;

}
