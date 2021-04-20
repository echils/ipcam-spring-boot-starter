package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * ByteArrayStructure
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_IPCHANINFO extends Structure {

    public byte byEnable;

    public byte byIPID;

    public byte bychannel;

    public byte[] byres = new byte[33];

}
