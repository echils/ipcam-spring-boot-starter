package com.github.ipcam.entity.xmeye;

import com.github.ipcam.entity.jnax.W32API;
import com.sun.jna.Structure;


/**
 * H264_DVR_CLIENTINFO
 *
 * @author echils
 * @since 2018-11-29 11:24
 */
public class H264_DVR_CLIENTINFO extends Structure {

    public int nChannel;

    public int nStream;

    public int nMode;

    public int nComType;

    public W32API.HWND hWnd;

}
