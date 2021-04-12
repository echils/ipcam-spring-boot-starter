package com.github.ipcam.entity;

import com.github.ipcam.CameraSupportedDriver;
import lombok.Data;

/**
 * NetworkCamera
 *
 * @author echils
 * @since 2020-03-19 18:09
 */
@Data
public class NetworkCamera {


    /**
     * The ip of the camera
     */
    private String ip;

    /**
     * The port of the camera
     */
    private int port;

    /**
     * The username of the camera
     */
    private String username;

    /**
     * The password of the camera
     */
    private String password;

    /**
     * The driver of the camera
     */
    private CameraSupportedDriver driverType;

    /**
     * Does the camera support infrared function
     */
    private boolean thermal;


}
