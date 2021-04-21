package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_FOCUSMODE_CFG
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_FOCUSMODE_CFG extends Structure {

    /**
     * Structure size
     */
    public int dwSize;

    public byte byFocusMode;

    public byte byAutoFocusMode;

    public short wMinFocusDistance;

    public byte byZoomSpeedLevel;

    public byte byFocusSpeedLevel;

    public byte byOpticalZoom;

    public byte byDigtitalZoom;

    public float fOpticalZoomLevel;

    public int dwFocusPos;

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes = new byte[56];

}
