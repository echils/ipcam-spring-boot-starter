package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_PTZABSOLUTEEX_CFG
 *
 * @author echils
 * @since 2019-12-31 10:13
 */
public class NET_DVR_PTZABSOLUTEEX_CFG extends Structure {

    public int dwSize;
    public NET_PTZ_INFO struPTZCtrl;
    public int dwFocalLen;
    public float fHorizontalSpeed;
    public float fVerticalSpeed;
    public byte byZoomType;
    public byte[] byRes = new byte[123];
}
