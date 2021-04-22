package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_PTZSCOPE
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_PTZSCOPE extends Structure {

    /**
     * Minimum value of P parameter (horizontal parameter)
     */
    public short panPosMin;

    /**
     * Maximum value of P parameter (horizontal parameter)
     */
    public short panPosMax;

    /**
     * Minimum value of the T parameter (vertical parameter)
     */
    public short tiltPosMin;

    /**
     * Maximum value of the T parameter (vertical parameter)
     */
    public short tiltPosMax;

    /**
     * Minimum value of the Z parameter (multiplier parameter)
     */
    public short zoomPosMin;

    /**
     * Maximum value of the Z parameter (multiplier parameter)
     */
    public short zoomPosMax;

}
