package com.github.ipcam.entity;

import com.sun.jna.Structure;

/**
 * LongStructure
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class LongStructure extends Structure {

    public long value;

    public LongStructure(long value) {
        this.value = value;
    }
}
