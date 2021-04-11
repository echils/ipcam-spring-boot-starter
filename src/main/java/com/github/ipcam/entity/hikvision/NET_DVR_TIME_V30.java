package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_TIME_V30
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_TIME_V30 extends Structure {

    public short wYear;
    public byte byMonth;
    public byte byDay;
    public byte byHour;
    public byte byMinute;
    public byte bySecond;
    public byte byRes;
    public short wMilliSec;
    public byte[] byRes1 = new byte[2];
}