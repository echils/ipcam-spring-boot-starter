package com.github.ipcam.entity;

import lombok.Data;

/**
 * PTZ
 *
 * @author echils
 */
@Data
public class PTZ {

    /**
     * The Horizontal value
     */
    private int pan;

    /**
     * The vertical value
     */
    private int tilt;

    /**
     * The depth value
     */
    private int zoom;

    public PTZ(int pan, int tilt, int zoom) {
        this.pan = pan;
        this.tilt = tilt;
        this.zoom = zoom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PTZ cameraPTZ = (PTZ) o;
        return Math.abs(pan - cameraPTZ.pan) < 10 &&
                Math.abs(tilt - cameraPTZ.tilt) < 10 &&
                Math.abs(zoom - cameraPTZ.zoom) < 10;
    }

}
