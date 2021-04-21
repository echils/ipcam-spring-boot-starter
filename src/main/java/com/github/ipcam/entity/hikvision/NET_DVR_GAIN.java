package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_GAIN
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_GAIN extends Structure {

    public byte byGainLevel;

    public byte byGainUserSet;

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes = new byte[2];

    public int dwMaxGainValue;

}
