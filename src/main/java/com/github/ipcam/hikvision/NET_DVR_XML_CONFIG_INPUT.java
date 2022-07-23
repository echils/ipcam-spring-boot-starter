package com.github.ipcam.hikvision;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

/**
 * NET_DVR_XML_CONFIG_INPUT
 *
 * @author echils
 */
public class NET_DVR_XML_CONFIG_INPUT extends Structure {

    /**
     * Structure size
     */
    public int dwSize;

    /**
     * Request signaling in string format
     */
    public Pointer lpRequestUrl;

    /**
     * Request signaling length, string length
     */
    public int dwRequestUrlLen;

    /**
     * Input parameter buffer, in XML format
     */
    public String lpInBuffer;

    /**
     * Input parameter buffer size
     */
    public int dwInBufferSize;

    /**
     * Receive timeout time in ms. If 0 is entered, the default timeout 5s will be used
     */
    public int dwRecvTimeOut = 0;

    /**
     * Whether to force encryption (when enabled, pass-through XML packets encrypt the transmission,
     * AES128 encryption algorithm) : 0- No, 1- Yes
     */
    public byte byForceEncrpt;

    /**
     * Whether to multi part
     */
    public byte byNumOfMultiPart;

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes = new byte[30];

}
