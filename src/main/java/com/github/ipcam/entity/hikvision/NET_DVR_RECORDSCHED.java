package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_RECORDSCHED
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_RECORDSCHED extends Structure {
    public NET_DVR_SCHEDTIME struRecordTime = new NET_DVR_SCHEDTIME();
    public byte byRecordType;    //0:定时录像，1:移动侦测，2:报警录像，3:动测|报警，4:动测&报警, 5:命令触发, 6: 智能录像
    public byte[] reservedData = new byte[3];
}
