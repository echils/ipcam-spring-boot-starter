package com.github.ipcam.entity.hikvision;

import com.github.ipcam.entity.NetworkCameraContext;
import com.sun.jna.Structure;


/**
 * NET_DVR_IPPARACFG
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_IPPARACFG extends Structure {
    public int dwSize;                                        /* 结构大小 */
    public NET_DVR_IPDEVINFO[] struIPDevInfo = new NET_DVR_IPDEVINFO[NetworkCameraContext.MAX_IP_DEVICE];    /* IP设备 */
    public byte[] byAnalogChanEnable = new byte[NetworkCameraContext.MAX_ANALOG_CHANNUM];        /* 模拟通道是否启用，从低到高表示1-32通道，0表示无效 1有效 */
    public NET_DVR_IPCHANINFO[] struIPChanInfo = new NET_DVR_IPCHANINFO[NetworkCameraContext.MAX_IP_CHANNEL];    /* IP通道 */
}
