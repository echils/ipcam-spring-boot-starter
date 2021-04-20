package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_AUDIO_INPUT_PARAM
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_AUDIO_INPUT_PARAM extends Structure {

    public byte byAudioInputType;

    public byte byVolume;

    public byte byEnableNoiseFilter;

    public byte[] byres = new byte[5];

}
