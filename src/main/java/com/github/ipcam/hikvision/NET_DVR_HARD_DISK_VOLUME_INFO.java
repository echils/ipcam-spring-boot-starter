package com.github.ipcam.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_HARD_DISK_VOLUME_INFO
 *
 * @author echils
 */
public class NET_DVR_HARD_DISK_VOLUME_INFO extends Structure {

    public int dwSize;

    public int dwHDVolumeCount;

    public NET_DVR_HARD_DISK_SINGLE_VOLUME_INFO[] struSingleVolumeInfo = new NET_DVR_HARD_DISK_SINGLE_VOLUME_INFO[33];

    public byte[] byRes = new byte[128];

}
