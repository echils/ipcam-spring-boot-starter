package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

import static com.github.ipcam.entity.comm.STRUCTURE_CONTEXT.*;

/**
 * NET_DVR_DEVICECFG_V40
 *
 * @author echils
 */
public class NET_DVR_DEVICECFG_V40 extends Structure {

    /**
     * Structure size
     */
    public int dwSize;

    /**
     * Device name
     */
    public byte[] sDVRName = new byte[NAME_LEN];

    /**
     * Device ID number, for remote control, device number range (0 to 99) for v1.4,
     * device number (1 to 255) for v1.5 and above
     */
    public int dwDVRID;

    /**
     * Whether to loop video: 0- no, 1- yes
     */
    public int dwRecycleRecord;

    /**
     * Device serial number (read only, not modifiable)
     */
    public byte[] sSerialNumber = new byte[SERIALNO_LEN];

    /**
     * (Read Only, Can't Be Modified)
     * The highest 8 bits are the major version number of the device supported by V3.0 or above,
     * the next highest 8 bits are the minor version number, and the lower 16 bits are the repair version number.
     * For example, 0x05050000 represents V5.5.0;
     * The higher 16 bits represent the primary version and the lower 16 bits represent the secondary version
     * for devices supported by versions below V3.0
     */
    public int dwSoftwareVersion;

    /**
     * The software generates the date, with the higher 16 bits for the year (plus 2000), the next 8 bits for the month,
     * and the last 8 bits for the date, for example, 0x0011090E for build20170914
     */
    public int dwSoftwareBuildDate;

    /**
     * (read-only, non-modifiable) DSP software version, the high 16 bits is the main version,
     * the low 16 bits is the secondary version
     */
    public int dwDSPSoftwareVersion;

    /**
     * (read-only, non-modifiable) DSP software generates the date, the higher 16 bits represent the year (need to add 2000),
     * the second 8 bits represent the month, and the last 8 bits represent the date
     */
    public int dwDSPSoftwareBuildDate;

    /**
     * (read-only, non-modifiable) front panel version, the high 16 bits is the major version, the low 16 bits is the minor version
     */
    public int dwPanelVersion;

    /**
     * (read-only, non-modifiable) hardware version, the high 16 bits is the major version, the low 16 bits is the minor version
     */
    public int dwHardwareVersion;

    /**
     * (Read only, can not be modified) the number of device analog alarm input
     */
    public byte byAlarmInPortNum;

    /**
     * (Read only, can not be modified) the number of device analog alarm output
     */
    public byte byAlarmOutPortNum;

    /**
     * Number of serial ports in device 232 (read only, not modified)
     */
    public byte byRS232Num;

    /**
     * (read-only, unmodifiable) Number of serial ports of device 485
     */
    public byte byRS485Num;

    /**
     * Number of network ports (read only, not modifiable)
     */
    public byte byNetworkPortNum;

    /**
     * Number of hard disk controllers (read only, not modifiable)
     */
    public byte byDiskCtrlNum;

    /**
     * Number of hard disks (read only, not modifiable)
     */
    public byte byDiskNum;

    /**
     * (Read only, not modifiable) Device type, as listed below
     */
    public byte byDVRType;

    /**
     * Number of hard disks (read only, not modifiable)
     */
    public byte byChanNum;

    /**
     * (read-only, immutable) The start channel number of the simulated channel
     */
    public byte byStartChan;

    /**
     * (read only, not modifiable) the number of device decoding paths
     */
    public byte byDecordChans;

    /**
     * Number of VGA ports (read only, not modifiable)
     */
    public byte byVGANum;

    /**
     * Number of USB ports (read only, not modifiable)
     */
    public byte byUSBNum;

    /**
     * Number of auxiliary ports (read only, not modifiable)
     */
    public byte byAuxoutNum;

    /**
     * Number of speech ports (read only, not modifiable)
     */
    public byte byAudioNum;

    /**
     * (read-only, non-modifiable) Maximum digital channel low 8 bits, high 8 bits see byHighIPChanNum
     */
    public byte byIPChanNum;

    /**
     * Number of zero channel codes (read only, not modifiable)
     */
    public byte byZeroChanNum;

    /**
     * (read-only, immutable) ability. Bit and result 0 denotes unsupported, 1 denotes supported
     * BySupport & 0x1, indicating whether intelligent search is supported
     * BySupport &0x2, indicating whether backups are supported
     * BySupport &0x4 indicates whether compression parameter capability fetching is supported
     * BySupport & 0x8 indicates whether dual network cards are supported
     * BySupport & 0x10, which indicates remote SADP support
     * BySupport & 0x20, indicating support for RAID card functionality
     * BySupport & 0x40, which means IPSAN search is supported
     * BySupport & 0x80, indicating support for RTP over RTSP
     */
    public byte bySupport;

    /**
     * Default use of eSATA, 0- Default backup, 1- Default recording
     */
    public byte byEsataUseage;

    /**
     * 0- Off Plug and Play, 1- On Plug and Play
     */
    public byte byIPCPlug;

    /**
     * Storage mode: 0- set mode, 1- disk quota, 2- frame extraction mode, 3- automatic
     */
    public byte byStorageMode;

    /**
     * (read-only, immutable) Ability set expansion, bits and results: 0 for unsupported, 1 for supported
     * BySupport1&0x1, indicating whether SNMP V30 is supported
     * BySupport1 & 0X2, supports playback and download discrimination
     */
    public byte bySupport1;

    /**
     * (Read only, can not be modified) device model, see below list
     */
    public short wDevType;

    /**
     * Device model name (read only, cannot be modified)
     */
    public byte[] byDevTypeName = new byte[DEV_TYPE_NAME_LEN];

    /**
     * (read-only, immutable) Ability set expansion, bits and results: 0 for unsupported, 1 for supported
     * BySupport2&0x1, indicating whether extended OSD character overlay is supported
     * or not (terminal and snapper extension differentiating)
     */
    public byte bySupport2;

    /**
     * Number of analog alarm inputs (read only, not modifiable)
     */
    public byte byAnalogAlarmInPortNum;

    /**
     * (Read only, not modifiable) Enter start number for analog alarm
     */
    public byte byStartAlarmInNo;

    /**
     * (Read only, not modifiable) Output start number of analog alarm
     */
    public byte byStartAlarmOutNo;

    /**
     * IP alarm input start number, 0 means that the parameter is invalid
     */
    public byte byStartIPAlarmInNo;

    /**
     * IP alarm output start number, 0 means the parameter is invalid
     */
    public byte byStartIPAlarmOutNo;

    /**
     * (Read only, not modifiable) Maximum digital channel 8 bits high, 8 bits low see byIPChanNum
     */
    public byte byHighIPChanNum;

    /**
     * Whether to enable the remote boot function in the dormant state of the device: 0- Not enabled, 1- Enable
     */
    public byte byEnableRemotePowerOn;

    /**
     * Categories of devices: 0- Retention, 1-50 DVR, 51-100 DVS, 101-150 NVR, 151-200 IPC, 65534 other types
     */
    public short wDevClass;

    /**
     * Keep it, set it to 0
     */
    byte[] byRes2 = new byte[6];

}
