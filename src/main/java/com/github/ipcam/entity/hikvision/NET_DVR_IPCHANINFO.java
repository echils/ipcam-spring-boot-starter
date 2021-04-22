package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * ByteArrayStructure
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_IPCHANINFO extends Structure {

    /**
     * IP channel online status is a read-only property. 0 means that the digital channel of an HDVR or NVR device
     * fails to connect to the corresponding IP device, and the channel is not online; 1 means the connection was
     * successful and the channel is online
     */
    public byte byEnable;

    /**
     * The low 8 bits of the IP device ID, byIPID = iDevID % 256
     */
    public byte byIPID;

    /**
     * If the channel number of the IP device (e.g., IP channel 01 on device A (HDVR or NVR device)
     * corresponds to channel 04 on device B, then byChannel=4.
     */
    public byte bychannel;

    /**
     * Keep it, set it to 0
     */
    public byte[] byres = new byte[33];

}
