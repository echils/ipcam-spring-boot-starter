package com.github.ipcam.entity.hikvision;

/**
 * NetworkCameraContext
 *
 * @author echils
 * @since 2020-03-19 13:36
 */
public class NetworkCameraContext {

//    *********************** HIKVISION ***********************

    public static final int INIT = 0;//初始大小
    public static final int FAILED = -1;    //失败
    public static final int COMPLETED = 100;    //完成
    public static final int STREAM_3GPP = 2;    //失败
    public static final short ACTION = 1;    //定位PTZ参数 仅在设置时有效
    public static final byte SPLIT = 1;    //不切片
    public static final short PICTURE_SIZE = 0xff;    //抓图图片尺寸
    public static final short PICTURE_QUALITY = 0;    //抓图图片质量
    public static final int DEFAULT_BUFFER = 1024;    //数据的缓冲指针
    public static final int PTZ_BUFFER_SIZE = 64;    //数据的缓冲长度
    public static final int NAME_LEN = 32;    //用户名长度
    public static final int SERIALNO_LEN = 48;   //序列号长度
    public static final int MACADDR_LEN = 6;      //mac地址长度
    public static final int COMM_ALARM_ACS = 0x5002; //报警类型--门禁主机报警信息
    public static final int MAX_NAMELEN = 16;    //DVR本地登陆名
    public static final int PASSWORD_LENGTH = 16;    //密码长度
    public static final int SERIAL_LENGTH = 48;   //序列号长度
    public static final int MAX_TIMESEGMENT_V30 = 8;    //9000设备最大时间段数
    public static final int MAX_DAYS = 7;      //每周天数
    public static final int MAX_LINK = 6;    //8000设备单通道最大视频流连接数
    public static final int MAX_ANALOG_CHANNUM = 32;    //最大32个模拟通道
    public static final int MAX_IP_DEVICE = 32;    //允许接入的最大IP设备数
    public static final int MAX_IP_CHANNEL = 32;   //允许加入的最多IP通道数
    public static final int NET_DVR_SET_PTZPOS = 292;    //云台设置PTZ位置
    public static final int NET_DVR_GET_PTZPOS = 293;        //云台获取PTZ位置
    public static final int NET_DVR_GET_PTZSCOPE = 294;//云台获取PTZ范围
    public static final int NET_DVR_GET_RECORDCFG_V30 = 1004;    //获取录像参数
    public static final int NET_DVR_SET_RECORDCFG_V30 = 1005;    //设置录像参数
    public static final int NET_DVR_GET_IPPARACFG = 1048;    //获取IP接入配置信息
    public static final int NET_DVR_FILE_SUCCESS = 1000;    //获得文件信息
    public static final int NET_DVR_FILE_NOFIND = 1001;    //没有文件
    public static final int NET_DVR_ISFINDING = 1002;//正在查找文件
    public static final int NET_DVR_LOCAL_CFG_TYPE_GENERAL = 17;//通用参数配置
    public static final int NET_DVR_SYSHEAD = 1;//系统头数据
    public static final int NET_DVR_STREAMDATA = 2;//视频流数据（包括复合流和音视频分开的视频流数据）
    public static final int NET_DVR_DEVICE_AUDIO = 1;//设备音频文件
    public static final int NET_DVR_GET_PRESET_NAME = 3383;//获取预置点名称
    public static final int NET_DVR_SET_PRESET_NAME = 3382;//设置预置点名称
    public static final int MAX_THERMOMETRY_REGION_NUM = 40;
    public static final int VCA_MAX_POLYGON_POINT_NUM = 10;
    public static final int NET_DVR_GET_REALTIME_THERMOMETRY = 3629;
    public static final int NET_DVR_GET_TEMP_HUMI_INFO = 6701;
    public static final int NET_DVR_CAMERAPARAMCFG_EX_GET = 3368;
    public static final int NET_DVR_CAMERAPARAMCFG_EX_SET = 3369;
    public static final int NET_DVR_AEMODECFG_GET = 3309;
    public static final int NET_DVR_AEMODECFG_SET = 3310;
    public static final int NET_DVR_LOW_LIGHT_CFG_GET = 3303;
    public static final int NET_DVR_LOW_LIGHT_CFG_SET = 3304;
    public static final int NET_DVR_COMPRESSIONCFG_V30_GET = 1040;
    public static final int NET_DVR_COMPRESSIONCFG_V30_SET = 1041;
    public static final int NET_DVR_AUDIO_INPUT_PARAM_GET = 3201;
    public static final int NET_DVR_AUDIO_INPUT_PARAM_SET = 3202;
    public static final int DEFAULT_BACK_LIGHT_LEVEL = 8;
    public static final int CUSTOM = 3;
    public static final int AAC = 7;
    public static final int PCM = 8;
    public static final int AUDIO_CHANNEL = 1;
    public static final int LOGIN_DEFAULT_WAIT_TIME = 3000;
    public static final int LOGIN_DEFAULT_TRY_TIME = 3;
    public static final byte OPEN_DEFOG = 2;
    public static final byte CLOSE_DEFOG = 0;
    public static final byte LIGHT_INHIBITION_DEFAULT_LEVEL = 2;
    public static final byte OPEN_ELECTRONIC_STABILIZATION = 1;
    public static final byte CLOSE_ELECTRONIC_STABILIZATION = 0;
    public static final byte LOW_LIGHT_LIMIT_OPEN = 1;
    public static final int STREAM_ID_LEN = 32;
    public static final int MAX_RULE = 21;//远程获取多码流压缩参数
    public static final int MIN_RULE = 0;//远程设置多码流压缩参数
    public static final int INFRARED_POINT_NUM = 4;//远程设置多码流压缩参数
    public static final int NET_DVR_GET_PTZABSOLUTEEX = 6696;
    public static final int NET_DVR_GET_THERMOMETRY_PRESETINFO = 3624;
    public static final int NET_DVR_SET_THERMOMETRY_PRESETINFO = 3625;
    public static final int NET_SDK_CALLBACK_TYPE_DATA = 2;
    public static final int ISAPI_DATA_LEN = 1024 * 1024;
    public static final int ISAPI_STATUS_LEN = 4 * 4096;
    public static final String ISAPI_PTZ_PRESET = "PTZPreset";
    public static final int USER = 0;    /*无意义*/


    //    *********************** XMEYE ***********************
    public static final int XMEYE_FAILED = 0;    /*无意义*/
    public static final int MEANINGLESS = 9999;    /*无意义*/
    public static final int NET_MAX_PATH_LENGTH = 260;            //路径长度
    public static final int TCPSOCKET = 0;            //最多日志条数
    public static NET_DVR_DEVICEINFO_V30 deviceInfo = new NET_DVR_DEVICEINFO_V30();


}
