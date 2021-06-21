package com.github.ipcam.entity.reference;

/**
 * NoiseReductionEnum
 *
 * @author echils
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
