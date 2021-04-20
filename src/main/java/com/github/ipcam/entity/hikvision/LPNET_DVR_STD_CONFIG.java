package com.github.ipcam.entity.hikvision;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

/**
 * LPNET_DVR_STD_CONFIG
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class LPNET_DVR_STD_CONFIG extends Structure {

    public Pointer lpCondBuffer;

    public int dwCondSize;

    public Pointer lpInBuffer;

    public int dwInSize;

    public Pointer lpOutBuffer;

    public int dwOutSize;

    public Pointer lpStatusBuffer;

    public int dwStatusSize;

    public Pointer lpXmlBuffer;

    public int dwXmlSize;

    public byte byDataType;

    public byte[] byRes = new byte[23];

}
