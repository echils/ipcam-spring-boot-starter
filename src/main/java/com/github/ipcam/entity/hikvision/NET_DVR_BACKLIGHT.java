package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_BACKLIGHT
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_BACKLIGHT extends Structure {

    /**
     * Backlight compensation: 0-off, 1-up, 2-down, 3-left, 4-right, 5-middle, 6-custom, 10-open,
     * 11-auto, 12-multi-area backlight compensation
     */
    public byte byBacklightMode;

    /**
     * Backlight compensation level: 0x0~0xF
     */
    public byte byBacklightLevel;

    /**
     * Keep
     */
    public byte[] byRes1 = new byte[2];

    /**
     * X coordinate 1
     */
    public int dwPositionX1;

    /**
     * Y coordinate 1
     */
    public int dwPositionY1;

    /**
     * X coordinate 2
     */
    public int dwPositionX2;

    /**
     * Y coordinate 2
     */
    public int dwPositionY2;

    /**
     * Keep
     */
    public byte[] byRes2 = new byte[4];

}
