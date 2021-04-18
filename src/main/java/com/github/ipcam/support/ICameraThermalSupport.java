package com.github.ipcam.support;


import com.github.ipcam.entity.Temperature;
import com.github.ipcam.entity.exception.CameraNotSupportException;

import java.util.List;

/**
 * ICameraThermalSupport
 *
 * @author echils
 * @since 2020-03-19 11:35
 */
public interface ICameraThermalSupport {


    /**
     * Measure all temperature from the preset point
     *
     * @return {@link Temperature}
     */
    default List<Temperature> measureAll() {
        throw new CameraNotSupportException();
    }


    /**
     * Measure the temperature of the one infrared point from the preset point
     *
     * @param infraredNo infrared point index of preset point
     * @return {@link Temperature}
     */
    default Temperature measure(int infraredNo) {
        throw new CameraNotSupportException();
    }


    /**
     * Set infrared point
     *
     * @param temperature {@link Temperature}
     */
    default void setInfraredPoint(Temperature temperature) {
        throw new CameraNotSupportException();
    }


    /**
     * Get infrared point info from the preset point
     *
     * @param presetNo   preset point index of the camera
     * @param infraredNo infrared point index of preset point
     * @return {@link Temperature}
     */
    default Temperature getInfraredPoint(int presetNo, int infraredNo) {
        throw new CameraNotSupportException();
    }


    /**
     * Delete infrared point info from the preset point
     *
     * @param presetNo   preset point index of the camera
     * @param infraredNo infrared point index of preset point
     */
    default void deleteInfraredPoint(int presetNo, int infraredNo) {
        throw new CameraNotSupportException();
    }


    /**
     * get infrared point name
     *
     * @param presetNo   preset point index of the camera
     * @param infraredNo infrared point index of network camera
     */
    default String getInfraredPointName(int presetNo, int infraredNo) {
        throw new CameraNotSupportException();
    }


    /**
     * set infrared point name
     *
     * @param presetNo   preset point index of the camera
     * @param infraredNo infrared point index of network camera
     * @param name       infrared point name
     */
    default void setInfraredPointName(int presetNo, int infraredNo, String name) {
        throw new CameraNotSupportException();
    }


}
