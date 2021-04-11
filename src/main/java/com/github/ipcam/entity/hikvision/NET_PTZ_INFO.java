package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_PTZ_INFO
 *
 * @author echils
 * @since 2019-12-31 10:16
 */
public class NET_PTZ_INFO extends Structure {

    public float fPan;
    public float fTilt;
    public float fZoom;
    public int dwFocus;
    public byte[] byRes = new byte[4];
}
