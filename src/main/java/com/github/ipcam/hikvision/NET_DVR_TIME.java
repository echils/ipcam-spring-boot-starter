package com.github.ipcam.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_TIME
 *
 * @author echils
 */
public class NET_DVR_TIME extends Structure {

    /**
     * Year
     */
    public int dwYear;

    /**
     * Month
     */
    public int dwMonth;

    /**
     * Day
     */
    public int dwDay;

    /**
     * Hour
     */
    public int dwHour;

    /**
     * Minute
     */
    public int dwMinute;

    /**
     * Second
     */
    public int dwSecond;

}
