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

    /**
     * [in] condition parameters (in structure or XML format), such as channel numbers, can be NULL
     */
    public Pointer lpCondBuffer;

    /**
     * [in] condition parameter buffer size
     */
    public int dwCondSize;

    /**
     * [in] input parameter (struct type), NULL when obtained, not NULL when set
     */
    public Pointer lpInBuffer;

    /**
     * [in] input parameter buffer size
     */
    public int dwInSize;

    /**
     * [out] Output parameter (structure type), not NULL when fetched, NULL when set
     */
    public Pointer lpOutBuffer;

    /**
     * [in] Output parameter buffer size
     */
    public int dwOutSize;

    /**
     * [OUT] Returns the status parameter (in XML format: responseStatus), which is not assigned if it is successfully
     * obtained and set. If not required, it can be NULL
     */
    public Pointer lpStatusBuffer;

    /**
     * [in] status parameter buffer size
     */
    public int dwStatusSize;

    /**
     * [In/Out] ByDataType = 1 valid, XML format data content
     */
    public Pointer lpXmlBuffer;

    /**
     * [in/out] lpXMLBuffer points to the size of memory: when the input and output parameters, after success will be
     * modified to return the actual length; Set to represent the actual data length [strlen((char*) lpXMLBuffer)],
     * not the total memory size
     */
    public int dwXmlSize;

    /**
     * [in/out] lpXMLBuffer points to the size of memory: when the input and output parameters, after success will
     * be modified to return the actual length; Set to represent the actual data length [strlen((char*) lpXMLBuffer)],
     * not the total memory size
     */
    public byte byDataType;

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes = new byte[23];

}
