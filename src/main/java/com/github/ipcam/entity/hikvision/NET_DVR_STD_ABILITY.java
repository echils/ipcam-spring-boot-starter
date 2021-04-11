package com.github.ipcam.entity.hikvision;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

/**
 * NET_DVR_STD_ABILITY
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_STD_ABILITY extends Structure {
    public Pointer lpCondBuffer;
    public int dwCondSize;
    public Pointer lpOutBuffer;
    public int dwOutSize;
    public Pointer lpStatusBuffer;
    public int dwStatusSize;
    public int dwRetSize;
    public byte[] byRes = new byte[32];
}
