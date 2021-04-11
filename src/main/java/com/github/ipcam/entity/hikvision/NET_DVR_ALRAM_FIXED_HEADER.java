package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_ALRAM_FIXED_HEADER
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_ALRAM_FIXED_HEADER extends Structure {
    public int dwAlarmType;
    public NET_DVR_TIME_EX struAlarmTime = new NET_DVR_TIME_EX();
    public StruAlarm struAlarm = new StruAlarm();
}