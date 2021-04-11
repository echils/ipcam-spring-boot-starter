package com.github.ipcam.entity.xmeye;

import com.github.ipcam.entity.jnax.W32API;
import com.sun.jna.Structure;


/**
 * H264_DVR_CLIENTINFO
 *
 * @author echils
 * @since 2018-11-29 11:24
 */
public class H264_DVR_CLIENTINFO extends Structure {

    public int nChannel;    //通道号

    public int nStream;    //0表示主码流，为1表示子码流

    public int nMode;        //0：TCP方式,1：UDP方式,2：多播方式,3 - RTP方式，4-音视频分开(TCP)

    public int nComType;    //只对组合编码通道有效, 组合编码通道的拼图模式

    public W32API.HWND hWnd;

}
