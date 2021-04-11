package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_THERMOMETRY_PRESETINFO
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_THERMOMETRY_PRESETINFO extends Structure {

    public int dwSize;
    public short wPresetNo;
    public byte[] byRes = new byte[2];
    public NET_DVR_THERMOMETRY_PRESETINFO_PARAM[] struPresetInfo = new NET_DVR_THERMOMETRY_PRESETINFO_PARAM[NetworkCameraContext.MAX_THERMOMETRY_REGION_NUM];

}
