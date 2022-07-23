package com.github.ipcam.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.math.RoundingMode;
import java.text.NumberFormat;

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
        private float x;

        /**
         * The Y value of the point
         */
        private float y;

        @JsonIgnore
        NumberFormat numberFormat = NumberFormat.getNumberInstance();

        public Region() {
        }

        public Region(float x, float y) {
            this.x = numFormat(x, 3);
            this.y = numFormat(x, 3);
        }

        /**
         * number format
         */
        public Float numFormat(Float source, int num) {
            numberFormat.setMaximumFractionDigits(num);
            numberFormat.setRoundingMode(RoundingMode.UP);
            return Float.valueOf(numberFormat.format(source));
        }
    }

}
