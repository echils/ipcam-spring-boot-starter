package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_AEMODECFG
 *
 * @author echils
 */
public class NET_DVR_AEMODECFG extends Structure {

    /**
     * Structure size
     */
    public int dwSize;

    /**
     * Aperture, is the actual value *100, 0 means to close the aperture
     */
    public int iIrisSet;

    /**
     * The fastball gain (exposure compensation, used to adjust the gain in manual exposure mode)
     * is the actual value *100, which may be negative
     */
    public int iGainSet;

    /**
     * Gain limit (exposure compensation, used to adjust gain limit in manual exposure mode)
     * is the actual value *100, which may be negative
     */
    public int iGainLimit;

    /**
     * Exposure compensation is the actual value *100, such as 1050 for 10.5dB, -750 for -7.5dB
     */
    public int iExposureCompensate;

    /**
     * Exposure mode of the ball machine: 0- manual mode, 1- automatic exposure, 2- aperture priority,
     * 3- shutter priority, 4- gain priority
     */
    public byte byExposureModeSet;

    /**
     * Shutter class: 0-off, 1-auto X1, 2-auto X2, 3-auto X4, 4-auto X8, 5-auto X16, 6-auto X32, 7-auto X64, 8-auto X128,
     * 9-1/1, 10-1/2, 11-1/3, 12-1/4, 13-1/6, 14-1/8, 15-1/12, 16-1/15, 17-1/25, 18-1/30, 19-1/50, 20-1/60, 21-1/75,
     * 22-1/90, 23-1/100, 24-1/120, 25-1/125, 26-1/150, 27- 1/180,28-1/200, 29-1/215, 30-1/250, 31-1/300, 32-1/350,
     * 33-1/425, 34-1/500, 35-1/600, 36-1/725, 37-1/1000, 38-1/1250, 39-1500,40-1/1750, 41-1/2000, 42-1/2500, 43-3000,
     * 44-1/3500, 45-1/4000, 46-1/10000, 47-1/10000, 48-1/300003, 49-1/1000004, 50-1/175, 51-1/195, 52-1/225, 53-1/230
     */
    public byte byShutterSet;

    /**
     * Anti-jitter level, value range: 0~3
     */
    public byte byImageStabilizeLevel;

    /**
     * Infrared correction: 0-off, 1-on, 2-automatic
     */
    public byte byCameraIrCorrect;

    /**
     * High sensitivity Settings: 0-off, 1-on
     */
    public byte byHighSensitivity;

    /**
     * Initialize lens: 0- off, 1- on
     */
    public byte byInitializeLens;

    /**
     * Color suppression, value range: 0~255
     */
    public byte byChromaSuppress;

    /**
     * Maximum shutter value with the same index value as byShutterset (effective in auto-exposure mode)
     */
    public byte byMaxShutterSet;

    /**
     * Minimum shutter value with the same index as byShutterset (effective in auto-exposure mode)
     */
    public byte byMinShutterSet;

    /**
     * Maximum aperture limit (in automatic exposure mode), range: [0,100]
     */
    public byte byMaxIrisSet;

    /**
     * Minimum aperture limit (in automatic exposure mode), range: [0,100]
     */
    public byte byMinIrisSet;

    /**
     * Exposure level (Exposure mode is automatic, aperture priority, shutter priority is valid),
     * values range: levels 1 to 5, default is 4 (for compatibility with older versions,
     * the default is exposure level 1 when this byte is 0)
     */
    public byte byExposureLevel;

    /**
     * Keep
     */
    public byte[] byRes2 = new byte[60];

}
