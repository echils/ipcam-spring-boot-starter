package com.github.ipcam.entity.hikvision;

import com.github.ipcam.entity.comm.STRUCTURE_CONTEXT;
import com.sun.jna.Structure;

/**
 * NET_DVR_THERMOMETRY_PRESETINFO
 *
 * @author echils
 */
public class NET_DVR_THERMOMETRY_PRESETINFO extends Structure {

    /**
     * Structure size
     */
    public int dwSize;

    /**
     * Preset point number
     */
    public short wPresetNo;

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes = new byte[2];

    public NET_DVR_THERMOMETRY_PRESETINFO_PARAM[] struPresetInfo =
            new NET_DVR_THERMOMETRY_PRESETINFO_PARAM[STRUCTURE_CONTEXT.MAX_THERMOMETRY_REGION_NUM];

}
