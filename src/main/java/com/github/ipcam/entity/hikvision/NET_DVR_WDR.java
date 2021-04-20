package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_WDR
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_WDR extends Structure {

    public byte byWDREnabled;

    public byte byWDRLevel1;

    public byte byWDRLevel2;

    public byte byWDRContrastLevel;

    public byte[] byRes = new byte[16];

}
