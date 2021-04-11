package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * ByteArrayStructure
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_DEVICEINFO_V30 extends Structure {

    public byte[] sSerialNumber = new byte[NetworkCameraContext.SERIAL_LENGTH];  //序列号
    public byte byAlarmInPortNum;                //报警输入个数
    public byte byAlarmOutPortNum;                //报警输出个数
    public byte byDiskNum;                    //硬盘个数
    public byte byDVRType;                    //设备类型, 1:DVR 2:ATM DVR 3:DVS ......
    public byte byChanNum;                    //模拟通道个数
    public byte byStartChan;                    //起始通道号,例如DVS-1,DVR - 1
    public byte byAudioChanNum;                //语音通道数
    public byte byStartDTalkChan;                //起始数字对讲通道
    public byte byIPChanNum;                    //最大数字通道个数
    public byte[] byRes1 = new byte[24];                    //保留
}
