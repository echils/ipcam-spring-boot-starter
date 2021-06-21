package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_NOISEREMOVE
 *
 * @author echils
 */
public class NET_DVR_NOISEREMOVE extends Structure {

    /**
     * Whether digital denoising is enabled, 0- not enabled, 1- normal mode digital denoising, 2- expert mode digital denoising
     */
    public byte byDigitalNoiseRemoveEnable;

    /**
     * Normal mode digital denoising level: 0x0~0xF
     */
    public byte byDigitalNoiseRemoveLevel;

    /**
     * Airspace intensity in expert mode: 0~100
     */
    public byte bySpectralLevel;

    /**
     * Time domain strength in expert mode: 0~100
     */
    public byte byTemporalLevel;

    /**
     * Catch frame 2D denoising: 0- not enabled, 1- enabled, intelligent traffic camera supported
     */
    public byte byDigitalNoiseRemove2DEnable;

    /**
     * 2D noise reduction level of capture frame, value range: 0~100, intelligent traffic camera support
     */
    public byte byDigitalNoiseRemove2DLevel;

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes = new byte[2];

}
