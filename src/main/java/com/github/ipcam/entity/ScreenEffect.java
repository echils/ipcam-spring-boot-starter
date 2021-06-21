package com.github.ipcam.entity;

import lombok.Data;

/**
 * ScreenEffect
 *
 * @author echils
 */
@Data
public class ScreenEffect {

    /**
     * The brightness of screen
     */
    private int brightness;

    /**
     * The contrast of screen
     */
    private int contrast;

    /**
     * The saturation of screen
     */
    private int saturation;

    /**
     * The hue of screen
     */
    private int hue;

    /**
     * The sharpness of screen
     */
    private int sharpness;

}
