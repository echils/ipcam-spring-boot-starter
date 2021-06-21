package com.github.ipcam.entity.xmeye;

import com.sun.jna.Structure;

/**
 * SDK_SYSTEM_TIME
 *
 * @author echils
 */
public class SDK_SYSTEM_TIME extends Structure {

    /**
     * Year
     */
    public int year;

    /**
     * Month
     */
    public int month;

    /**
     * Day
     */
    public int day;

    /**
     * Week
     */
    public int wday;

    /**
     * Hour
     */
    public int hour;

    /**
     * Minute
     */
    public int minute;

    /**
     * Second
     */
    public int second;

    /**
     * Daylight saving time marker
     */
    public int isdst;

}
