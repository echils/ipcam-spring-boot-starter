package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_CMOSMODECFG
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_CMOSMODECFG extends Structure {

    /**
     * Catch Mode: 0- Catch Mode 1; 1- Catch Mode 2
     */
    public byte byCaptureMod;

    /**
     * Brightness threshold
     */
    public byte byBrightnessGate;

    /**
     * Catch gain 1, 0-100
     */
    public byte byCaptureGain1;

    /**
     * Catch gain 2, 0-100
     */
    public byte byCaptureGain2;

    /**
     * Snap shutter speed 1
     */
    public int dwCaptureShutterSpeed1;

    /**
     * Snap shutter speed 2
     */
    public int dwCaptureShutterSpeed2;

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes = new byte[4];

}
