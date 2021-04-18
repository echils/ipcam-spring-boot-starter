package com.github.ipcam.entity.reference;

/**
 * LightEnum
 *
 * @author echils
 * @since 2020-03-19 13:36
 */
public enum LightEnum {

    ALL_OFF(0, "防过曝和强光抑制均关闭"),
    PREVENT_OVEREXPOSURE_ON(1, "防过曝开启"),
    STRONG_LIGHT_INHIBITION_ON(4, "强光抑制开启");


    private byte value;
    private String desc;

    public byte getValue() {
        return value;
    }

    LightEnum(int value, String desc) {
        this.value = (byte) value;
        this.desc = desc;
    }
}
