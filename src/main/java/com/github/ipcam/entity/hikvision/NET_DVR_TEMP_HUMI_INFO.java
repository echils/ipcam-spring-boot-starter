package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_TEMP_HUMI_INFO
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_TEMP_HUMI_INFO extends Structure {
    public int dwSize;
    public NET_DVR_TIME_V30 struCurrentTime = new NET_DVR_TIME_V30();
    public float fTemperature;
    public float fHumidity;
    public byte[] byRes = new byte[256];
}
