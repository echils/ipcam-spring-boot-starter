package com.github.ipcam.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_GAIN
 *
 * @author echils
 */
public class NET_DVR_GAIN extends Structure {

    /**
     * Gain, in dB, range [0,100]
     */
    public byte byGainLevel;

    /**
     * The user-defined gain, in unit dB, is within the value range [0,100]. For the intelligent traffic camera,
     * it is the capture gain in CCD mode
     */
    public byte byGainUserSet;

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes = new byte[2];

    /**
     * Maximum gain value, in dB
     */
    public int dwMaxGainValue;

}
