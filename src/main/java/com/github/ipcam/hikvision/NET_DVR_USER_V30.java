package com.github.ipcam.hikvision;

import com.sun.jna.Structure;

import static com.github.ipcam.entity.comm.STRUCTURE_CONTEXT.MAX_USERNUM_V30;

public class NET_DVR_USER_V30 extends Structure {

    /**
     * Structure size
     */
    public int dwSize;

    /**
     * User information parameter
     */
    public NET_DVR_USER_INFO_V30[] struUser = new NET_DVR_USER_INFO_V30[MAX_USERNUM_V30];

}