package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_DDE_PARAM
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_DDE_PARAM extends Structure {
    public byte byMode;
    public byte byNormalLevel;
    public byte byExpertLevel;
    public byte[] byRes = new byte[5];

}
