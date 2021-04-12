package com.github.ipcam.entity.xmeye;

import com.sun.jna.Structure;

/**
 * SDK_SYSTEM_TIME
 *
 * @author echils
 * @since 2018-11-29 10:56
 */
public class SDK_SYSTEM_TIME extends Structure {

    public int year;

    public int month;

    public int day;

    public int wday;

    public int hour;

    public int minute;

    public int second;

    public int isdst;

}
