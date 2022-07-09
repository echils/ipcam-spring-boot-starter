package com.github.ipcam.entity.reference;

/**
 * ExposureModeEnum
 *
 * @author echils
 */
public enum ExposureModeEnum {

    /**
     * Manual exposure
     */
    MANUAL(0),

    /**
     * Automatic exposure
     */
    AUTOMATIC(1),

    /**
     * Aperture priority
     */
    APERTURE_PRIORITY(2),

    /**
     * Shutterpriority
     */
    SHUTTER_PRIORITY(3),

    /**
     * Gain priority
     */
    GAIN_PRIORITY(3);


    private byte key;

    ExposureModeEnum(int key) {
        this.key = (byte) key;
    }

    public byte getKey() {
        return key;
    }

}
