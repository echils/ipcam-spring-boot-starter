package com.github.ipcam.entity.hikvision;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

/**
 * NET_DVR_XML_CONFIG_OUTPUT
 *
 * @author echils
 * @since 2020-04-16 10:54
 */
public class NET_DVR_XML_CONFIG_OUTPUT extends Structure {

    /**
     * Structure size
     */
    public int dwSize;

    public Pointer lpOutBuffer;

    public int dwOutBufferSize;

    public int dwReturnedXMLSize;

    public Pointer lpStatusBuffer;

    public int dwStatusSize;

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes = new byte[32];

}