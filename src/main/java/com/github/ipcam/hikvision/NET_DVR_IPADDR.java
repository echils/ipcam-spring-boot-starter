package com.github.ipcam.hikvision;

import com.sun.jna.Structure;

/**
 * BYTE_ARRAY_STRUCTURE
 *
 * @author echils
 */
public class NET_DVR_IPADDR extends Structure {

    /**
     * Ipv4 of the camera
     */
    public byte[] sIpV4 = new byte[16];

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes = new byte[128];

}
