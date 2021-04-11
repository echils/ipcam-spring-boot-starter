package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * StruIOAlarm
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class StruIOAlarm extends Structure {
    public int dwAlarmInputNo;
    public int dwTrigerAlarmOutNum;
    public int dwTrigerRecordChanNum;
}