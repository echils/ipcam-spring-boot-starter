package com.github.ipcam.entity.reference;

/**
 * TrackEnum
 *
 * @author echils
 * @since 2020-03-19 13:36
 */
public enum TrackEnum {

    STA_MEM_CRUISE(34, "Start recording track"),
    STO_MEM_CRUISE(35, "Stop recording track"),
    RUN_CRUISE(36, "Start tracking");


    private int key;
    private String implication;

    TrackEnum(int key, String implication) {
        this.key = key;
        this.implication = implication;
    }

    public Integer key() {
        return key;
    }
}
