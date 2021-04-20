package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;


/**
 * NET_DVR_PRESET_NAME
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_PRESET_NAME extends Structure {

    public int dwSize;

    public short wPresetNum;

    public byte[] byRes1 = new byte[2];

    public byte[] byName = new byte[32];

    public short wPanPos;

    public short wTiltPos;

    public short wZoomPos;

    public byte[] byRes = new byte[58];

}
