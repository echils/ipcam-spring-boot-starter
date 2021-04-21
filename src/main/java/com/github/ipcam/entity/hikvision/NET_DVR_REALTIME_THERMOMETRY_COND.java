package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * ByteArrayStructure
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_REALTIME_THERMOMETRY_COND extends Structure {

    /**
     * Structure size
     */
    public int dwSize;

    public int dwChan = 2;

    public byte byRuleID;

    public byte byMode = 0;

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes = new byte[62];

}
