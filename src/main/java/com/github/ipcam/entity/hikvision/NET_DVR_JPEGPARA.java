package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_JPEGPARA
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_JPEGPARA extends Structure {

    public short wPicSize;

    public short wPicQuality;

    public NET_DVR_JPEGPARA(int wPicSize, int wPicQuality) {
        this.wPicSize = (short) wPicSize;
        this.wPicQuality = (short) wPicQuality;
    }
}
