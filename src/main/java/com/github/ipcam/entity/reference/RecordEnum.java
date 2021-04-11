package com.github.ipcam.entity.reference;

/**
 * 录像枚举
 *
 * @author echils
 * @since 2020-03-19 13:36
 */
public enum RecordEnum {

    ALL_DAY(1),
    SECTIONAL(0);

    private int value;

    RecordEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
