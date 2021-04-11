package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_THERMOMETRY_BASICPARAM
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_THERMOMETRY_BASICPARAM extends Structure {

    public int dwSize;
    public byte byEnabled;
    public byte byStreamOverlay;
    public byte byPictureOverlay;
    public byte byThermometryRange;
    public byte byThermometryUnit;
    public byte byThermometryCurve;
    public byte byFireImageModea;
    public byte byShowTempStripEnable;
    public float fEmissivity;
    public byte byDistanceUnit;
    public byte byEnviroHumidity;
    public byte[] byRes2 = new byte[2];
    public NET_DVR_TEMPERATURE_COLOR struTempColor;
    public int iEnviroTemperature;
    public int iCorrectionVolume;
    public byte bySpecialPointThermType;
    public byte byReflectiveEnabled;
    public short wDistance;
    public float fReflectiveTemperature;
    public float fAlert;
    public float fAlarm;
    public byte[] byRes = new byte[72];
}
