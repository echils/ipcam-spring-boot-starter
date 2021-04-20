package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * PTZ_POSITION_STRUCTURE
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class PTZ_POSITION_STRUCTURE extends Structure {

    public short action;

    public short panPos;

    public short tiltPos;

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
