package com.github.ipcam.entity.hikvision;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

/**
 * NET_DVR_XML_CONFIG_INPUT
 *
 * @author echils
 * @since 2020-04-16 10:51
 */
public class NET_DVR_XML_CONFIG_INPUT extends Structure {

    public int dwSize;

    public Pointer lpRequestUrl;

    public int dwRequestUrlLen;

    public Pointer lpInBuffer;

    public int dwInBufferSize;

    public int dwRecvTimeOut;

    public byte byForceEncrpt;

    public byte byNumOfMultiPart;

    public byte[] byRes = new byte[30];

    public NET_DVR_XML_CONFIG_INPUT() {
    }
}
