package com.github.ipcam.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_FOCUSMODE_CFG
 *
 * @author echils
 */
public class NET_DVR_FOCUSMODE_CFG extends Structure {

    /**
     * Structure size
     */
    public int dwSize;

    /**
     * Focus mode: 0- automatic, 1- manual, 2- semi-automatic
     */
    public byte byFocusMode;

    /**
     * Auto Focus Mode: 0-off, 1-mode A, 2-mode B, 3-mode AB, 4-mode C
     */
    public byte byAutoFocusMode;

    /**
     * Minimum focusing distance in CM: 0- auto, 0xFFFF - infinity
     */
    public short wMinFocusDistance;

    /**
     * Is the actual value, and the value range is 1~3
     */
    public byte byZoomSpeedLevel;

    /**
     * Focusing speed, is the actual value, the value range: 1~3
     */
    public byte byFocusSpeedLevel;

    /**
     * Optical doubling, value range: 0~255
     */
    public byte byOpticalZoom;

    /**
     * Numbers are doubled, with values ranging from 0 to 255
     */
    public byte byDigtitalZoom;

    /**
     * Optical magnification (magnification value), range: [1,32], minimum interval 0.5,
     * when internal device interaction *1000
     */
    public float fOpticalZoomLevel;

    /**
     * Focus value (range of value: [0x1000,0xC000], which is SONY coordinate value. This value is used for external
     * unification to ensure that the external focus values of different lenses are converted within this range
     * (application in manual focus mode).
     */
    public int dwFocusPos;

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes = new byte[56];

}
