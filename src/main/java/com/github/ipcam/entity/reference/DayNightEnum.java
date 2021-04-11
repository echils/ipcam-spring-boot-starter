package com.github.ipcam.entity.reference;

/**
 * DayNightEnum
 *
 * @author echils
 * @since 2020-03-19 13:36
 */
public enum DayNightEnum {

    DAY(0),
    NIGHT(1),
    AUTO(2);

    private byte value;

    public byte getValue() {
        return value;
    }

    DayNightEnum(int value) {
        this.value = (byte) value;
    }
}
