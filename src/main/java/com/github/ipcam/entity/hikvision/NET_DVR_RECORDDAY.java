package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_RECORDDAY
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_RECORDDAY extends Structure {

    public short wAllDayRecord;                /* 是否全天录像 0-否 1-是*/
    public byte byRecordType;                /* 录象类型 0:定时录像，1:移动侦测，2:报警录像，3:动测|报警，4:动测&报警 5:命令触发, 6: 智能录像*/
    public byte reservedData;

}
