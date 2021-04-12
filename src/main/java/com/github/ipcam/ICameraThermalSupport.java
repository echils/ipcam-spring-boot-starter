package com.github.ipcam;


import com.github.ipcam.entity.Temperature;

import java.util.List;

/**
 * ICameraThermalSupport
 *
 * @author echils
 * @since 2020-03-19 11:35
 */
public interface ICameraThermalSupport {


    /**
     * Get all temperature of the preset point
     *
     * @return {@link Temperature}
     */
    List<Temperature> tempAll();


    /**
     * get the temperature of the infrared point
     *
     * @param infraredNo infrared point index of network camera
     * @return {@link Temperature}
     */
    Temperature temp(int infraredNo);


    /**
     * get infrared point name
     *
     * @param presetNo   preset point index of the camera
     * @param infraredNo infrared point index of network camera
     */
    String getInfraredPointName(int presetNo, int infraredNo);


    /**
     * set infrared point name
     *
     * @param presetNo   preset point index of the camera
     * @param infraredNo infrared point index of network camera
     * @param name       infrared point name
     */
    void setInfraredPointName(int presetNo, int infraredNo, String name);


    /**
     * set infrared point
     *
     * @param temperature {@link Temperature}
     */
    void setInfraredPoint(Temperature temperature);


    /**
     * get infrared point info
     *
     * @param presetNo   preset point index of the camera
     * @param infraredNo infrared point index of network camera
     * @return {@link Temperature}
     */
    Temperature getInfraredPoint(int presetNo, int infraredNo);


    /**
     * delete infrared point info
     *
     * @param presetNo   preset point index of the camera
     * @param infraredNo infrared point index of network camera
     */
    void deleteInfraredPoint(int presetNo, int infraredNo);

}
