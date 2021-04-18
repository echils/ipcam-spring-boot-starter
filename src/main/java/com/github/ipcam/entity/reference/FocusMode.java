package com.github.ipcam.entity.reference;

/**
 * FocusMode
 *
 * @author echils
 * @since 2021-04-18 18:54:23
 */
public enum FocusMode {

    AUTOMATIC(0, "自动"),
    MANUAL(1, "手动"),
    SEMI_AUTOMATIC(2, "半自动");


    private byte key;
    private String desc;

    FocusMode(int key, String desc) {
        this.key = (byte) key;
        this.desc = desc;
    }

    public byte getKey() {
        return key;
    }
}
