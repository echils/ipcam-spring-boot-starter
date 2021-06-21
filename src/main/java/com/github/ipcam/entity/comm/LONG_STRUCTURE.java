package com.github.ipcam.entity.comm;

import com.sun.jna.Structure;

/**
 * LONG_STRUCTURE
 *
 * @author echils
 */
public class LONG_STRUCTURE extends Structure {

    /**
     * long value
     */
    public long value;

    public LONG_STRUCTURE(long value) {
        this.value = value;
    }

}
