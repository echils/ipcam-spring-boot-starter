package com.github.ipcam.entity.reference;

/**
 * PresetEnum
 *
 * @author echils
 */
public enum PresetEnum {

    /**
     * Set the preset
     */
    SET_PRESET(8),

    /**
     * Clear the preset
     */
    CLE_PRESET(9),

    /**
     * Goto the preset
     */
    GOTO_PRESET(39);


    private int key;

    PresetEnum(int key) {
        this.key = key;
    }

    public Integer key() {
        return key;
    }

}
