package com.github.ipcam.entity.reference;

/**
 * FocusMode
 *
 * @author echils
 * @since 2021-04-18 18:54:23
 */
public enum FocusMode {

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

    FocusMode(int key) {
        this.key = (byte) key;
    }

    public byte getKey() {
        return key;
    }

}
