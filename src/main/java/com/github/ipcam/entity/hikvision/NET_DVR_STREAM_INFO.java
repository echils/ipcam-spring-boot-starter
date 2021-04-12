package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

import static com.github.ipcam.entity.NetworkCameraContext.STREAM_ID_LEN;


/**
 * ByteArrayStructure
 *
 * @author echils
 * @since 2020-03-19 13:42
 */

public class NET_DVR_STREAM_INFO extends Structure {
    public int dwSize;
    public byte[] byID = new byte[STREAM_ID_LEN];
    public int dwChannel;
    public byte[] byRes = new byte[32];
}