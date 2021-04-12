package com.github.ipcam.entity.hikvision;

import com.github.ipcam.entity.NetworkCameraContext;
import com.sun.jna.Structure;

/**
 * NET_DVR_IPDEVINFO
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_IPDEVINFO extends Structure {
    public int dwEnable;                    /* 该IP设备是否启用 */
    public byte[] sUserName = new byte[NetworkCameraContext.NAME_LEN];        /* 用户名 */
    public byte[] sPassword = new byte[NetworkCameraContext.PASSWORD_LENGTH];        /* 密码 */
    public NET_DVR_IPADDR struIP = new NET_DVR_IPADDR();            /* IP地址 */
    public short wDVRPort;                    /* 端口号 */
    public byte[] byres = new byte[34];                /* 保留 */

}
