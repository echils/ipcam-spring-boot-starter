package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_TIME
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_TIME extends Structure {
    public int dwYear;        //年
    public int dwMonth;        //月
    public int dwDay;        //日
    public int dwHour;        //时
    public int dwMinute;        //分
    public int dwSecond;        //秒
}
