package com.github.ipcam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

/**
 * NetworkCamera
 *
 * @author echils
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NetworkCamera {

    /**
     * The ip of the camera
     */
    private String ip;

    /**
     * The port of the camera
     */
    private Integer port;

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


    public boolean isIllegal() {
        return StringUtils.isEmpty(ip) || port == null || driverType == null;
    }

}
