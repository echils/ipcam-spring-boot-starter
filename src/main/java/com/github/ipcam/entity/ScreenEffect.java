package com.github.ipcam.entity;

import lombok.Data;

/**
 * ScreenEffect
 *
 * @author echils
 * @since 2020-03-19 13:36
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
