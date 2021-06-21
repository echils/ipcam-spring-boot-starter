package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_COMPRESSION_INFO_V30
 *
 * @author echils
 */
public class NET_DVR_COMPRESSION_INFO_V30 extends Structure {

    /**
     * Type of stream: 0- Video stream, 1- Composite stream, 0xFE - Automatic (same as source)
     * If it is an event compression parameter (StruEventRecordPara, device support is required),
     * the highest bit (byStreamType & 0x80) indicates whether the event compression parameter is enabled,
     * <p>
     * that is, when StruEventRecordPara is configured:
     * ByStreamType&0x80 == 0 to disable event compression;
     * (byStreamType&0x80 == 1)&&(byStreamType&0x7f == 0) means that event compression parameter
     * is enabled and the stream type is set to video stream;
     * <p>
     * (byStreamType&0x80 == 1)&&(byStreamType&0x7f == 1) means that event compression parameter
     * is enabled and the stream type is set to composite;
     * <p>
     * ByStreamType ==0xfe means that the event compression parameter is enabled and the stream type is set to be the same as the source.
     */
    public byte byStreamType;

    /**
     * When the current resolution index of the device exceeds 254:
     * 1) When getting, the value of this field is 254 (without specific meaning, used to indicate that the actual resolution
     * index value exceeds 255), and the actual resolution value is represented by DWResolution in Net_DVR_Multi_Stream_CompressionCFG;
     * 2) When setting, the value still needs to be set to 254, and the actual resolution value is represented by DWResolution.
     * When the current resolution index of the device is less than 254:
     * 1) When obtaining, the value of this field is the actual resolution value, and the DWResolution value does not need
     * to be processed (0 (devices greater than 254 are not supported) or the actual resolution value (devices configured with 254 are supported);
     * 2) When setting, this field is the actual resolution value, and the DWResolution value remains unchanged.
     * <p>
     * Resolution index value: 0-DCIF(528*384/528*320), 1-CIF(352*288/352*240), 2-QCIF(176*144/176*120), 3-4CIF(704*576/704*480)
     * or D1(720*576/720*486), 4-2CIF(704*288/704*240), 6-QQVGA (320*240), 7-QQVGA(160*120), 12-384*288, 13-576*576, 16-VGA(640*480),
     * 17-UXGA(1600*1200), 18-SVGA(800*600), 19-HD720P(1280*720), 20-XVGA(1280*960), 21-HD900P(1600*900), 22-1360*1024, 23-1536*1536,
     * 24-1920*1920, 27-1920*1080p, 28-2560*1920, 29-1600*304, 30-2048*1536, 31-2448*2048, 32-2448*1200, 33-2448*800, 34-XGA(1024*768),
     * 35-SXGA(1280*1024), 36-WD1(960*576/960*480), 37-1080I (1920*1080), 38-WXGA(1440*900), 39-HD_F(1920*1080/1280*720),
     * 40-HD_H(1920*540/1280*360), 41-HD_Q(960*540/630*360), 42-2336*1744, 43-1920*1456, 44-2592*2048, 45-3296*2472, 46-1376*768,
     * 47-1366*768, 48-1360*768, 49-WSXGA+, 50-720*720, 51-1280*1280, 52-2048*768, 53-2048*2048, 54-2560*2048, 55-3072*2048,
     * 56-2304*1296, 57-WXGA(1280*800), 58-1600*600, 59-1600*900, 60-2752*2208, 61-384*288, 62-4000*3000, 63-4096*2160, 64-3840*2160,
     * 65-4000*2250, 66-3072*1728, 67-2592*1944, 68-2464*1520, 69-1280*1920, 70-2560*1440, 71-1024*1024, 72-160*128, 73-324*240,
     * 74-324*256, 75-336*256, 76-640*512, 77-2720*2048, 78-384*256, 79-384*216, 80-320*256, 81-320*180, 82-320*192, 83-512*384,
     * 84-325*256, 85-256*192, 86-640 *360, 87-1776x1340, 88-1936x1092, 89-2080x784, 90-2144x604, 91-1920*1200, 92-4064*3040,
     * 93-3040*3040, 94-3072*2304, 95-3072*1152, 96-2560*2560, 97-2688*1536, 98-2688*1520, 99-3072*3072, 100-3392*2008,
     * 101-4000*3080, 102-960*720, 103-1024*1536, 104-704*1056, 105-352*528, 106-2048*1530, 107-2560*1600, 108-2800* 2100,
     * 109-4088*4088, 110-4000*3072, 111-960*1080(1080p Lite), 112-640*720(Half 720p), 113-640*960, 114-320*480, 115-3840*2400,
     * 116-3840*1680, 117-2560*1120, 118-704*320, 119-1200*192, 120-480*768, 121-768*480, 122-320* 512,123-512 *320, 124-4096*1800,
     * 125-1280*560, 126-2400*3840, 127-480* 272,128-512 * 272,129-2592 *2592, 131-1600* 2880, 131-1600*2560, 132-2720*1192,
     * 133-3MP(1920*1536/2048*1536), 134-5MP(2560*1944), 137-4096*1200, 138-3840*1080, 139-2720*800, 140-512*232, 141-704*200,
     * 142-512*152, 143-2048* 896,144-2048 *600, 145-1280* 376,150-8208 *3072, 151-4096* 1536,152-6912 *2800, 153-3456*1400,
     * 167-720*1280, 168-576*960(placeholder, Not tested), 169-2944*1656, 170-432*240, 171-2160*3840, 172-1080*192,173-7008*1080,
     * 174-3504*540, 175-1752*270,176-876*135,177-4096*1440,178-4096*1080,179-1536*864,180-180*240(placeholder, not tested),
     * 181-360*480(placeholder, Untested), 182-540*720(occupied, untested), 183-720*960(occupied, untested), 184-960*1280(occupied, untested),
     * 185-1080*1440(occupied, untested) Not tested),186-3200*1800,187-1752*272,188-872*136,189-1280*1440,190-3520*544,
     * 191-1728*256,192-864*128, 193-5470*3684, 194-2560 * 750, 195-480196-1024 * 1600 * 300, 197-848 * * * 2304, 3616199-8160,
     * 480198-8160, 215-1080*720 216-360x640, 0xFF-AUTO (using current stream resolution)
     */

    public byte byResolution;

    /**
     * Bit rate type: 0- variable bit rate, 1- fixed rate
     */
    public byte byBitrateType;

    /**
     * Image Quality: 0- Best, 1- Second Good, 2- Good, 3- Fair, 4- Poor, 5- Poor, 0xFE - Automatic (Same as source)
     */
    public byte byPicQuality;

    /**
     * Video code rate: 0-Reserved, 1-16K(Reserved), 2-32K, 3-48K, 4-64K, 5-80K, 6-96K, 7-128K, 8-160K, 9-192K, 10-224K,
     * 11-256K, 12-320K, 13-384K, 14-448K, 15-512K, 16-640K, 17-768K, 18-896K, 19-1024K, 20-1280K, 21-1536K, 22-1792K,
     * 23-2048K, 24-3072K, 25-4096K, 26-8192K, 27-16384K, 0xFFFFFFFE - Automatic (source consistent).
     * The highest bit (31 bits) is set to 1 to represent the custom code stream, 0 ~ 30 bits represent the code stream
     * value, and the minimum value is 16K
     */
    public int dwVideoBitrate;

    /**
     * Video frame rate: 0-all, 1-1/16, 2-1/8, 3-1/4, 4-1/2, 5-1, 6-2, 7-4, 8-6, 9-8, 10-10, 11-12, 12-16, 13-20, 14-15,
     * 15-18, 16-22, 17-25, 18-30, 19-35, 20-40, 21-45, 22-50, 23-55, 24-60, 25-3, 26-5, 27-7, 28-9, 29-100, 30-120,
     * 31-24, 32-48, 33-8.3, 0xFFFFFFFE - Automatic (same as source)
     */
    public int dwVideoFrameRate;

    /**
     * I frame interval, 0xFFFE - automatic (source consistent), 0xFFFF - invalid
     */
    public short wIntervalFrameI;

    /**
     * Video frame format: 0-BBP frame, 1-BP frame, 2-Single P frame, 0xFF - Invalid
     */
    public byte byIntervalBPFrame;

    /**
     * Keep it, set it to 0
     */
    public byte byres1;

    /**
     * Video encoding types: 0-Private 264, 1-Standard H264, 2-Standard MPEG4, 7-M-JPEG, 8-MPEG2, 9-SVAC, 10-Standard H265,
     * 0xFE - Automatic (source congruent), 0xFF - Invalid
     */
    public byte byVideoEncType;

    /**
     * Audio encoding types: 0-G722, 1-G711_U, 2-G711_A, 5-MP2L2, 6-G726, 7-AAC, 8-PCM,
     * 0xFE - Automatic (source consistent), 0xFF - Invalid
     */
    public byte byAudioEncType;

    /**
     * Video Coding Complexity: 0- Low, 1- Medium, 2- High, 0xFE - Automatic (same as source),
     */
    public byte byVideoEncComplexity;

    /**
     * 0- SVC feature is not enabled, 1- SVC feature is enabled, 2- SVC feature is automatically enabled.
     * SVC: Scalable Video Coding Scalable Video Coding
     */
    public byte byEnableSvc;

    /**
     * Package Type: 1-Bare Stream, 2-RTP, 3-PS, 4-TS, 5-Private, 6-FLV, 7-ASF, 8-3GP, 9-RTP+PS (GB: GB28181), 0xFF - Invalid
     */
    public byte byFormatType;

    /**
     * Audio code rate: 0- default, 1-8kbps, 2-16kbps, 3-32kbps, 4-64kbps, 5-128kbps, 6-192kbps, 7-40kbps, 8-48kbps,
     * 9-56kbps, 10-80kbps, 11-96kbps, 12-112kbps, 13-144kbps, 14-160kbps
     * IPC V5.1.0 default: 4-64kbps, support 3, 4, 5
     */
    public byte byAudioBitRate;

    /**
     * Smooth code stream, value range: 1 ~ 100, level 1 means Clear, 100 means Smooth
     */
    public byte bySteamSmooth;

    /**
     * Audio Sampling Rate: 0-Default, 1-16kHz, 2-32kHz, 3-48kHz, 4-44.1kHz, 5-8kHz
     */
    public byte byAudioSamplingRate;

    /**
     * Whether to enable high performance encoding (enabling high performance encoding when byVideoEncType is 1 means
     * Smart264; High performance encoding is enabled when byVideoEncType is 10, indicating Smart265) : 0- Off, 1- Open.
     * After enabling this function, the video encoding type (ByVideoenctype) is 1 (standard H264). When changing the
     * bit rate, you can configure the bit rate upper limit (DWVideoObitRate) and the average bit rate (WaverageVideoObitRate).
     * Maximum bit rate (DWVideobitRate) can be configured while average bit rate (WaverageIdeobitRate) is not valid for
     * fixed bit rate
     */
    public byte bySmartCodec;

    /**
     * Depth Map Enable Switch, 0- Off, 1- On; If enabled, the video parameters of the second channel's sub-code stream
     * (binocular) cannot be configured, and the default output is a depth map of 960*540
     */
    public byte byDepthMapEnable;

    /**
     * Video Average Bit Rate (with SmartCodec enabled) : 0-0K, 1-16K, 2-32K, 3-48K, 4-64K, 5-80K, 6-96K, 7-128K, 8-160K,
     * 9-192K, 10-224K, 11-256K, 12-320K, 13-384K, 14-448K, 15-512K, 16-640K, 17-768K, 18-896K, 19-1024K, 20-1280K,
     * 21-1536K, 22-1792K, 23-2048K, 24-2560K, 25-3072K, 26-4096K, 27-5120K, 28-6144K, 29-7168K, 30-8192 k.
     * The highest bit (15 bits) set to 1 indicates a custom stream, and 0~14 bits denote the stream value
     */
    public short wAverageVideoBitrate;

}
