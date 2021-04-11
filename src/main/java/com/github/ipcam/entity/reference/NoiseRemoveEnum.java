package com.github.ipcam.entity.reference;

/**
 * NoiseRemoveEnum
 *
 * @author echils
 * @since 2020-03-19 13:36
 */
public enum NoiseRemoveEnum {

    OFF(0),
    GENERAL(1),
    EXPERT(2);

    private byte key;

    public byte getKey() {
        return key;
    }

    NoiseRemoveEnum(int key) {
        this.key = (byte) key;
    }
}
