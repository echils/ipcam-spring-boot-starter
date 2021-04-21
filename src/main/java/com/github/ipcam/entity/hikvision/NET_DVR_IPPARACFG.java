package com.github.ipcam.entity.hikvision;

import com.github.ipcam.entity.comm.StructureContext;
import com.sun.jna.Structure;


/**
 * NET_DVR_IPPARACFG
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_IPPARACFG extends Structure {

    /**
     * Structure size
     */
    public int dwSize;

    public NET_DVR_IPDEVINFO[] struIPDevInfo = new NET_DVR_IPDEVINFO[StructureContext.MAX_IP_DEVICE];

    public byte[] byAnalogChanEnable = new byte[StructureContext.MAX_ANALOG_CHANNUM];

    public NET_DVR_IPCHANINFO[] struIPChanInfo = new NET_DVR_IPCHANINFO[StructureContext.MAX_IP_CHANNEL];

}
