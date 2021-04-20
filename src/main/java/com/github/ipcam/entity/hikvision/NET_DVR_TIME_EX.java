package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_TIME_EX
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_TIME_EX extends Structure {

    public int wYear;

    public byte byMonth;

    public byte byDay;

    public byte byHour;

    public byte byMinute;

    public byte bySecond;

    public byte byRes;

}
