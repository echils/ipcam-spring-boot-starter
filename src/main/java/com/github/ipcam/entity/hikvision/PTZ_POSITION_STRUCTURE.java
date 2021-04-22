package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * PTZ_POSITION_STRUCTURE
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class PTZ_POSITION_STRUCTURE extends Structure {

    /**
     * Operation type, valid only at setup time. 1- Positioning PTZ parameter, 2- Positioning P parameter,
     * 3- Positioning T parameter, 4- Positioning Z parameter, 5- Positioning PT parameter
     */
    public short action;

    /**
     * P parameter (horizontal parameter)
     */
    public short panPos;

    /**
     * T parameter (vertical parameter)
     */
    public short tiltPos;

    /**
     * Z parameter (doubled parameter)
     */
    public short zoomPos;

    public PTZ_POSITION_STRUCTURE() {
    }

    public PTZ_POSITION_STRUCTURE(int action, int panPos, int tiltPos, int zoomPos) {
        this.action = (short) action;
        this.panPos = (short) panPos;
        this.tiltPos = (short) tiltPos;
        this.zoomPos = (short) zoomPos;
    }

}
