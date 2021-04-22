package com.github.ipcam.entity.reference;

/**
 * TrackEnum
 *
 * @author echils
 * @since 2020-03-19 13:36
 */
public enum TrackEnum {

    /**
     * Start recording track
     */
    START_MEM_CRUISE(34),

    /**
     * Stop recording track
     */
    STOP_MEM_CRUISE(35),

    /**
     * Run tracking
     */
    RUN_CRUISE(36);


    private int key;

    TrackEnum(int key) {
        this.key = key;
    }

    public Integer key() {
        return key;
    }

}
