package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_LOCAL_GENERAL_CFG
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_LOCAL_GENERAL_CFG extends Structure {

    public byte byExceptionCbDirectly;

    public byte byNotSplitRecordFile;

    public byte[] bytes = new byte[6];

    public int i64FileSize;

    public byte[] byRes1 = new byte[240];

}
