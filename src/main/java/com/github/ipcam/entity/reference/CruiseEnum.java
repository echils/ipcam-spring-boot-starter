package com.github.ipcam.entity.reference;

/**
 * CruiseEnum
 *
 * @author echils
 */
public enum CruiseEnum {

    /**
     * Add the preset point to the cruise sequence
     */
    FILL_PRE_SEQ(30),

    /**
     * Set the cruising pause time
     */
    SET_SEQ_DWELL(31),

    /**
     * Cruising speed
     */
    SET_SEQ_SPEED(32),

    /**
     * Removes the preset point from the cruise sequence
     */
    CLE_PRE_SEQ(33),

    /**
     * Start to cruise
     */
    RUN_SEQ(37),

    /**
     * Stop to cruise
     */
    STOP_SEQ(38);

    private int key;

    CruiseEnum(int key) {
        this.key = key;
    }

    public Integer key() {
        return key;
    }

}
