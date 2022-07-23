package com.github.ipcam.hikvision;

import com.sun.jna.Structure;

/**
 * PRESET_NAME_STRUCTURE
 *
 * @author echils
 */
public class PRESET_NAME_STRUCTURE extends Structure {

    /**
     * The preset point name
     */
    public NET_DVR_PRESET_NAME[] name = new NET_DVR_PRESET_NAME[new NET_DVR_PRESET_NAME().size() * 256];

}
