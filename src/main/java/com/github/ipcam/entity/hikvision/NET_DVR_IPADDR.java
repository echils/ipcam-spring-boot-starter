package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * ByteArrayStructure
 *
 * @author echils
 * @since 2020-03-19 13:42
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
