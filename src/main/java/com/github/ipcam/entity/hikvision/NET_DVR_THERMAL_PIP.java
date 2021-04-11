package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_THERMAL_PIP
 *
 * @author echils
 * @since 2020-01-03 13:41
 */
public class NET_DVR_THERMAL_PIP extends Structure {
    public int dwSize;
    public byte byEnable;
    public byte byPipMode;
    public byte byOverlapType;
    public byte byTransparency;
    public NET_VCA_POLYGON struPipRegion;
    public byte byImageFusionRatio;
    public byte byBorderFusionRatio;
    public byte[] byRes = new byte[638];
}
