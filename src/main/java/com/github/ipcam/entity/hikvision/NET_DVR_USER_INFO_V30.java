package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

import static com.github.ipcam.entity.comm.StructureContext.*;

public class NET_DVR_USER_INFO_V30 extends Structure {

    public byte[] sUserName = new byte[NAME_LEN];

    public byte[] sPassword = new byte[PASSWD_LEN];

    public byte[] byLocalRight = new byte[MAX_RIGHT];

    public byte[] byRemoteRight = new byte[MAX_RIGHT];

    public byte[] byNetPreviewRight = new byte[MAX_CHANNUM_V30];

    public byte[] byLocalPlaybackRight = new byte[MAX_CHANNUM_V30];

    public byte[] byNetPlaybackRight = new byte[MAX_CHANNUM_V30];

    public byte[] byLocalRecordRight = new byte[MAX_CHANNUM_V30];

    public byte[] byNetRecordRight = new byte[MAX_CHANNUM_V30];

    public byte[] byLocalPTZRight = new byte[MAX_CHANNUM_V30];

    public byte[] byNetPTZRight = new byte[MAX_CHANNUM_V30];

    public byte[] byLocalBackupRight = new byte[MAX_CHANNUM_V30];

    public NET_DVR_IPADDR struUserIP = new NET_DVR_IPADDR();

    public byte[] byMACAddr = new byte[MACADDR_LEN];

    public byte byPriority;

    public byte[] byRes = new byte[17];

}