package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

import static com.github.ipcam.entity.comm.StructureContext.*;

/**
 * NET_DVR_DEVICECFG_V40
 *
 * @author echils
 * @since 2020-05-08 14:21
 */
public class NET_DVR_DEVICECFG_V40 extends Structure {

    public int dwSize;

    public byte[] sDVRName = new byte[NAME_LEN];

    public int dwDVRID;

    public int dwRecycleRecord;

    public byte[] sSerialNumber = new byte[SERIALNO_LEN];

    public int dwSoftwareVersion;

    public int dwSoftwareBuildDate;

    public int dwDSPSoftwareVersion;

    public int dwDSPSoftwareBuildDate;

    public int dwPanelVersion;

    public int dwHardwareVersion;

    public byte byAlarmInPortNum;

    public byte byAlarmOutPortNum;

    public byte byRS232Num;

    public byte byRS485Num;

    public byte byNetworkPortNum;

    public byte byDiskCtrlNum;

    public byte byDiskNum;

    public byte byDVRType;

    public byte byChanNum;

    public byte byStartChan;

    public byte byDecordChans;

    public byte byVGANum;

    public byte byUSBNum;

    public byte byAuxoutNum;

    public byte byAudioNum;

    public byte byIPChanNum;

    public byte byZeroChanNum;

    public byte bySupport;

    public byte byEsataUseage;

    public byte byIPCPlug;

    public byte byStorageMode;

    public byte bySupport1;

    public short wDevType;

    public byte[] byDevTypeName = new byte[DEV_TYPE_NAME_LEN];

    public byte bySupport2;

    public byte byAnalogAlarmInPortNum;

    public byte byStartAlarmInNo;

    public byte byStartAlarmOutNo;

    public byte byStartIPAlarmInNo;

    public byte byStartIPAlarmOutNo;

    public byte byHighIPChanNum;

    public byte byEnableRemotePowerOn;

    public short wDevClass;

    byte[] byRes2 = new byte[6];

}
