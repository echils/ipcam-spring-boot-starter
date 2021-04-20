package com.github.ipcam.entity;

import lombok.Data;

/**
 * PTZScope
 *
 * @author echils
 * @since 2021-04-18 17:06:40
 */
@Data
public class PTZScope {

    /**
     * The supported minimum Horizontal value
     */
    private int panMin;

    /**
     * The supported max Horizontal value
     */
    private int panMax;

    /**
     * The supported minimum vertical value
     */
    private int tiltMin;

    /**
     * The supported max vertical value
     */
    private int tiltMax;

    /**
     * The supported minimum depth value
     */
    private int zoomMin;

    /**
     * The supported max depth value
     */
    private int zoomMax;

}
