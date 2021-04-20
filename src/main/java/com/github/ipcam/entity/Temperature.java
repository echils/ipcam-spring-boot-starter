package com.github.ipcam.entity;

import lombok.Data;

import static com.github.ipcam.entity.comm.StructureContext.INFRARED_POINT_NUM;

/**
 * Temperature
 *
 * @author echils
 * @since 2019-4-8 20:33
 */
@Data
public class Temperature {

    /**
     * Monitored area code
     */
    private int infraredNo;

    /**
     * Monitored area name
     */
    private String infraredName;

    /**
     * The preset point code of the camera
     */
    private int presetNo;

    /**
     * Max temperature
     */
    private float maxTemperature;

    /**
     * Min temperature
     */
    private float minTemperature;

    /**
     * Infrared temperature measurement coordinates
     */
    private Region[] regions = new Region[INFRARED_POINT_NUM];


    @Data
    public static class Region {

        /**
         * The X value of the point
         */
        private double x;

        /**
         * The Y value of the point
         */
        private double y;

        public Region(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

}
