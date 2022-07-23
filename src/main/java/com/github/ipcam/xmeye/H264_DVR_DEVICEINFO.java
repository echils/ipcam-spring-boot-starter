package com.github.ipcam.xmeye;

import com.sun.jna.Structure;


/**
 * H264_DVR_DEVICEINFO
 *
 * @author echils
 */
public class H264_DVR_DEVICEINFO extends Structure {

    /**
     * Software version information
     */
    public byte[] sSoftWareVersion = new byte[64];

    /**
     * Hardware version information
     */
    public byte[] sHardWareVersion = new byte[64];

    /**
     * Encrypted version information
     */
    public byte[] sEncryptVersion = new byte[64];

    /**
     * Software creation time
     */
    public SDK_SYSTEM_TIME tmBuildTime = new SDK_SYSTEM_TIME();

    /**
     * Device serial number
     */
    public byte[] sSerialNumber = new byte[64];

    /**
     * Number of video input channels
     */
    public int byChanNum;

    /**
     * Number of video output channels
     */
    public int iVideoOutChannel;

    /**
     * Number of alarm input channels
     */
    public int byAlarmInPortNum;

    /**
     * Number of alarm output channels
     */
    public int byAlarmOutPortNum;

    /**
     * Number of intercom input channels
     */
    public int iTalkInChannel;

    /**
     * Number of intercom output channels
     */
    public int iTalkOutChannel;

    /**
     * Number of extended channels
     */
    public int iExtraChannel;

    /**
     * Number of audio input channels
     */
    public int iAudioInChannel;

    /**
     * Whether the combination code channel split mode supports switching
     */
    public int iCombineSwitch;

    /**
     * Number of digital channels
     */
    public int iDigChannel;

    /**
     * System uptime
     */
    public int uiDeviceRunTime;

    /**
     * Device type
     */
    public SDK_DEVICETYPE deviceTye = new SDK_DEVICETYPE();

    /**
     * Equipment model
     */
    public byte[] sHardWare = new byte[64];

    /**
     * Updated date
     */
    public byte[] uUpdataTime = new byte[20];

    /**
     * Update the content
     */
    public int uUpdataType;

    /**
     * Equipment model
     */
    public byte[] sDeviceModel = new byte[16];

    /**
     * National language ID,0 English 1 Chinese 2 Chinese traditional 3 Korean 4 German 5 Portuguese 6 Russian
     */
    public int nLanguage;

    /**
     * Cloud landing specific error content
     */
    public byte[] sCloudErrCode = new byte[260];

    /**
     * Proxy status
     */
    public int[] status = new int[32];

}

