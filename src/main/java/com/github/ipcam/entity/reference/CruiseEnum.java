package com.github.ipcam.entity.reference;

/**
 * CruiseEnum
 *
 * @author echils
 * @since 2020-03-19 13:36
 */
public enum CruiseEnum {

    FILL_PRE_SEQ(30, "Add the preset point to the cruise sequence"),
    SET_SEQ_DWELL(31, "Set the cruising pause time"),
    SET_SEQ_SPEED(32, "Cruising speed"),
    CLE_PRE_SEQ(33, "Removes the preset point from the cruise sequence"),
    RUN_SEQ(37, "start to cruise"),
    STOP_SEQ(38, "stop to cruise");

    private int key;
    private String implication;

    CruiseEnum(int key, String implication) {
        this.key = key;
        this.implication = implication;
    }

    public Integer key() {
        return key;
    }
}
