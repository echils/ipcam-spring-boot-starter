package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * PresetName
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class PresetName extends Structure {

    public NET_DVR_PRESET_NAME[] name = new NET_DVR_PRESET_NAME[new NET_DVR_PRESET_NAME().size() * 256];
}
