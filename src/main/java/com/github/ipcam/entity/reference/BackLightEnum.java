package com.github.ipcam.entity.reference;

/**
 * BackLightEnum
 *
 * @author echils
 * @since 2020-03-19 13:36
 */
public enum BackLightEnum {
    OFF(0),
    UP(1),
    DOWN(2),
    LEFT(3),
    RIGHT(4),
    MIDDLE(5),
    CUSTOM(6),
    AUTO(11),
    MULTI_ZONE(12);


    private byte value;

    public byte getValue() {
        return value;
    }

    BackLightEnum(int value) {
        this.value = (byte) value;
    }
}
