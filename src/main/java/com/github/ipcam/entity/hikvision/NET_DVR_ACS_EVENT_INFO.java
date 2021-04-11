package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_ACS_EVENT_INFO
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_ACS_EVENT_INFO extends Structure {
    public int dwSize;
    public byte[] byCardNo = new byte[32];
    public byte byCardType;
    public byte byWhiteListNo;
    public byte byReportChannel;
    public byte byCardReaderKind;
    public int dwCardReaderNo;
    public int dwDoorNo;
    public int dwVerifyNo;
    public int dwAlarmInNo;
    public int dwAlarmOutNo;
    public int dwCaseSensorNo;
    public int dwRs485No;
    public int dwMultiCardGroupNo;
    public short wAccessChannel;
    public byte byDeviceNo;
    public byte byDistractControlNo;
    public int dwEmployeeNo;
    public short wLocalControllerID;
    public byte byInternetAccess;
    public byte byType;
    public byte[] byRes = new byte[20];
}