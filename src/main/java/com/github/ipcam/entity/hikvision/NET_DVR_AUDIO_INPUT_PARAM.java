package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_AUDIO_INPUT_PARAM
 *
 * @author echils
 */
public class NET_DVR_AUDIO_INPUT_PARAM extends Structure {

    /**
     * Audio input type: 0-mic in, 1-line in
     */
    public byte byAudioInputType;

    /**
     * Volume, value range: [0,100]
     */
    public byte byVolume;

    /**
     * Whether to enable sound filtering: 0- off, 1- on
     */
    public byte byEnableNoiseFilter;

    /**
     * Keep
     */
    public byte[] byres = new byte[5];

}
