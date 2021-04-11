package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_DAYNIGHT
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_DAYNIGHT extends Structure {
    public byte byDayNightFilterType;
    public byte bySwitchScheduleEnabled;
    public byte byBeginTime;
    public byte byEndTime;
    public byte byDayToNightFilterLevel;
    public byte byNightToDayFilterLevel;
    public byte byDayNightFilterTime;
    public byte byBeginTimeMin;
    public byte byBeginTimeSec;
    public byte byEndTimeMin;
    public byte byEndTimeSec;
    public byte byAlarmTrigState;

}
