package com.github.ipcam.entity;

import lombok.Data;

/**
 * NetworkCamera
 *
 * @author echils
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
    private CameraDriver driverType;

}
