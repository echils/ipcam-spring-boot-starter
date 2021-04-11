package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_DEFOGCFG
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_DEFOGCFG extends Structure {
    public byte byMode;
    public byte byLevel;
    public byte[] byRes = new byte[6];

}
