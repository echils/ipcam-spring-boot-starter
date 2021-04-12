package com.github.ipcam.entity.reference;

/**
 * PresetEnum
 *
 * @author echils
 * @since 2020-03-19 13:36
 */
public enum PresetEnum {

    SET_PRESET(8, "Set the preset"),
    CLE_PRESET(9, "Clear the preset"),
    GOTO_PRESET(39, "Goto the preset");


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
