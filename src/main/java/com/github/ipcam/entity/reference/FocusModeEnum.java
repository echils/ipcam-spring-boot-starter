package com.github.ipcam.entity.reference;

/**
 * FocusModeEnum
 *
 * @author echils
 */
public enum FocusModeEnum {

    /**
     * Automatic
     */
    AUTOMATIC(0),

    /**
     * Manual
     */
    MANUAL(1),

    /**
     * SEMI-Automatic
     */
    SEMI_AUTOMATIC(2);


    private byte key;

    FocusModeEnum(int key) {
        this.key = (byte) key;
    }

    public byte getKey() {
        return key;
    }

}
