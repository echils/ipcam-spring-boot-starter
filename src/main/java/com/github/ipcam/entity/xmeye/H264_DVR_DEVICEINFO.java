package com.github.ipcam.entity.xmeye;

import com.sun.jna.Structure;


/**
 * H264_DVR_DEVICEINFO
 *
 * @author echils
 * @since 2018-11-29 10:42
 */
public class H264_DVR_DEVICEINFO extends Structure {

    public byte[] sSoftWareVersion = new byte[64];

    public byte[] sHardWareVersion = new byte[64];

    public byte[] sEncryptVersion = new byte[64];

    public SDK_SYSTEM_TIME tmBuildTime = new SDK_SYSTEM_TIME();

    public byte[] sSerialNumber = new byte[64];

    public int byChanNum;

    public int iVideoOutChannel;

    public int byAlarmInPortNum;

    public int byAlarmOutPortNum;

    public int iTalkInChannel;

    public int iTalkOutChannel;

    public int iExtraChannel;

    public int iAudioInChannel;

    public int iCombineSwitch;

    public int iDigChannel;

    public int uiDeviceRunTime;

    public SDK_DEVICETYPE deviceTye = new SDK_DEVICETYPE();

    public byte[] sHardWare = new byte[64];

    public byte[] uUpdataTime = new byte[20];

    public int uUpdataType;

    public byte[] sDeviceModel = new byte[16];

    public int nLanguage;

    public byte[] sCloudErrCode = new byte[260];

    public int[] status = new int[32];

}

