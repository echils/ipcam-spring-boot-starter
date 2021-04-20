package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_THERMOMETRY_COND
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_THERMOMETRY_COND extends Structure {

    public int dwSize;

    public int dwChannel = 2;

    public short wPresetNo;

    public byte[] byRes = new byte[62];

}
