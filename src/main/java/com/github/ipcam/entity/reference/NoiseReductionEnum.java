package com.github.ipcam.entity.reference;

/**
 * NoiseReductionEnum
 *
 * @author echils
 * @since 2020-03-19 13:36
 */
public enum NoiseReductionEnum {

    /**
     * Close
     */
    OFF(0),

    /**
     * General
     */
    GENERAL(1),

    /**
     * Expert
     */
    EXPERT(2);

    private byte key;

    NoiseReductionEnum(int key) {
        this.key = (byte) key;
    }

    public byte getKey() {
        return key;
    }

}
