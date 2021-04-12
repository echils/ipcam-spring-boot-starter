package com.github.ipcam.entity.hikvision;

import com.github.ipcam.entity.NetworkCameraContext;
import com.sun.jna.Structure;

/**
 * NET_DVR_CHANNELSTATE_V30
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_CHANNELSTATE_V30 extends Structure {
    public byte byRecordStatic; //通道是否在录像,0-不录像,1-录像
    public byte bySignalStatic; //连接的信号状态,0-正常,1-信号丢失
    public byte byHardwareStatic;//通道硬件状态,0-正常,1-异常,例如DSP死掉
    public byte reservedData;        //保留
    public int dwBitRate;//实际码率
    public int dwLinkNum;//客户端连接的个数
    public NET_DVR_IPADDR[] struClientIP = new NET_DVR_IPADDR[NetworkCameraContext.MAX_LINK];//客户端的IP地址
    public int dwIPLinkNum;//如果该通道为IP接入，那么表示IP接入当前的连接数
    public byte[] byRes = new byte[12];
}
