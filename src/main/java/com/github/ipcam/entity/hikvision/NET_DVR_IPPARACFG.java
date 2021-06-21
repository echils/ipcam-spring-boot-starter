package com.github.ipcam.entity.hikvision;

import com.github.ipcam.entity.comm.STRUCTURE_CONTEXT;
import com.sun.jna.Structure;


/**
 * NET_DVR_IPPARACFG
 *
 * @author echils
 */
public class NET_DVR_IPPARACFG extends Structure {

    /**
     * Structure size
     */
    public int dwSize;

    /**
     * IP device, subscript 0 corresponds to device IP ID 1
     */
    public NET_DVR_IPDEVINFO[] struIPDevInfo = new NET_DVR_IPDEVINFO[STRUCTURE_CONTEXT.MAX_IP_DEVICE];

    /**
     * Enable emulated channel resources: 0- disabled, 1- enabled. DS-9000 This configuration
     * will automatically restart after modification
     */
    public byte[] byAnalogChanEnable = new byte[STRUCTURE_CONTEXT.MAX_ANALOG_CHANNUM];

    /**
     * IP channel information
     */
    public NET_DVR_IPCHANINFO[] struIPChanInfo = new NET_DVR_IPCHANINFO[STRUCTURE_CONTEXT.MAX_IP_CHANNEL];

}
