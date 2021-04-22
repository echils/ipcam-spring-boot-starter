package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_TIME_EX
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_TIME_EX extends Structure {

    /**
     * Year
     */
    public int wYear;

    /**
     * Month
     */
    public byte byMonth;

    /**
     * Day
     */
    public byte byDay;

    /**
     * Hour
     */
    public byte byHour;

    /**
     * Minute
     */
    public byte byMinute;

    /**
     * Second
     */
    public byte bySecond;

    /**
     * Keep it, set it to 0
     */
    public byte byRes;

}
