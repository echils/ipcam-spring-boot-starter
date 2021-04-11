package com.github.ipcam;

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
     * The driver of the camera
     */
    private CameraSupportedDriver driverType;

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
     * Does the camera support infrared function
     */
    private boolean thermal;


}
