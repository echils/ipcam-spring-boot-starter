package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;


/**
 * NET_DVR_FIND_DATA
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_FIND_DATA extends Structure {

    public byte[] sFileName = new byte[100];

    public NET_DVR_TIME struStartTime;

    public NET_DVR_TIME struStopTime;

    public int dwFileSize;

}
