package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_COMPRESSION_INFO_V30
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_COMPRESSION_INFO_V30 extends Structure {

    public byte byStreamType;

    public byte byResolution;

    public byte byBitrateType;

    public byte byPicQuality;

    public int dwVideoBitrate;

    public int dwVideoFrameRate;

    public short wIntervalFrameI;

    public byte byIntervalBPFrame;

    public byte byres1;

    public byte byVideoEncType;

    public byte byAudioEncType;

    public byte byVideoEncComplexity;

    public byte byEnableSvc;

    public byte byFormatType;

    public byte byAudioBitRate;

    public byte bySteamSmooth;

    public byte byAudioSamplingRate;

    public byte bySmartCodec;

    public byte byDepthMapEnable;

    public short wAverageVideoBitrate;

}
