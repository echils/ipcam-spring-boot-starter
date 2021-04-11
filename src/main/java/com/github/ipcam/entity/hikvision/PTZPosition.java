package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * PTZPosition
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class PTZPosition extends Structure {

    public short action;
    public short panPos;
    public short tiltPos;
    public short zoomPos;

    public PTZPosition() {
    }

    public PTZPosition(short action, short panPos, short tiltPos, short zoomPos) {
        this.action = action;
        this.panPos = panPos;
        this.tiltPos = tiltPos;
        this.zoomPos = zoomPos;
    }

    public PTZPosition(int panPos, int tiltPos, int zoomPos) {
        this.panPos = (short) panPos;
        this.tiltPos = (short) tiltPos;
        this.zoomPos = (short) zoomPos;
    }

    public PTZPosition(int action, int panPos, int tiltPos, int zoomPos) {
        this.action = (short) action;
        this.panPos = (short) panPos;
        this.tiltPos = (short) tiltPos;
        this.zoomPos = (short) zoomPos;
    }


    @Override
    public String toString() {
        return "PTZPosition{" +
                "action=" + action +
                ", panPos=" + panPos +
                ", tiltPos=" + tiltPos +
                ", zoomPos=" + zoomPos +
                '}';
    }
}
