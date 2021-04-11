package com.github.ipcam.entity.reference;


/**
 * 码流类型枚举
 *
 * @author echils
 * @since 2020-03-19 13:36
 */
public enum StreamTypeEnum {

    MAIN_STREAM(0, 0 << 31),  //主码流
    SUB_STREAM(1, 1 << 31); //子码流

    private int key;
    private int value;

    StreamTypeEnum(int key, int value) {
        this.key = key;
        this.value = value;
    }

    public int key() {
        return key;
    }

    public int value() {
        return value;
    }
}
