package com.github.ipcam.entity.hikvision;

import com.github.ipcam.entity.NetworkCameraContext;
import com.sun.jna.Structure;

/**
 * NET_DVR_ALARMER
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_ALARMER extends Structure {
    public byte byUserIDValid;                 /* userid是否有效 0-无效，1-有效 */
    public byte bySerialValid;                 /* 序列号是否有效 0-无效，1-有效 */
    public byte byVersionValid;                /* 版本号是否有效 0-无效，1-有效 */
    public byte byDeviceNameValid;             /* 设备名字是否有效 0-无效，1-有效 */
    public byte byMacAddrValid;                /* MAC地址是否有效 0-无效，1-有效 */
    public byte byLinkPortValid;               /* login端口是否有效 0-无效，1-有效 */
    public byte byDeviceIPValid;               /* 设备IP是否有效 0-无效，1-有效 */
    public byte bySocketIPValid;               /* socket ip是否有效 0-无效，1-有效 */
    public int lUserID;                       /* NET_DVR_Login()返回值, 布防时有效 */
    public byte[] sSerialNumber = new byte[NetworkCameraContext.SERIALNO_LEN];    /* 序列号 */
    public int dwDeviceVersion;                /* 版本信息 高16位表示主版本，低16位表示次版本*/
    public byte[] sDeviceName = new byte[NetworkCameraContext.NAME_LEN];            /* 设备名字 */
    public byte[] byMacAddr = new byte[NetworkCameraContext.MACADDR_LEN];        /* MAC地址 */
    public short wLinkPort;                     /* link port */
    public byte[] sDeviceIP = new byte[128];                /* IP地址 */
    public byte[] sSocketIP = new byte[128];                /* 报警主动上传时的socket IP地址 */
    public byte byIpProtocol;                  /* Ip协议 0-IPV4, 1-IPV6 */
    public byte[] byRes2 = new byte[11];
}