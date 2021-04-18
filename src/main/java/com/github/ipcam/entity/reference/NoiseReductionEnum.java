package com.github.ipcam.entity.reference;

/**
 * NoiseReductionEnum
 *
 * @author echils
 * @since 2020-03-19 13:36
 */
public enum NoiseReductionEnum {

    OFF(0),
    GENERAL(1),
    EXPERT(2);

    private byte key;

    public byte getKey() {
        return key;
    }

    NoiseReductionEnum(int key) {
        this.key = (byte) key;
    }
}
