package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_AEMODECFG
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_AEMODECFG extends Structure {
    public int dwSize;
    public int iIrisSet;
    public int iGainSet;
    public int iGainLimit;
    public int iExposureCompensate;
    public byte byExposureModeSet;
    public byte byShutterSet;
    public byte byImageStabilizeLevel;
    public byte byCameraIrCorrect;
    public byte byHighSensitivity;
    public byte byInitializeLens;
    public byte byChromaSuppress;
    public byte byMaxShutterSet;
    public byte byMinShutterSet;
    public byte byMaxIrisSet;
    public byte byMinIrisSet;
    public byte byExposureLevel;
    public byte[] byRes2 = new byte[60];
}
