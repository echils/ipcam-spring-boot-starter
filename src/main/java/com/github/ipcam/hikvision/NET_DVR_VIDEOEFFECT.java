package com.github.ipcam.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_VIDEOEFFECT
 *
 * @author echils
 */
public class NET_DVR_VIDEOEFFECT extends Structure {

    /**
     * Brightness, value range [0,100]
     */
    public byte byBrightnessLevel;

    /**
     * Contrast, range [0,100]
     */
    public byte byContrastLevel;

    /**
     * Sharpness, value range [0,100]
     */
    public byte bySharpnessLevel;

    /**
     * Saturation, range [0,100]
     */
    public byte bySaturationLevel;

    /**
     * Color, value range [0,100], reserved
     */
    public byte byHueLevel;

    /**
     * Enable, in bits. Bit0 -SMART IR(anti-overexposure), Bit1 - low illumination, Bit2 - strong light suppression enable,
     * values: 0- no, 1- yes, for example, ByenableFunc &0x2==1 indicates enable low illumination function;
     * Bit3 - sharpness type, value: 0- automatic, 1- manual.
     */
    public byte byEnableFunc;

    /**
     * High light suppression level, value range: [1,3]
     */
    public byte byLightInhibitLevel;

    /**
     * Gray scale range :0-[0,255], 1-[16,235]
     */
    public byte byGrayLevel;

}
