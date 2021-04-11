package com.github.ipcam.entity;

import com.sun.jna.Structure;

/**
 * IntArrayStructure
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class IntArrayStructure extends Structure {

    public int[] value;

    public IntArrayStructure(int iLen) {
        value = new int[iLen];
    }
}
