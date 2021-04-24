package com.github.ipcam.entity.hikvision;

import com.github.ipcam.entity.comm.STRUCTURE_CONTEXT;
import com.sun.jna.Structure;

/**
 * NET_DVR_IPDEVINFO
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_IPDEVINFO extends Structure {

    /**
     * Whether the IP device is enabled
     */
    public int dwEnable;

    /**
     * The username of the device
     */
    public byte[] sUserName = new byte[STRUCTURE_CONTEXT.NAME_LEN];

    /**
     * The password of the device
     */
    public byte[] sPassword = new byte[STRUCTURE_CONTEXT.PASSWORD_LENGTH];

    /**
     * The ip of the device
     */
    public NET_DVR_IPADDR struIP = new NET_DVR_IPADDR();

    /**
     * The port of the device
     */
    public short wDVRPort;

    /**
     * Keep it, set it to 0
     */
    public byte[] byres = new byte[34];

}
