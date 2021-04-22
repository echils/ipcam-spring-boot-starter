package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_DAYNIGHT
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_DAYNIGHT extends Structure {

    /**
     * Switch day and night: 0- Day, 1- Night, 2- Automatic (photosensitive mode), 3- Timing, 4- Alarm input trigger,
     * 5- Automatic mode 2 (no photosensitive, i.e. use video brightness to determine the day and night mode rather
     * than the photosensitive resistance)
     */
    public byte byDayNightFilterType;

    /**
     * 0- Enable, 1- Disable. (keep)
     */
    public byte bySwitchScheduleEnabled;

    /**
     * Timing mode start time (hours), value range: 0~23
     */
    public byte byBeginTime;

    /**
     * Timing mode end time (hours), value range: 0~23
     */
    public byte byEndTime;

    /**
     * Value range of network camera: 0~7, value range of ball machine: 1~3
     */
    public byte byDayToNightFilterLevel;

    /**
     * Value range of network camera: 0~7, value range of ball machine: 1~3
     */
    public byte byNightToDayFilterLevel;

    /**
     * 60 Seconds
     */
    public byte byDayNightFilterTime;

    /**
     * Timing mode start time (minutes), value range: 0~59
     */
    public byte byBeginTimeMin;

    /**
     * Timing mode start time (seconds), value range: 0~59
     */
    public byte byBeginTimeSec;

    /**
     * Timing mode end time (minutes), value range: 0~59
     */
    public byte byEndTimeMin;

    /**
     * Timing mode end time (seconds), value range: 0~59
     */
    public byte byEndTimeSec;

    /**
     * Alarm input trigger state: 0- day, 1- night
     */
    public byte byAlarmTrigState;

}
