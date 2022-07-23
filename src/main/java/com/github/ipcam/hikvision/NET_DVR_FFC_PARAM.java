package com.github.ipcam.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_FFC_PARAM
 *
 * @author echils
 */
public class NET_DVR_FFC_PARAM extends Structure {

    /**
     * 1- Timing mode, 2- Temperature mode, 3- Off
     */
    public byte byMode;

    /**
     * Keep it, set it to 0
     */
    public byte byRes1;

    /**
     * Time (effective in timer mode), unit: minutes, the specific value is obtained through the ability set,
     * the options are: 10, 20, 30, 40, 50, 60, 120, 180, 240
     */
    public int wCompensateTime;

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes2 = new byte[4];

}
