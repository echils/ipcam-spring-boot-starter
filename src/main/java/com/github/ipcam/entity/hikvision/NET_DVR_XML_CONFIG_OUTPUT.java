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

    /**
     * [OUT] Output parameter buffer, XML format, request signaling is of type "GET" the application layer
     * needs to allocate enough memory in advance
     */
    public Pointer lpOutBuffer;

    /**
     * [in] output parameter buffer size (memory size)
     */
    public int dwOutBufferSize;

    /**
     * [out] Size of the actual output XML content
     */
    public int dwReturnedXMLSize;

    /**
     * [out] Returns the status parameter (in XML format: responseStatus), which is not assigned on success of the command.
     * If not required, it can be NULL
     */
    public Pointer lpStatusBuffer;

    /**
     * State buffer size (memory size)
     */
    public int dwStatusSize;

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes = new byte[32];

}
