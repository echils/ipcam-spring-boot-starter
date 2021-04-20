package com.github.ipcam.entity.comm;

import com.github.ipcam.entity.hikvision.NET_DVR_DEVICEINFO_V30;

/**
 * StructureContext
 *
 * @author echils
 * @since 2020-03-19 13:36
 */
public class StructureContext {

//    *********************** HIKVISION ***********************

    public static final int INIT = 0;
    public static final int FAILED = -1;
    public static final int COMPLETED = 100;
    public static final int STREAM_3GPP = 2;
    public static final short ACTION = 1;
    public static final byte SPLIT = 1;
    public static final short PICTURE_SIZE = 0xff;
    public static final short PICTURE_QUALITY = 0;
    public static final int DEFAULT_BUFFER = 1024;
    public static final int PTZ_BUFFER_SIZE = 64;
    public static final int NAME_LEN = 32;
    public static final int SERIALNO_LEN = 48;
    public static final int MACADDR_LEN = 6;
    public static final int MAX_NAMELEN = 16;
    public static final int PASSWORD_LENGTH = 16;
    public static final int SERIAL_LENGTH = 48;
    public static final int MAX_TIMESEGMENT_V30 = 8;
    public static final int MAX_DAYS = 7;
    public static final int MAX_LINK = 6;
    public static final int MAX_ANALOG_CHANNUM = 32;
    public static final int MAX_IP_DEVICE = 32;
    public static final int MAX_IP_CHANNEL = 32;
    public static final int NET_DVR_SET_PTZPOS = 292;
    public static final int NET_DVR_GET_PTZPOS = 293;
    public static final int NET_DVR_GET_PTZSCOPE = 294;
    public static final int NET_DVR_GET_IPPARACFG = 1048;
    public static final int NET_DVR_FILE_SUCCESS = 1000;
    public static final int NET_DVR_FILE_NOFIND = 1001;
    public static final int NET_DVR_ISFINDING = 1002;
    public static final int NET_DVR_LOCAL_CFG_TYPE_GENERAL = 17;
    public static final int NET_DVR_SYSHEAD = 1;
    public static final int NET_DVR_STREAMDATA = 2;
    public static final int NET_DVR_DEVICE_AUDIO = 1;
    public static final int NET_DVR_GET_PRESET_NAME = 3383;
    public static final int NET_DVR_SET_PRESET_NAME = 3382;
    public static final int MAX_THERMOMETRY_REGION_NUM = 40;
    public static final int VCA_MAX_POLYGON_POINT_NUM = 10;
    public static final int NET_DVR_GET_REALTIME_THERMOMETRY = 3629;
    public static final int NET_DVR_CAMERAPARAMCFG_EX_GET = 3368;
    public static final int NET_DVR_CAMERAPARAMCFG_EX_SET = 3369;
    public static final int NET_DVR_AEMODECFG_GET = 3309;
    public static final int NET_DVR_AEMODECFG_SET = 3310;
    public static final int NET_DVR_LOW_LIGHT_CFG_GET = 3303;
    public static final int NET_DVR_LOW_LIGHT_CFG_SET = 3304;
    public static final int NET_DVR_FOCUSMODE_CFG_GET = 3305;
    public static final int NET_DVR_FOCUSMODE_CFG_SET = 3306;
    public static final int NET_DVR_COMPRESSIONCFG_V30_GET = 1040;
    public static final int NET_DVR_COMPRESSIONCFG_V30_SET = 1041;
    public static final int NET_DVR_AUDIO_INPUT_PARAM_GET = 3201;
    public static final int NET_DVR_AUDIO_INPUT_PARAM_SET = 3202;
    public static final int NET_DVR_SET_PTZOSDCFG = 3273;
    public static final int NET_DVR_GET_PTZOSDCFG = 3272;
    public static final int DEFAULT_BACK_LIGHT_LEVEL = 8;
    public static final int CUSTOM = 3;
    public static final int AAC = 7;
    public static final int PCM = 8;
    public static final int AUDIO_CHANNEL = 1;
    public static final int LOGIN_DEFAULT_WAIT_TIME = 3000;
    public static final int LOGIN_DEFAULT_TRY_TIME = 3;
    public static final byte OPEN_DEFOG = 2;
    public static final byte CLOSE_DEFOG = 0;
    public static final byte OPEN_ELECTRONIC_STABILIZATION = 1;
    public static final byte CLOSE_ELECTRONIC_STABILIZATION = 0;
    public static final byte LOW_LIGHT_LIMIT_OPEN = 1;
    public static final int STREAM_ID_LEN = 32;
    public static final int MAX_RULE = 21;
    public static final int MIN_RULE = 0;
    public static final int INFRARED_POINT_NUM = 4;
    public static final int NET_DVR_GET_THERMOMETRY_PRESETINFO = 3624;
    public static final int NET_DVR_SET_THERMOMETRY_PRESETINFO = 3625;
    public static final int NET_SDK_CALLBACK_TYPE_DATA = 2;
    public static final int ISAPI_DATA_LEN = 1024 * 1024;
    public static final int ISAPI_STATUS_LEN = 4 * 4096;
    public static final String ISAPI_PTZ_PRESET = "PTZPreset";
    public static final int USER = 0;
    public static final int MIN_PASSWD_LEN = 8;
    public static final int PASSWD_LEN = 16;
    public static final int MAX_USERNUM_V30 = 32;
    public static final int MAX_RIGHT = 32;
    public static final int MAX_CHANNUM_V30 = (MAX_ANALOG_CHANNUM + MAX_IP_CHANNEL);
    public static final int NET_DVR_GET_USERCFG_V30 = 1006;
    public static final int NET_DVR_SET_USERCFG_V30 = 1007;
    public static final int DEV_TYPE_NAME_LEN = 24;
    public static final int NET_DVR_GET_DEVICECFG_V40 = 1100;


    //    *********************** XMEYE ***********************
    public static final int XMEYE_FAILED = 0;
    public static final int MEANINGLESS = 9999;
    public static final int TCPSOCKET = 0;
    public static final int NET_MAX_MAC_LEN = 32;
    public static final int E_SDK_CONFIG_MODIFY_PSW = 8;
    public static final String DEFAULT_DVR_CHANNEL = "A1";
    public static NET_DVR_DEVICEINFO_V30 deviceInfo = new NET_DVR_DEVICEINFO_V30();


}
