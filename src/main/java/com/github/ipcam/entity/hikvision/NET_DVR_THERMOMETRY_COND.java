package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_THERMOMETRY_COND
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_THERMOMETRY_COND extends Structure {

    /**
     * Structure size
     */
    public int dwSize;

    /**
     * Channel number
     */
    public int dwChannel = 2;

    /**
     * Preset point number
     */
    public short wPresetNo;

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes = new byte[62];

}
