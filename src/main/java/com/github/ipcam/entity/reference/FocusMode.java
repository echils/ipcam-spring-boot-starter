package com.github.ipcam.entity.reference;

/**
 * FocusMode
 *
 * @author echils
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
