package com.github.ipcam.entity.reference;

/**
 * DayNightEnum
 *
 * @author echils
 * @since 2020-03-19 13:36
 */
public enum DayNightEnum {

    /**
     * Day
     */
    DAY(0),

    /**
     * Night
     */
    NIGHT(1),

    /**
     * Automatic
     */
    AUTOMATIC(2);

    private byte value;

    DayNightEnum(int value) {
        this.value = (byte) value;
    }

    public byte getValue() {
        return value;
    }

}
