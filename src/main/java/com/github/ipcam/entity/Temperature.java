package com.github.ipcam.entity;

import lombok.Data;

import java.io.Serializable;

import static com.github.ipcam.entity.comm.STRUCTURE_CONTEXT.INFRARED_POINT_NUM;

/**
 * Temperature
 *
 * @author echils
 */
@Data
public class Temperature implements Serializable {

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
    public static class Region implements Serializable {

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
