package com.github.ipcam.entity.hikvision;

import com.sun.jna.Union;

/**
 * StruAlarm
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class StruAlarm extends Union {
    public byte[] byUnionLen = new byte[128];
    public StruIOAlarm struioAlarm = new StruIOAlarm();
    public StruAlarmHardDisk strualarmHardDisk = new StruAlarmHardDisk();
    public StruAlarmChannel sstrualarmChannel = new StruAlarmChannel();
    public StruRecordingHost strurecordingHost = new StruRecordingHost();
}