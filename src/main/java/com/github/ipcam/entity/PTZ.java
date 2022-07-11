package com.github.ipcam.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PTZ
 *
 * @author echils
 */
@Data
@NoArgsConstructor
public class PTZ {

    /**
     * The Horizontal value
     */
    private double pan;

    /**
     * The vertical value
     */
    private double tilt;

    /**
     * The depth value
     */
    private double zoom;


    public PTZ(int pan, int tilt, int zoom) {
        this.pan = pan;
        this.tilt = tilt;
        this.zoom = zoom;
    }

    public PTZ(double pan, double tilt, double zoom) {
        this.pan = pan;
        this.tilt = tilt;
        this.zoom = zoom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PTZ cameraPTZ = (PTZ) o;
        return pan == cameraPTZ.pan && tilt == cameraPTZ.tilt && zoom == cameraPTZ.zoom;
    }

}
