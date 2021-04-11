package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_TEMPERATURE_COLOR
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_TEMPERATURE_COLOR extends Structure {
    public byte byType;
    public byte[] byRes1 = new byte[3];
    public int iHighTemperature;
    public int iLowTemperature;
    public byte[] byRes = new byte[8];
}
