package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_COMPRESSION_AUDIO
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_COMPRESSION_AUDIO extends Structure {
    public byte byAudioEncType;
    public byte byAudioSamplingRate;
    public byte byAudioBitRate;
    public byte[] byres = new byte[4];
    public byte bySupport;
}
