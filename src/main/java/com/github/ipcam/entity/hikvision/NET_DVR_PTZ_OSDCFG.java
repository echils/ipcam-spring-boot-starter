package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_PTZ_OSDCFG
 *
 * @author echils
 * @since 2020-05-15 15:12
 */
public class NET_DVR_PTZ_OSDCFG extends Structure {

    /**
     * Structure size
     */
    public int dwSize;

    /**
     * Lens Multiple Display Time: 1-2 seconds, 2-5 seconds, 3-10 seconds,
     * 0xFF - Normally Off, 0-Normally On, Default: 2 seconds
     */
    public byte byZoomStatus;

    /**
     * Azimuth display time: 1-2 seconds, 2-5 seconds, 3-10 seconds,
     * 0xFF - normally off, 0- normally on, default: 2 seconds
     */
    public byte byPtStatus;

    /**
     * Preset point title display time: 1-2 seconds, 2-5 seconds, 3-10 seconds,
     * 0xFF - normally off, 0- normally on, default: 2 seconds
     */
    public byte byPresetStatus;

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes = new byte[125];

}
