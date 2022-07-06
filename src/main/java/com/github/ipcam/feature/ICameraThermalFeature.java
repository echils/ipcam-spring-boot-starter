package com.github.ipcam.feature;


import com.github.ipcam.entity.Temperature;
import com.github.ipcam.entity.exception.CameraNotSupportException;

import java.util.List;

/**
 * ICameraThermalFeature
 *
 * @author echils
 */
public interface ICameraThermalFeature {


    /**
     * Measure all infrared points temperature of the preset point and
     * you must jump to the specified preset point before temperature measurement.
     * If you want to get the details of the infrared point,
     * you can get it from the method {@link ICameraThermalFeature#getInfraredPoint(int, int)}
     *
     * @return {@link Temperature}
     */
    default List<Temperature> measureAll() {
        throw new CameraNotSupportException();
    }


    /**
     * Measure the one infrared point temperature of the preset point and
     * you must jump to the specified preset point before temperature measurement
     * If you want to get the details of the infrared point,
     * you can get it from the method {@link ICameraThermalFeature#getInfraredPoint(int, int)}
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
     * Get infrared point detail info from the preset point
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


}
