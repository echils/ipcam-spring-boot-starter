package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;


/**
 * NET_DVR_RECORD_V30
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_RECORD_V30 extends Structure {

    public int dwSize;
    public int dwRecord;                        /*是否录像 0-否 1-是*/
    public NET_DVR_RECORDDAY[] struRecAllDay = new NET_DVR_RECORDDAY[NetworkCameraContext.MAX_DAYS];
    public NET_DVR_RECORDSCHEDWEEK[] struRecordSched = new NET_DVR_RECORDSCHEDWEEK[NetworkCameraContext.MAX_DAYS];
    public int dwRecordTime;                    /* 录象延时长度 0-5秒， 1-20秒， 2-30秒， 3-1分钟， 4-2分钟， 5-5分钟， 6-10分钟*/
    public int dwPreRecordTime;                /* 预录时间 0-不预录 1-5秒 2-10秒 3-15秒 4-20秒 5-25秒 6-30秒 7-0xffffffff(尽可能预录) */
    public int dwRecorderDuration;                /* 录像保存的最长时间 */
    public byte byRedundancyRec;    /*是否冗余录像,重要数据双备份：0/1*/
    public byte byAudioRec;        /*录像时复合流编码时是否记录音频数据：国外有此法规*/
    public byte byStreamType;   //码流类型
    public byte byPassbackRecord;   //录像回传
    public byte wLockDuration;   //录像锁定时长，单位：小时，0表示不锁定，0xffff表示永久锁定
    public byte byRecordBackup;   //录像存档
    public byte byRecordManage;   //录像调度
    public byte byExtraSaveAudio;   //是否单独存储音频录像
    public byte[] byReserve = new byte[10];
}