package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_COMPRESSION_AUDIO
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_COMPRESSION_AUDIO extends Structure {

    /**
     * Audio Coding Type: 0-G722, 1-G711_U, 2-G711_A, 5-MP2L2, 6-G726, 7-AAC, 8-PCM, 9-G722, 10-G723, 11-G729
     */
    public byte byAudioEncType;

    /**
     * Audio Sampling Rate: 0-Default, 1-16kHz, 2-32kHz, 3-48kHz, 4-44.1kHz, 5-8kHz
     */
    public byte byAudioSamplingRate;

    /**
     * Audio bit rate
     */
    public byte byAudioBitRate;

    /**
     * Keep it, set it to 0
     */
    public byte[] byres = new byte[4];

    /**
     * The meaning of the first 4 bytes of MP2L2 indicates the length of the audio data of the following content
     */
    public byte bySupport;

}
