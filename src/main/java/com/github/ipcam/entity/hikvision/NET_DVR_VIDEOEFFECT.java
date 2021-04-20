package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_VIDEOEFFECT
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_VIDEOEFFECT extends Structure {

    public byte byBrightnessLevel;

    public byte byContrastLevel;

    public byte bySharpnessLevel;

    public byte bySaturationLevel;

    public byte byHueLevel;

    public byte byEnableFunc;

    public byte byLightInhibitLevel;

    public byte byGrayLevel;

}
