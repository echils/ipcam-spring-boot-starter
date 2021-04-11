package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_COMPRESSIONCFG_V30
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_COMPRESSIONCFG_V30 extends Structure {

    public int dwSize;
    public NET_DVR_COMPRESSION_INFO_V30 struNormHighRecordPara = new NET_DVR_COMPRESSION_INFO_V30();
    public NET_DVR_COMPRESSION_INFO_V30 struRes = new NET_DVR_COMPRESSION_INFO_V30();
    public NET_DVR_COMPRESSION_INFO_V30 struEventRecordPara = new NET_DVR_COMPRESSION_INFO_V30();
    public NET_DVR_COMPRESSION_INFO_V30 struNetPara = new NET_DVR_COMPRESSION_INFO_V30();

}
