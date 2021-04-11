package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * StruRecordingHost
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class StruRecordingHost extends Structure {
    public byte bySubAlarmType;
    public byte[] byRes1 = new byte[3];
    public NET_DVR_TIME_EX struRecordEndTime = new NET_DVR_TIME_EX();
    public byte[] byRes = new byte[116];
}