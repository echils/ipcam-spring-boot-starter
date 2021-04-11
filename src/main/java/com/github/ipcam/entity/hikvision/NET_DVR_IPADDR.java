package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * ByteArrayStructure
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_IPADDR extends Structure {

    public byte[] sIpV4 = new byte[16];
    public byte[] byRes = new byte[128];

}
