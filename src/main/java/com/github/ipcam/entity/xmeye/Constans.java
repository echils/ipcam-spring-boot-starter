package com.github.ipcam.entity.xmeye;

/**
 * 原生常量
 *
 * @author echils
 * @since 2018-11-29 10:47
 */
public class Constans {


    public static final int USER = 0;    /*无意义*/
    public static final int FAILED = 0;    /*无意义*/
    public static final int CHANNEL = 0;    /*无意义*/
    public static final int MEANINGLESS = 9999;    /*无意义*/
    public static final int PAN_AUTO = 29;    /* 云台以SS的速度左右自动扫描 */
    public static final int EXTPTZ_FASTGOTO = 30;    //三维定位


    public static final int NET_MAX_CHANNUM = 64;            //最大通道个数
    public static final int NET_DECORDR_CH = 64;          //最大解码通道个数
    public static final int NET_MAX_USER_NUM = 60;            //最多用户数
    public static final int NET_MAX_RIGTH_NUM = 200;            //最多权限数
    public static final int NET_MAX_GROUP_NUM = 50;            //最多组数
    public static final int NET_MAX_USER_LENGTH = 32;            //用户名密码最大长度
    public static final int NET_MAX_COMBINE_NUM = 2;            //最大组合编码通道数
    public static final int NET_MAX_DECORDR_CH = 64;          //最大解码通道个数

    public static final int NET_MAX_DDNS_TYPE = 5;            //支持的DDNS种类
    public static final int NET_MAX_ARSP_TYPE = 5;
    public static final int NET_MAX_ALARMSERVER_TYPE = 5;           //支持报警中心种类
    public static final int NET_MAX_SYSFUNC = 20;            //最多系统功能个数
    public static final int NET_MAX_PTZ_PROTOCOL_LENGTH = 32;            //云台协议名称最大长度
    public static final int NET_N_WEEKS = 7;            //星期数
    public static final int NET_N_TSECT = 6;            //时间段数
    public static final int NET_MD_REGION_ROW = 32;            //动态检测区域行数
    public static final int NET_COVERNUM = 8;             //覆盖区域数
    public static final int NET_MAX_FILTERIP_NUM = 64;            //IP地址最大过滤数
    public static final int NET_NAME_PASSWORD_LEN = 64;            //用户名密码最大长度
    public static final int NET_MAX_PATH_LENGTH = 260;            //路径长度
    public static final int NET_N_MIN_TSECT = 2;
    public static final int NET_MAX_RETURNED_LOGLIST = 128;            //最多日志条数
    public static final int NET_MAX_MAC_LEN = 32;          //MAC地址字符最大长度
    public static final int NET_IW_ENCODING_TOKEN_MAX = 128;
    public static final int NET_MAX_AP_NUMBER = 32;          //SID最大数量，暂定10
    public static final int NET_MAX_INFO_LEN = 128;
    public static final int NET_MAX_USERNAME_LENGTH = 128;
    public static final int NET_MAX_SERIALNO_LENGTH = 128;         //最大解码通道个数
    public static final int NET_CHANNEL_NAME_MAX_LEN = 64;            //通道名称最大长度
    public static final int NET_MAX_LINE_PER_OSD_AREA = 12;            //编码中一个OSD区域最多可以显示的字符行数

    //DDNS参数
    public static final int DDNS_MAX_DDNS_NAMELEN = 64;        //主机名长度
    public static final int DDNS_MAX_DDNS_PWDLEN = 32;        //密码长度
    public static final int DDNS_MAX_DDNS_IPSIZE = 64;        //IP地址长度
    public static final int DDNS_MAX_DDNS_IDSIZE = 32;        //设备标识长度
    public static final int DDNS_MAX_SERIALINFO_SIZE = 16;            //序列号以及用户名长度

    public static final int NET_MAX_MSK_SIZE = 128;            //掩码数组的大小


    //摄象机参数
    public static final int CAMERAPARA_MAXNUM = 16;            //曝光能力中目前最大长度
    //短信最大数量
    public static final int NET_MAX_RECIVE_MSG_PHONE_COUNT = 3;        ///<最大发生短信数量

    //VGA分辨率
    public static final int VGA_MAXNUM = 32;                    //分辨率 种类
    public static final int VGA_NAME_LENGTH = 10;                    //分辨率 长度

    //显示的设备列表(设备端搜索)
    public static final int DEV_LIST_SHOW = 256;

    //IP SIZE
    public static final int IP_SIZE = 16;
    public static final int MAX_HVR_CHNCAP = 16;


    //通道模式
    public static final int MAX_HVR_CHNCAP_CHN = 32;
    public static final int NET_NAME_TOKEN_LEN = 128;
    public static final int NET_NAME_URL_LEN = 128;
    public static final int NET_NAME_ID_LEN = 64;
    public static final int NET_MAX_POS_FUNC_KEYWORDS = 4; //POS功能中最多支持的关键字个数
    public static final int NET_MAX_TITLE_DOT_BUF_LEN = 64 * 24 * 24;
    //硬盘信息
    public static final int NET_FSLEN = 8;    //从IConstraint ::FS_LEN拷过来

    //车牌图片信息大小
    public static final int MAX_PLATE_NUM = 7;
    public static final int LPR_MAX_LED_CONTENT = 120;
    public static final int LPR_MAX_LED_COUNT = 8;

    //地址大小和pin码大小
    public static final int NAME_TOKEN_LEN = 128;
    public static final int NAME_URL_LEN = 128;

    public static final int E_SDK_CONFIG_MODIFY_PSW = 8;
    public static final int E_SDK_CONFIG_NET_DHCP = 37;

    public static final int UPGRADE_SUCCESS = 1;
    public static final int UPGRADE_RUNNING = 2;
    public static final int UPGRADE_FAILED = 3;

}
