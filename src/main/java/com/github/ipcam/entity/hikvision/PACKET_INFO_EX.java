package com.github.ipcam.entity.hikvision;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

/**
 * PACKET_INFO_EX
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class PACKET_INFO_EX extends Structure {

    public int nPacketType;            // 包类型,见MEDIA_PACK_TYPE
    public Pointer pPacketBuffer;        // 缓存区地址
    public int dwPacketSize;        // 包的大小
    public int nEncodeType;         ///数据格式类型见SDK_ENCODE_TYPE
    // 绝对时标
    public int nYear;                // 时标:年
    public int nMonth;                // 时标:月
    public int nDay;                // 时标:日
    public int nHour;                // 时标:时
    public int nMinute;                // 时标:分
    public int nSecond;                // 时标:秒
    public int dwTimeStamp;            // 相对时标低位，单位为毫秒
    public int dwTimeStampHigh;        //相对时标高位，单位为毫秒
    public int dwFrameNum;          //帧序号
    public int dwFrameRate;         //帧率
    public short uWidth;              //图像宽度
    public short uHeight;             //图像高度
    public int[] Reserved = new int[6];        //保留
}
