package com.github.ipcam.entity.comm;

import com.sun.jna.Structure;

/**
 * BYTE_ARRAY_STRUCTURE
 *
 * @author echils
 */
public class BYTE_ARRAY_STRUCTURE extends Structure {

    /**
     * byte array
     */
    public byte[] byValue;

    public BYTE_ARRAY_STRUCTURE(int iLen) {
        byValue = new byte[iLen];
    }

}