package com.github.ipcam.entity.comm;

import com.sun.jna.Structure;

/**
 * BYTE_ARRAY_STRUCTURE
 *
 * @author echils
 * @since 2020-03-19 13:42
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