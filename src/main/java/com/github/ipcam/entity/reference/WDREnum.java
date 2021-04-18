package com.github.ipcam.entity.reference;

/**
 * WDREnum
 *
 * @author echils
 * @since 2020-03-19 13:36
 */
public enum WDREnum {

    OFF(0),
    ON(1),
    AUTOMATIC(2);


    private byte key;

    public byte getKey() {
        return key;
    }

    WDREnum(int key) {
        this.key = (byte) key;
    }
}
