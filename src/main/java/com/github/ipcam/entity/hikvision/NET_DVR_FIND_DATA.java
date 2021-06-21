package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;


/**
 * NET_DVR_FIND_DATA
 *
 * @author echils
 */
public class NET_DVR_FIND_DATA extends Structure {

    /**
     * The file name
     */
    public byte[] sFileName = new byte[100];

    /**
     * The start time of the file
     */
    public NET_DVR_TIME struStartTime;

    /**
     * The end time of the file
     */
    public NET_DVR_TIME struStopTime;

    /**
     * The size of the file
     */
    public int dwFileSize;

}
