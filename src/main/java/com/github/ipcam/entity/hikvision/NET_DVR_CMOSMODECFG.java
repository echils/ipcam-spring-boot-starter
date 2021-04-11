package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_CMOSMODECFG
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_CMOSMODECFG extends Structure {
    public byte byCaptureMod;
    public byte byBrightnessGate;
    public byte byCaptureGain1;
    public byte byCaptureGain2;
    public int dwCaptureShutterSpeed1;
    public int dwCaptureShutterSpeed2;
    public byte[] byRes = new byte[4];
}
