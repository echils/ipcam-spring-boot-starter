package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_LASER_PARAM_CFG
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_LASER_PARAM_CFG extends Structure {
    public byte byControlMode;
    public byte bySensitivity;
    public byte byTriggerMode;
    public byte byBrightness;
    public byte byAngle;
    public byte byLimitBrightness;
    public byte byEnabled;
    public byte byIllumination;
    public byte byLightAngle;
    public byte[] byRes = new byte[7];

}
