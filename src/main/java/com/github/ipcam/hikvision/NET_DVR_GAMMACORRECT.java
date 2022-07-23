package com.github.ipcam.hikvision;

import com.sun.jna.Structure;

/**
 * BYTE_ARRAY_STRUCTURE
 *
 * @author echils
 */
public class NET_DVR_GAMMACORRECT extends Structure {

    /**
     * Gamma correction enabled, 0- Not enabled, 1- Enabled
     */
    public byte byGammaCorrectionEnabled;

    /**
     * Gamma correction level:0-100
     */
    public byte byGammaCorrectionLevel;

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes = new byte[6];

}
