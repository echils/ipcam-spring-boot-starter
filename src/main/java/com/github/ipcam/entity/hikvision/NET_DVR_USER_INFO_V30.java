package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

import static com.github.ipcam.entity.comm.StructureContext.*;

public class NET_DVR_USER_INFO_V30 extends Structure {

    /**
     * The username
     */
    public byte[] sUserName = new byte[NAME_LEN];

    /**
     * Password (Invalid when getting, empty when setting means not changing the password
     */
    public byte[] sPassword = new byte[PASSWD_LEN];

    /**
     * Local operation permission, parameter value of 1 means enable:
     * Array 0- Local control head
     * Array 1- Local manual recording
     * Array 2- Local Playback
     * Array 3- Set parameters locally
     * Array 4- Locally view status, log
     * Array 5- Local Advanced Operations (Upgrade, Disk Management (Formatting, Setting Disk Properties, Setting Disk Group, Array Enlargement, RAID Firmware Upgrade), Reboot, Shutdown)
     * Array 6 - View parameters locally
     * Array 7 - Local management emulation and IP Camera
     * Array 8 - Local Backup
     * Array 9 - Local shutdown/restart
     */
    public byte[] byLocalRight = new byte[MAX_RIGHT];

    /**
     * Remote operation permission, parameter value of 1 means enable:
     * Array 0- Remote control head
     * Array 1- Remote manual recording
     * Array 2- Remote Playback
     * Array 3- Remote setting parameters
     * Array 4- Remote view status, log
     * Array 5- Remote Advanced Operations (Upgrade, Disk Management (Formatting, Setting Disk Properties, Setting Disk Group, Array Enlargement, RAID Firmware Upgrade), JPEG Sketch, Front Panel Lock & Unlock, Reboot, Shutdown)
     * Array 6- Remote Initiate Voice Intercom
     * Array 7- Remote Preview
     * Array 8- Remote request alarm upload, alarm output
     * Array 9- Remote control, local output
     * Array 10- Remote control serial port
     * Array 11 - View parameters remotely
     * Array 12 - Remote management of emulation and IP Camera
     * Array 13 -- Remote shutdown/restart
     */
    public byte[] byRemoteRight = new byte[MAX_RIGHT];

    /**
     * Remote Preview Channels: 1- Authorized, 0- Unauthorized
     */
    public byte[] byNetPreviewRight = new byte[MAX_CHANNUM_V30];

    /**
     * Local playback channels: 1- authorized, 0- not authorized
     */
    public byte[] byLocalPlaybackRight = new byte[MAX_CHANNUM_V30];

    /**
     * Remote playback channels: 1- authorized, 0- not authorized
     */
    public byte[] byNetPlaybackRight = new byte[MAX_CHANNUM_V30];

    /**
     * Local recording channels: 1- authorized, 0- not authorized
     */
    public byte[] byLocalRecordRight = new byte[MAX_CHANNUM_V30];

    /**
     * Remote recording channels: 1- authorized, 0- not authorized
     */
    public byte[] byNetRecordRight = new byte[MAX_CHANNUM_V30];

    /**
     * The PTZ channel can be controlled locally: 1- authorized, 0- no authorized
     */
    public byte[] byLocalPTZRight = new byte[MAX_CHANNUM_V30];

    /**
     * Remote control of PTZ channels: 1- authorized, 0- not authorized
     */
    public byte[] byNetPTZRight = new byte[MAX_CHANNUM_V30];

    /**
     * Local backup permission channels: 1- authorized, 0- no authorized
     */
    public byte[] byLocalBackupRight = new byte[MAX_CHANNUM_V30];

    /**
     * User IP address (0 means any address is allowed)
     */
    public NET_DVR_IPADDR struUserIP = new NET_DVR_IPADDR();

    /**
     * Physical address
     */
    public byte[] byMACAddr = new byte[MACADDR_LEN];

    /**
     * Priority: 0xFF - None, 0- Low, 1- Medium, 2- High
     * None (indicates that priority setting is not supported)
     * Low (Default permissions: including local and remote playback, local and remote view log and status, local and remote shutdown/restart)
     * (including local and remote control pan-head, local and remote manual recording, local and remote playback, voice intercom and remote preview, local backup, local/remote shutdown/restart)
     * Ko (Administrator)
     */
    public byte byPriority;

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes = new byte[17];

}