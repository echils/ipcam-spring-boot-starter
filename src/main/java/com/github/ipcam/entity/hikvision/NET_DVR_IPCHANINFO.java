package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * ByteArrayStructure
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_IPCHANINFO extends Structure {
    public byte byEnable;                    /* 该通道是否启用 */
    public byte byIPID;                    /* IP设备ID 取值1- MAX_IP_DEVICE */
    public byte bychannel;                    /* 通道号 */
    public byte[] byres = new byte[33];                    /* 保留 */
}
