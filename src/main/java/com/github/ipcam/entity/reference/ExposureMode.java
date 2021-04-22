package com.github.ipcam.entity.reference;

/**
 * ExposureMode
 *
 * @author echils
 * @since 2020-03-19 13:36
 */
public enum ExposureMode {

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

    ExposureMode(int key) {
        this.key = (byte) key;
    }

    public byte getKey() {
        return key;
    }

}
