package com.github.ipcam.entity.hikvision;

import com.github.ipcam.entity.comm.STRUCTURE_CONTEXT;
import com.sun.jna.Structure;

/**
 * NET_DVR_DEVICEINFO_V30
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_DEVICEINFO_V30 extends Structure {

    /**
     * The serial number
     */
    public byte[] sSerialNumber = new byte[STRUCTURE_CONTEXT.SERIAL_LENGTH];

    /**
     * Number of analog alarm inputs
     */
    public byte byAlarmInPortNum;

    /**
     * Number of analog alarm outputs
     */
    public byte byAlarmOutPortNum;

    /**
     * The number of hard disk
     */
    public byte byDiskNum;

    /**
     * For the type of equipment, see the list below
     */
    public byte byDVRType;

    /**
     * Number of analog channels of the device. The maximum number of digital
     * (IP) channels is BYIPChannum + BYHIGHDChannum *256
     */
    public byte byChanNum;

    /**
     * The start channel number of an analog channel, starting at 1. The starting channel number of the
     * digital channel is shown in the following parameter byStartdchan
     */
    public byte byStartChan;

    /**
     * Number of equipment voice intercom channels
     */
    public byte byAudioChanNum;

    /**
     * The initial digital intercom channel number, as distinct from the analog intercom channel number,
     * 0 indicates a digital-free intercom channel
     */
    public byte byStartDTalkChan;

    /**
     * Maximum number of digital channels for equipment, low 8 bits, high 8 bits see byHighDChanNum.
     * Net_dvr_getdvrconfig (configuration command NET_DVR_GET_IPPARACFG_V40) can be used to determine
     * whether to call net_dvr_getdvrrrconfig (configuration command NET_DVR_GET_IPPARACFG_V40) to obtain
     * analog and digital channel parameters (NET_DVR_IPPARACFG_V40) according to the number of IP channels.
     */
    public byte byIPChanNum;

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes1 = new byte[24];

}
