package com.github.ipcam.entity.reference;

/**
 * 预置点枚举
 *
 * @author echils
 * @since 2020-03-19 13:36
 */
public enum PresetEnum {

    SET_PRESET(8, "Set the preset"), //设置预置点
    CLE_PRESET(9, "Clear the preset"), //清除预置点
    GOTO_PRESET(39, "Goto the preset");//转到预置点


    private int key;
    private String implication;

    PresetEnum(int key, String implication) {
        this.key = key;
        this.implication = implication;
    }

    public Integer key() {
        return key;
    }
}
