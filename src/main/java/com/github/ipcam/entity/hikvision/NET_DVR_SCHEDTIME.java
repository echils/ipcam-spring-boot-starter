package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_SCHEDTIME
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_SCHEDTIME extends Structure {
    public byte byStartHour;    //开始时间
    public byte byStartMin;
    public byte byStopHour;            //结束时间
    public byte byStopMin;
}
