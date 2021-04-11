package com.github.ipcam.entity.xmeye;

import com.sun.jna.Structure;

/**
 * H264_DVR_TIME
 *
 * @author echils
 * @since 2018-11-29 11:46
 */
public class H264_DVR_TIME extends Structure {

    public int dwYear;        //年

    public int dwMonth;    //月

    public int dwDay;        //日

    public int dwHour;        //时

    public int dwMinute;    //分

    public int dwSecond;    //秒

}
