package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_CAMERAPARAMCFG_EX
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_CAMERAPARAMCFG_EX extends Structure {

    /**
     * Structure size
     */
    public int dwSize;

    /**
     * Video effect parameters
     */
    public NET_DVR_VIDEOEFFECT struVideoEffect = new NET_DVR_VIDEOEFFECT();

    /**
     * Gain parameters
     */
    public NET_DVR_GAIN struGain = new NET_DVR_GAIN();

    /**
     * White balance parameter
     */
    public NET_DVR_WHITEBALANCE struWhiteBalance = new NET_DVR_WHITEBALANCE();

    /**
     * Exposure parameters
     */
    public NET_DVR_EXPOSURE struExposure = new NET_DVR_EXPOSURE();

    /**
     * Gamma correction parameter
     */
    public NET_DVR_GAMMACORRECT struGammaCorrect = new NET_DVR_GAMMACORRECT();

    /**
     * Wide dynamic parameter
     */
    public NET_DVR_WDR struWdr = new NET_DVR_WDR();

    /**
     * Day and night conversion function parameters
     */
    public NET_DVR_DAYNIGHT struDayNight = new NET_DVR_DAYNIGHT();

    /**
     * Backlight compensation parameters
     */
    public NET_DVR_BACKLIGHT struBackLight = new NET_DVR_BACKLIGHT();

    /**
     * Digital noise reduction parameters
     */
    public NET_DVR_NOISEREMOVE struNoiseRemove = new NET_DVR_NOISEREMOVE();

    /**
     * 0-50HZ; 1-60HZ
     */
    public byte byPowerLineFrequencyMode;

    /**
     * Aperture mode:
     * 0- Automatic aperture
     * 1- Manual aperture
     * 2- P-Iris1
     * 3-UNION 3-9mm F1.6-2.7 (T5280-PQ1) 3-UNION 3-9mm F1.6-2.7 (T5280-PQ1)
     * 4-UNION 2.8-12mm F1.6-2.7 (T5289-PQ1) 4-UNION 2.8-12mm F1.6-2.7 (T5289-PQ1)
     * 5-HIK 3.8-16mm F1.5 (HV3816P-8MPIR)
     * 6-HIK 11-40mm F1.7 (HV1140P-8MPIR)
     * 7-HIK 2.7-12mm F1.2 (TV2712P-MPIR) 7-HIK 2.7-12mm F1.2 (TV2712P-MPIR)
     * 8- MZ5721D-12MPIR
     * 9- MZ1555D-12MPIR
     * 10- MZ5721D-12MPIR(RS485)
     * 11- MZ1555D-12MPIR(RS485)
     * When byirisMode ==2, the infrared aperture size level can be configured, i.e. the strupirisParam parameter is valid.
     */
    public byte byIrisMode;

    /**
     * Mirror: 0 off; About 1; 2 up and down; Among the three
     */
    public byte byMirror;

    /**
     * Digital scaling: 0- not enabled, 1- enabled. For the thermal imager, the number multiplier:
     * 0- off, 1- x 2,2 - x 4,3 - x 8,4 - x 16,5 - x 32
     */
    public byte byDigitalZoom;

    /**
     * Bad spot detection is enabled, 0- not enabled, 1- enabled
     */
    public byte byDeadPixelDetect;

    /**
     * Black level compensation, 0-255
     */
    public byte byBlackPwl;

    /**
     * EPTZ switch variable: 0- Turn off the electronic head; 1- Open the electronic head
     */
    public byte byEptzGate;

    /**
     * Local output switch variable:
     * 0- Local output turned off
     * 1- Local BNC output turned on
     * 2- HDMI output turned off
     * 11- Zoom output
     * 12- Trim output
     * 13- Trims the zoom output
     * 20-HDMI_720P50 output on
     * 21-HDMI_720P60 output on
     * 22-HDMI_1080I60 output on
     * 23-HDMI_1080I50 output on
     * 24-HDMI_1080P24 output on
     * 25-HDMI_1080P25 output on
     * 26-HDMI_1080P30 output on
     * 27-HDMI_1080P50 output on
     * 28- HDMI_1080P60 output on
     * 40- SDI_720P50
     * 41- SDI_720P60
     * 42- SDI_1080I50
     * 43- SDI_1080I60
     * 44- SDI_1080P24
     * 45- SDI_1080P25
     * 46- SDI_1080P30
     * 47- SDI_1080P50
     * 48- SDI_1080P60
     * 49- SDI_720P25
     * 50- SDI_720P30
     */
    public byte byLocalOutputGate;

    /**
     * Encoder FPGA output mode: 0-through; 3- Pixel move
     */
    public byte byCoderOutputMode;

    /**
     * Whether to enable line coding: 0- No, 1- Yes
     */
    public byte byLineCoding;

    /**
     * Dimming mode: 0-semi-automatic, 1-automatic, suitable for thermal imager
     */
    public byte byDimmerMode;

    /**
     * Dynamic contrast enhancement: 0- no enhancement; 1 - to enhance
     */
    public byte byPaletteMode;

    /**
     * Enhancement mode (detection of objects around) : 0- no enhancement, 1-1, 2-2, 3-3, 4-4, suitable for thermal imager
     */
    public byte byEnhancedMode;

    /**
     * Dynamic contrast enhancement: 0- no enhancement; 1 - to enhance
     */
    public byte byDynamicContrastEN;

    /**
     * Dynamic contrast: 0~100
     */
    public byte byDynamicContrast;

    /**
     * JPEG image quality: 0~100. This parameter is not valid for Intelligent Traffic Cameras V3.5 or above.
     * The function is implemented by the WJPEGPICSIZE of the enabling parameter (NET_DVR_SNAPENABLEFG).
     */
    public byte byJPEGQuality;

    /**
     * Front-end parameters are configured in CMOS mode, and the lens mode is obtained from the capability set
     */
    public NET_DVR_CMOSMODECFG struCmosModeCfg = new NET_DVR_CMOSMODECFG();

    /**
     * Filter switch: 0- not enabled, 1- enabled, suitable for thermal imager
     */
    public byte byFilterSwitch;

    /**
     * Lens focusing speed, value range 0~10, suitable for thermal imager
     */
    public byte byFocusSpeed;

    /**
     * Timing automatic shutter compensation, value range 1~120, unit: minutes, suitable for thermal imager
     */
    public byte byAutoCompensationInterval;

    /**
     * Scene Mode: 0- Outdoor, 1- Indoor, 2- Default, 3- Low Light
     */
    public byte bySceneMode;

    /**
     * Parameters to penetrate the fog
     */
    public NET_DVR_DEFOGCFG struDefogCfg = new NET_DVR_DEFOGCFG();

    /**
     * Electronic image stabilization
     */
    public NET_DVR_ELECTRONICSTABILIZATION struElectronicStabilization = new NET_DVR_ELECTRONICSTABILIZATION();

    /**
     * The rotation function
     */
    public NET_DVR_CORRIDOR_MODE_CCD struCorridorMode = new NET_DVR_CORRIDOR_MODE_CCD();

    /**
     * The exposure time and gain are adjusted in a step-like manner: 0- not enabled, 1- enabled. For example,
     * when the exposure is adjusted upwards, the exposure time is first increased to the middle value, then the
     * gain is increased to the middle value, then the exposure is increased to the maximum value, and finally
     * the gain is increased to the maximum value
     */
    public byte byExposureSegmentEnable;

    /**
     * Enhanced night brightness, value range: [0,100]
     */
    public byte byBrightCompensate;

    /**
     * Video input mode (N system) :
     * 0-off, 1-640*480@25fps, 2-640* on, 3-704* on, 4-704* on, 5-1280* on, 1-640* on, 3-704* on, 4-704* on, 5-1280* on,
     * 6-1280*720@30fps, 7-1280* light, 8-1280* light, 9-1280* light, 10-1280* light, 7-1280* light, 8-1280* light, 9-1280* light, 10-1280* light,
     * 11-1280*960@30fps, 12-1280* Chi, 13--1280* Chi, 14-1600* Chi, 15-1600* Chi,
     * 16-1920*1080@15fps, 17-1920* Sun, 18-1920* Sun, 19-1920* Sun, 20-1920* Sun, 20-1920* Sun,
     * 21-2048*1536@15fps, 22-2048* *, 23-2048* *, 24-2048* *, 25-2048* *,
     * 26-2560*2048@25fps, 27-2560* at, 28-2560* at, 29-3072* at, 30-3072* at,
     * 31-2048*1536@12.5fps, 32-2560* cheg, 33-1600* cheg, 34-1600* cheg, 35-1600* cheg,
     * 36-1600*900@12.5fps, chip @15fps, 38-800* ce, 39-800* ce, 40-4000* ce,
     * 41-4000*3000@15fps, 42-4096* carbon, 43-3840* ce, 44-960* ca, 45-960* ca,
     * 46-752*582@25fps, 47-768* at, 48-2560* at, 49-2560* at, g,
     * 51-720P@120fps, 52-2048* chem, 53-2048* chem, 54-3840* chem, 55-3840* chem,
     * 56-4096*2160@25fps, 57-4096* *, 58-1280*, 59-1280*, 60-3072*,
     * 61-3072*2048@60fps, 62-3072* at, 63-3072* at, 64-3072* at, 65-3072* at, 65-3072* at,
     * 66-336*256@50fps, 67-336* at, 68-384* at, 69-384* at, 70-640* at,
     * 71-640*512@60fps, 72-2592* at, 73-2592* at, 74-2688* at, 75-2688* at, 75-2688* at,
     * 76-2592*1944@20fps, 77-2592* at, 78-2688* at, 79-2688* at, 80-2688* at,
     * 81-2688*1520@30fps, 82-2720* cheg, 83-2720* cheg, 84-336* cheg, 85-384* cheg, 85-384* cheg, 83-2720* cheg, 84-336* cheg, 85-384* cheg,
     * 86-640*512@25fps, 87-1280* chem, 88-1280* chem, 89-1280* chem, 90-1280* sem,
     * 91-4000*3000@20fps, 92-1920* SH, 93-1920* SH, 94-2560* SH, 95-2560* SH, 94-2560* SH, 95-2560* SH,
     * 96-2560*1920@30fps, 97-1280* *, 98-1280*, 99-4000* *, 100-4000* *,
     * 101-4000*3000@10fps, 102-384 * at, 103-2560* at, 104-2400* at, 105-1200* at,
     * 106-4096*1800@30fps, 107-3840* z, 108-2560* z, 109-704* z, 110-1280* z,
     * 111-4096*1800@25fps, 112-3840* *, 113-2560* *, 114-704* *, 115-1280* *,
     * 116-2400*3840@24fps, 117-3840* Nat, 118-3840* Nat, 119-2560* Nat, 120-2560* Nat, 120-2560* Nat, 120-2560* Nat,
     * 121-2560*2048@15fps, 122-2560* nil, 123-2560* nil, 124-2256* nil, 125-2256* nil, 125-2256* nil, 125-2256* nil,
     * 126-2592*2592@12.5fps, 127-2592* on, 128-640 * on, 129-2048* on, 130-2048* on,
     * 131-3840*2160@24fps, 132-2592* SH, 133-2592* SH, 134-2592* SH, 134-2592* SH, 135-2592* SH, 134-2592* SH, 135-2592* SH,
     * 136-640*960@25fps, 137-640* z, 141-2688* z, 142-2992* z, 143-2992* z, 143-2992* z,
     * 144-3008*2160@25fps, 145-3008* on, 146-3072* on, 147-2560* on, 148-2160* on, 149-2160* on, 149-2160* on,
     * 150-7008*1080@25fps, 151-7008* g, 152-3072* g, 153-1536* g, 154-2560* g, 155-2400* g,
     * 156-3840*2400@30fps, 157-3840* on, 158-384* on, 159-640* on, 160-160* on,
     * 161-1024 * 768 @8.3fps, 162-640 * 480 @8.3fps
     */
    public byte byCaptureModeN;

    /**
     * Video input mode (P system) with the same values as byCaptureModeN
     */
    public byte byCaptureModeP;

    /**
     * Infrared overburst configuration information
     */
    public NET_DVR_SMARTIR_PARAM struSmartIRParam = new NET_DVR_SMARTIR_PARAM();

    /**
     * P-IRIS infrared aperture size level configuration information
     */
    public NET_DVR_PIRIS_PARAM struPIrisParam = new NET_DVR_PIRIS_PARAM();

    /**
     * The laser parameters
     */
    public NET_DVR_LASER_PARAM_CFG struLaserParam = new NET_DVR_LASER_PARAM_CFG();

    /**
     * FFC parameters
     */
    public NET_DVR_FFC_PARAM struFFCParam = new NET_DVR_FFC_PARAM();

    /**
     * DDE parameters
     */
    public NET_DVR_DDE_PARAM struDDEParam = new NET_DVR_DDE_PARAM();

    /**
     * AGC parameters
     */
    public NET_DVR_AGC_PARAM struAGCParam = new NET_DVR_AGC_PARAM();

    /**
     * Lens distortion correction: 0- off, 1- on
     */
    public byte byLensDistortionCorrection;

    /**
     * Keep
     */
    public byte[] byRes1 = new byte[3];

    /**
     * Capture CCD parameter, only for intelligent traffic camera (Capture camera)
     */
    public NET_DVR_SNAP_CAMERAPARAMCFG struSnapCCD = new NET_DVR_SNAP_CAMERAPARAMCFG();

    /**
     * Optical fog penetration parameters
     */
    public NET_DVR_OPTICAL_DEHAZE struOpticalDehaze = new NET_DVR_OPTICAL_DEHAZE();

    /**
     * Keep
     */
    public byte[] byRes2 = new byte[180];

}
