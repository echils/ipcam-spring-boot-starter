package com.github.ipcam.entity.xmeye;

import com.sun.jna.Structure;

import static com.github.ipcam.entity.xmeye.Constans.NET_MAX_PATH_LENGTH;


/**
 * H264_DVR_DEVICEINFO
 *
 * @author echils
 * @since 2018-11-29 10:42
 */
public class H264_DVR_DEVICEINFO extends Structure {

    public byte[] sSoftWareVersion = new byte[64];    ///< 软件版本信息

    public byte[] sHardWareVersion = new byte[64];    ///< 硬件版本信息

    public byte[] sEncryptVersion = new byte[64];     ///< 加密版本信息

    public SDK_SYSTEM_TIME tmBuildTime = new SDK_SYSTEM_TIME(); ///< 软件创建时间

    public byte[] sSerialNumber = new byte[64];        ///< 设备序列号

    public int byChanNum;                ///< 视频输入通道数

    public int iVideoOutChannel;        ///< 视频输出通道数

    public int byAlarmInPortNum;        ///< 报警输入通道数

    public int byAlarmOutPortNum;        ///< 报警输出通道数

    public int iTalkInChannel;            ///< 对讲输入通道数

    public int iTalkOutChannel;        ///< 对讲输出通道数

    public int iExtraChannel;            ///< 扩展通道数

    public int iAudioInChannel;        ///< 音频输入通道数

    public int iCombineSwitch;            ///< 组合编码通道分割模式是否支持切换

    public int iDigChannel;            ///<数字通道数

    public int uiDeviceRunTime;///<系统运行时间

    public SDK_DEVICETYPE deviceTye = new SDK_DEVICETYPE();    ///设备类型

    public byte[] sHardWare = new byte[64];            ///<设备型号

    public byte[] uUpdataTime = new byte[20];        ///<更新日期 例如 2013-09-03 14:15:13

    public int uUpdataType;    ///<更新内容

    public byte[] sDeviceModel = new byte[16];       //设备型号(底层库从加密里获得，sHardWare针对多个设备用同一个程序这种情况区分不了)

    public int nLanguage;//国家的语言ID,0英语 1中文 2中文繁体 3韩语 4德语 5葡萄牙语 6俄语

    public byte[] sCloudErrCode = new byte[NET_MAX_PATH_LENGTH];//云登陆具体错误内容

    public int[] status = new int[32];

}

