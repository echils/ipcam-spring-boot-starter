package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_BACKLIGHT
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_BACKLIGHT extends Structure {
    public byte byBacklightMode;
    public byte byBacklightLevel;
    public byte[] byRes1 = new byte[2];
    public int dwPositionX1;
    public int dwPositionY1;
    public int dwPositionX2;
    public int dwPositionY2;
    public byte[] byRes2 = new byte[4];
}
