package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * PRESET_NAME_STRUCTURE
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class PRESET_NAME_STRUCTURE extends Structure {

    /**
     * The preset point name
     */
    public NET_DVR_PRESET_NAME[] name = new NET_DVR_PRESET_NAME[new NET_DVR_PRESET_NAME().size() * 256];

}
