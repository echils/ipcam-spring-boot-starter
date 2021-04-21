package com.github.ipcam.entity.hikvision;

import com.github.ipcam.entity.comm.StructureContext;
import com.sun.jna.Structure;

/**
 * NET_DVR_THERMOMETRY_PRESETINFO
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_THERMOMETRY_PRESETINFO extends Structure {

    /**
     * Structure size
     */
    public int dwSize;

    public short wPresetNo;

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes = new byte[2];

    public NET_DVR_THERMOMETRY_PRESETINFO_PARAM[] struPresetInfo =
            new NET_DVR_THERMOMETRY_PRESETINFO_PARAM[StructureContext.MAX_THERMOMETRY_REGION_NUM];

}
