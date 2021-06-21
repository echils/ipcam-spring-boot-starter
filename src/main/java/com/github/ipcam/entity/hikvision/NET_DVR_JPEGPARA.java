package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_JPEGPARA
 *
 * @author echils
 */
public class NET_DVR_JPEGPARA extends Structure {

    /**
     * Picture size: 0-CIF(352*288/352*240), 1-QCIF(176*144/176*120), 2-4CIF(704*576/704*480) or D1(720*576/720*486),
     * 3-UXGA(1600*1200), 1-UXGA (1600*1200), 4-SVGA(800*600), 5-HD720P(1280*720), 6-VGA(640*480), 7-XVGA(1280*960),
     * 8-HD900P(1600*900), 9-HD1080P(1920*1080), 4-SVGA(800*600), 5-HD720P(1280*720), 6-VGA(640*480), 7-XVGA(1280*960),
     * 8-HD900P(1600*900), 9-HD1080P(1920*1080), 10-2560*1920, 11-1600*304, 12-2048*1536, 13-2448*2048, 14-2448*1200,
     * 15-2448*800, 16-XGA(1024*768), 17-SXGA(1280*1024), 18-WD1(960*576/960*480), 19-1080i (1920*1080), 20-576*576,
     * 21-1536*1536, 22-1920*1920, 23-320*240, 24-720*720, 25-1024*768, 26-1280*1280, 27-1600*600, 28-2048*768,
     * 29-160*120, 75-336*256, 78-384*256, 79-384*216, 80-320*256, 82-320*192, 83-512*384, 127-480*272, 128-512*272,
     * 161-288*320, 162-144* 176,163-480 *640, 164-240*320, 165-120*160, 166-576*720, 167-720*1280, 168-576 * 960,
     * 180-180*240, 181-360*480, 182-540*720, 183-720*960, 184-960*1280, 185-1080*1440, 500-384*288,
     * 0xFF-AUTO (using current stream resolution)
     */
    public short wPicSize;

    /**
     * Image quality coefficient: 0- best, 1- good, 2- average
     */
    public short wPicQuality;

    public NET_DVR_JPEGPARA(int wPicSize, int wPicQuality) {
        this.wPicSize = (short) wPicSize;
        this.wPicQuality = (short) wPicQuality;
    }

}
