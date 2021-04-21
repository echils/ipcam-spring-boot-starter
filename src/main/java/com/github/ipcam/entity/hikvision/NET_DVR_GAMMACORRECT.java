package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * ByteArrayStructure
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_GAMMACORRECT extends Structure {

    public byte byGammaCorrectionEnabled;

    public byte byGammaCorrectionLevel;

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes = new byte[6];

}
