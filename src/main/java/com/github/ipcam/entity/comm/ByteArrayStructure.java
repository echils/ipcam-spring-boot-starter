package com.github.ipcam.entity.comm;

import com.sun.jna.Structure;

/**
 * ByteArrayStructure
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class ByteArrayStructure extends Structure {

    /**
     * byte array
     */
    public byte[] byValue;

    public ByteArrayStructure(int iLen) {
        byValue = new byte[iLen];
    }
}