package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;


/**
 * ByteArrayStructure
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_POINT_THERM_CFG extends Structure {

    public float fTemperature;
    public NET_VCA_POINT struPoint = new NET_VCA_POINT();
    public byte[] byRes = new byte[120];
}
