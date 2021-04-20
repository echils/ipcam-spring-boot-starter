package com.github.ipcam.entity.hikvision;

import com.github.ipcam.entity.comm.StructureContext;
import com.sun.jna.Structure;

/**
 * NET_DVR_DEVICEINFO_V30
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_DEVICEINFO_V30 extends Structure {

    public byte[] sSerialNumber = new byte[StructureContext.SERIAL_LENGTH];

    public byte byAlarmInPortNum;

    public byte byAlarmOutPortNum;

    public byte byDiskNum;

    public byte byDVRType;

    public byte byChanNum;

    public byte byStartChan;

    public byte byAudioChanNum;

    public byte byStartDTalkChan;

    public byte byIPChanNum;

    public byte[] byRes1 = new byte[24];

}
