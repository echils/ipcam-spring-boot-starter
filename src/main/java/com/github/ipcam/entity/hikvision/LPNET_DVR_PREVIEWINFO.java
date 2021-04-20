package com.github.ipcam.entity.hikvision;


import com.github.ipcam.entity.comm.StructureContext;
import com.github.ipcam.entity.jnax.W32API;
import com.sun.jna.Structure;

/**
 * LPNET_DVR_PREVIEWINFO
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class LPNET_DVR_PREVIEWINFO extends Structure {

    public int lChannel;

    public int dwStreamType;

    public int dwLinkMode;

    public W32API.HWND hPlayWnd;

    public boolean bBlocked;

    public boolean bPassbackRecord;

    public byte byPreviewMode;

    public byte[] byStreamID = new byte[StructureContext.STREAM_ID_LEN];

    public byte byProtoType;

    public byte byRes1;

    public byte byVideoCodingType;

    public int dwDisplayBufNum;

    public byte[] byRes = new byte[216];

}