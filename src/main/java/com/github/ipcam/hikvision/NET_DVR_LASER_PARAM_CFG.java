package com.github.ipcam.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_LASER_PARAM_CFG
 *
 * @author echils
 */
public class NET_DVR_LASER_PARAM_CFG extends Structure {

    /**
     * Control mode: 0- invalid, 1- automatic, 2- manual, default: automatic
     */
    public byte byControlMode;

    /**
     * Laser lamp sensitivity, value range: 0~100, default: 50
     */
    public byte bySensitivity;

    /**
     * Laser lamp trigger mode: 0- invalid, 1- movement trigger, 2- photosensitive trigger, default: movement trigger
     */
    public byte byTriggerMode;

    /**
     * Laser lamp brightness, control mode is effective in manual mode, value range: 0~255, default: 100
     */
    public byte byBrightness;

    /**
     * Laser lamp Angle, 0 means invalid, value range: 1~36, default: 12. The irradiation range of the laser lamp is
     * a circle, and adjusting the laser Angle is to adjust the radius of this circle
     */
    public byte byAngle;

    /**
     * Laser lamp brightness limit, control mode is effective in automatic mode, value range: 0~100
     */
    public byte byLimitBrightness;

    /**
     * Manual control of laser lamp enable: 0- off, 1- start
     */
    public byte byEnabled;

    /**
     * Laser lamp intensity configuration, value range: 0~100
     */
    public byte byIllumination;

    /**
     * Compensatory light Angle, value range: 0~100
     */
    public byte byLightAngle;

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes = new byte[7];

}
