package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_AGC_PARAM
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_AGC_PARAM extends Structure {
    public byte bySceneType;
    public byte byLightLevel;
    public byte byGainLevel;
    public byte[] byRes = new byte[5];
}
