package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_SMARTIR_PARAM
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_SMARTIR_PARAM extends Structure {

    public byte byMode;

    public byte byIRDistance;

    public byte byShortIRDistance;

    public byte byLongIRDistance;

}
