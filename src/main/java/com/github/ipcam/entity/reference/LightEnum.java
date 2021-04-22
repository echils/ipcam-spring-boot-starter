package com.github.ipcam.entity.reference;

/**
 * LightEnum
 *
 * @author echils
 * @since 2020-03-19 13:36
 */
public enum LightEnum {

    /**
     * Overexposure prevention and strong light suppression are off
     */
    ALL_OFF(0),

    /**
     * Anti-overexposure opening
     */
    PREVENT_OVEREXPOSURE_ON(1),

    /**
     * Strong light suppression turns on
     */
    STRONG_LIGHT_INHIBITION_ON(4);


    private byte value;

    LightEnum(int value) {
        this.value = (byte) value;
    }

    public byte getValue() {
        return value;
    }

}
