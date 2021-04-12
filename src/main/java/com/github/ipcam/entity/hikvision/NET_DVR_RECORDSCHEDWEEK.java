package com.github.ipcam.entity.hikvision;

import com.github.ipcam.entity.NetworkCameraContext;
import com.sun.jna.Structure;


/**
 * NET_DVR_RECORDSCHEDWEEK
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_RECORDSCHEDWEEK extends Structure {
    public NET_DVR_RECORDSCHED[] struRecordSched = new NET_DVR_RECORDSCHED[NetworkCameraContext.MAX_TIMESEGMENT_V30];
}
