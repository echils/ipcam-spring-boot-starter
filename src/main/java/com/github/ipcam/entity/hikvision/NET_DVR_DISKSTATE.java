package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_DISKSTATE
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_DISKSTATE extends Structure {

    public int dwVolume;//硬盘的容量
    public int dwFreeSpace;//硬盘的剩余空间
    public int dwHardDiskStatic; //硬盘的状态,按位:1-休眠,2-不正常,3-休眠硬盘出错
}
