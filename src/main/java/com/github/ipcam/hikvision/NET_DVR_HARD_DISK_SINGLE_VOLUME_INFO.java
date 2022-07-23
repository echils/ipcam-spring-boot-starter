package com.github.ipcam.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_HARD_DISK_SINGLE_VOLUME_INFO
 *
 * @author echils
 */
public class NET_DVR_HARD_DISK_SINGLE_VOLUME_INFO extends Structure {

    public int dwSize;

    public byte byHDVolumeNo;

    public byte byType;

    public byte[] byRes1 = new byte[2];

    public int dwCapacity;

    public int dwFreeSpace;

    public byte[] byHDVolumeName = new byte[36];

    public byte byLoopCover;

    public byte[] byRes = new byte[63];

}
