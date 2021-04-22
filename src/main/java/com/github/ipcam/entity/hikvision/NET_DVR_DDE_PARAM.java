package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_DDE_PARAM
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_DDE_PARAM extends Structure {

    /**
     * 1- Off, 2- Normal mode, 3- Expert mode
     */
    public byte byMode;

    /**
     * Normal mode level, value range: [1,100], valid in normal mode
     */
    public byte byNormalLevel;

    /**
     * Expert mode level, value range: [1,100], effective in Expert mode
     */
    public byte byExpertLevel;

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes = new byte[5];

}
