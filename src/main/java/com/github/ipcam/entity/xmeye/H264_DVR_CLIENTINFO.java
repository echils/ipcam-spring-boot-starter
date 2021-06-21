package com.github.ipcam.entity.xmeye;

import com.github.ipcam.entity.jnax.W32API;
import com.sun.jna.Structure;


/**
 * H264_DVR_CLIENTINFO
 *
 * @author echils
 */
public class H264_DVR_CLIENTINFO extends Structure {

    /**
     * Channel number
     */
    public int nChannel;

    /**
     * 0 represents the primary stream and 1 represents the sub-stream
     */
    public int nStream;

    /**
     * 0: TCP mode,1: UDP mode,2: multicast mode, 3-RTP mode, 4-audio and video separation (TCP)
     */
    public int nMode;

    /**
     * Only works for the combined code channel, the jigsaw pattern of the combined code channel
     */
    public int nComType;

    /**
     * {@link W32API.HWND}
     */
    public W32API.HWND hWnd;

}
