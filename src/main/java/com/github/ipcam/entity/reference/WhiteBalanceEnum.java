package com.github.ipcam.entity.reference;

/**
 * WhiteBalanceEnum
 *
 * @author echils
 */
public enum WhiteBalanceEnum {

    /**
     * Manual white balance
     */
    MWB(0),

    /**
     * Automatic white balance 1
     */
    AWB1(1),

    /**
     * Automatic white balance 2
     */
    AWB2(2),

    /**
     * Locked white balance
     */
    LWB(3),

    /**
     * outdoor
     */
    OUTDOOR(4),

    /**
     * indoor
     */
    INDOOR(5),

    /**
     * Fluorescent lamp
     */
    FLUORESCENT(6),

    /**
     * Sodium lamp
     */
    SODIUM(7),

    /**
     * Automatic tracking
     */
    AUTO_TRACK(8),

    /**
     * One-time white balance
     */
    ONEPUSH(9),

    /**
     * Outdoor automatic
     */
    AUTO_OUTDOOR(10),

    /**
     * Sodium lamp automatic
     */
    AUTO_SODIUM(11),

    /**
     * Mercury lamp mode
     */
    MERCURY(12),

    /**
     * Automatic white balance
     */
    AUTOMATIC(13),

    /**
     * Incandescent lamp
     */
    INCANDESCENT(14),

    /**
     * Warm light
     */
    WARM(15),

    /**
     * Natural light
     */
    NATURAL(16);

    private byte key;

    WhiteBalanceEnum(int key) {
        this.key = (byte) key;
    }

    public byte getKey() {
        return key;
    }

}
