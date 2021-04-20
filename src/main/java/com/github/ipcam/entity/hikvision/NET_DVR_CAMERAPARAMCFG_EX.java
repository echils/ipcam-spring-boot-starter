package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_CAMERAPARAMCFG_EX
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_CAMERAPARAMCFG_EX extends Structure {

    public int dwSize;

    public NET_DVR_VIDEOEFFECT struVideoEffect = new NET_DVR_VIDEOEFFECT();

    public NET_DVR_GAIN struGain = new NET_DVR_GAIN();

    public NET_DVR_WHITEBALANCE struWhiteBalance = new NET_DVR_WHITEBALANCE();

    public NET_DVR_EXPOSURE struExposure = new NET_DVR_EXPOSURE();

    public NET_DVR_GAMMACORRECT struGammaCorrect = new NET_DVR_GAMMACORRECT();

    public NET_DVR_WDR struWdr = new NET_DVR_WDR();

    public NET_DVR_DAYNIGHT struDayNight = new NET_DVR_DAYNIGHT();

    public NET_DVR_BACKLIGHT struBackLight = new NET_DVR_BACKLIGHT();

    public NET_DVR_NOISEREMOVE struNoiseRemove = new NET_DVR_NOISEREMOVE();

    public byte byPowerLineFrequencyMode;

    public byte byIrisMode;

    public byte byMirror;

    public byte byDigitalZoom;

    public byte byDeadPixelDetect;

    public byte byBlackPwl;

    public byte byEptzGate;

    public byte byLocalOutputGate;

    public byte byCoderOutputMode;

    public byte byLineCoding;

    public byte byDimmerMode;

    public byte byPaletteMode;

    public byte byEnhancedMode;

    public byte byDynamicContrastEN;

    public byte byDynamicContrast;

    public byte byJPEGQuality;

    public NET_DVR_CMOSMODECFG struCmosModeCfg = new NET_DVR_CMOSMODECFG();

    public byte byFilterSwitch;

    public byte byFocusSpeed;

    public byte byAutoCompensationInterval;

    public byte bySceneMode;

    public NET_DVR_DEFOGCFG struDefogCfg = new NET_DVR_DEFOGCFG();

    public NET_DVR_ELECTRONICSTABILIZATION struElectronicStabilization = new NET_DVR_ELECTRONICSTABILIZATION();

    public NET_DVR_CORRIDOR_MODE_CCD struCorridorMode = new NET_DVR_CORRIDOR_MODE_CCD();

    public byte byExposureSegmentEnable;

    public byte byBrightCompensate;

    public byte byCaptureModeN;

    public byte byCaptureModeP;

    public NET_DVR_SMARTIR_PARAM struSmartIRParam = new NET_DVR_SMARTIR_PARAM();

    public NET_DVR_PIRIS_PARAM struPIrisParam = new NET_DVR_PIRIS_PARAM();

    public NET_DVR_LASER_PARAM_CFG struLaserParam = new NET_DVR_LASER_PARAM_CFG();

    public NET_DVR_FFC_PARAM struFFCParam = new NET_DVR_FFC_PARAM();

    public NET_DVR_DDE_PARAM struDDEParam = new NET_DVR_DDE_PARAM();

    public NET_DVR_AGC_PARAM struAGCParam = new NET_DVR_AGC_PARAM();

    public byte byLensDistortionCorrection;

    public byte[] byRes1 = new byte[3];

    public NET_DVR_SNAP_CAMERAPARAMCFG struSnapCCD = new NET_DVR_SNAP_CAMERAPARAMCFG();

    public NET_DVR_OPTICAL_DEHAZE struOpticalDehaze = new NET_DVR_OPTICAL_DEHAZE();

    public byte[] byRes2 = new byte[180];

}
