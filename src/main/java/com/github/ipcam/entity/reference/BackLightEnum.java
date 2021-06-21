package com.github.ipcam.entity.reference;

/**
 * BackLightEnum
 *
 * @author echils
 */
public enum BackLightEnum {

    /**
     * Close
     */
    OFF(0),

    /**
     * The up
     */
    UP(1),

    /**
     * The down
     */
    DOWN(2),

    /**
     * The left
     */
    LEFT(3),

    /**
     * The right
     */
    RIGHT(4),

    /**
     * The middle
     */
    MIDDLE(5),

    /**
     * Custom config
     */
    CUSTOM(6),

    /**
     * Automatic
     */
    AUTOMATIC(11),

    /**
     * Multi zone
     */
    MULTI_ZONE(12);


    private byte value;

    BackLightEnum(int value) {
        this.value = (byte) value;
    }

    public byte getValue() {
        return value;
    }

}
