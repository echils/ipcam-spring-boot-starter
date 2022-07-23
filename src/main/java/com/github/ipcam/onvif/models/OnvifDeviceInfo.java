package com.github.ipcam.onvif.models;

import lombok.Data;

/**
 * OnvifDeviceInfo
 *
 * @author echils
 */
@Data
public class OnvifDeviceInfo {

    /**
     * The manufacturer of device
     */
    private String manufacturer;

    /**
     * The model of device
     */
    private String model;

    /**
     * The serialNumber of device
     */
    private String serialNumber;

    /**
     * The firmware version of device
     */
    private String firmwareVersion;

    /**
     * The hardware id of device
     */
    private String hardwareId;

}
