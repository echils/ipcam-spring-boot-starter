package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_NOISEREMOVE
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_NOISEREMOVE extends Structure {

    public byte byDigitalNoiseRemoveEnable;

    public byte byDigitalNoiseRemoveLevel;

    public byte bySpectralLevel;

    public byte byTemporalLevel;

    public byte byDigitalNoiseRemove2DEnable;

    public byte byDigitalNoiseRemove2DLevel;

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes = new byte[2];

}
