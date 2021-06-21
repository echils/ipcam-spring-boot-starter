package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_COMPRESSIONCFG_V30
 *
 * @author echils
 */
public class NET_DVR_COMPRESSIONCFG_V30 extends Structure {

    /**
     * Structure size
     */
    public int dwSize;

    /**
     * Video stream compression parameters (i.e. the compression parameters of the main stream)
     */
    public NET_DVR_COMPRESSION_INFO_V30 struNormHighRecordPara = new NET_DVR_COMPRESSION_INFO_V30();

    /**
     * Keep it, set it to 0
     */
    public NET_DVR_COMPRESSION_INFO_V30 struRes = new NET_DVR_COMPRESSION_INFO_V30();

    /**
     * Event triggers the compression parameter. After the alarm event triggers, the main code stream switches
     * to the event compression parameter configuration
     */
    public NET_DVR_COMPRESSION_INFO_V30 struEventRecordPara = new NET_DVR_COMPRESSION_INFO_V30();

    /**
     * Network stream compression parameters (i.e., sub-stream compression parameters)
     */
    public NET_DVR_COMPRESSION_INFO_V30 struNetPara = new NET_DVR_COMPRESSION_INFO_V30();

}
