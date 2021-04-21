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

    public byte byZoomStatus;

    public byte byPtStatus;

    public byte byPresetStatus;

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes = new byte[125];

}
