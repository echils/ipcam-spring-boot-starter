package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;


/**
 * NET_DVR_FIND_DATA
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_FIND_DATA extends Structure {

    public byte[] sFileName = new byte[100];//文件名
    public NET_DVR_TIME struStartTime;//文件的开始时间
    public NET_DVR_TIME struStopTime;//文件的结束时间
    public int dwFileSize;//文件的大小

}
