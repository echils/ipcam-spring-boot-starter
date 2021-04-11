package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_FFC_PARAM
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_FFC_PARAM extends Structure {
    public byte byMode;
    public byte byRes1;
    public int wCompensateTime;
    public byte[] byRes2 = new byte[4];

}
