package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_ELECTRONICSTABILIZATION
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_ELECTRONICSTABILIZATION extends Structure {

    public byte byEnable;

    public byte byLevel;

    public byte[] byRes = new byte[6];

}
