package com.github.ipcam.entity.reference;

/**
 * WDREnum
 *
 * @author echils
 * @since 2020-03-19 13:36
 */
public enum WDREnum {

    /**
     * Close
     */
    OFF(0),

    /**
     * Open
     */
    ON(1),

    /**
     * Automatic
     */
    AUTOMATIC(2);

    private byte key;

    WDREnum(int key) {
        this.key = (byte) key;
    }

    public byte getKey() {
        return key;
    }

}
