package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_JPEGPARA
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_JPEGPARA extends Structure {

    public short wPicSize;                /* 0=CIF, 1=QCIF, 2=D1 3=UXGA(1600x1200), 4=SVGA(800x600), 5=HD720p(1280x720),6=VGA*/
    public short wPicQuality;            /* 图片质量系数 0-最好 1-较好 2-一般 */

    public NET_DVR_JPEGPARA() {
    }

    public NET_DVR_JPEGPARA(int wPicSize, int wPicQuality) {
        this.wPicSize = (short) wPicSize;
        this.wPicQuality = (short) wPicQuality;
    }
}
