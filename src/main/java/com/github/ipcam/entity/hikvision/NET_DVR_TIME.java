package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_TIME
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_TIME extends Structure {

    public int dwYear;

    public int dwMonth;

    public int dwDay;

    public int dwHour;

    public int dwMinute;

    public int dwSecond;

}
