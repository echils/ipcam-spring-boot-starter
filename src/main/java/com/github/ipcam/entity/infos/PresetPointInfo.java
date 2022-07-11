package com.github.ipcam.entity.infos;

import lombok.Data;

/**
 * PresetPointInfo
 *
 * @author echils
 */
@Data
public class PresetPointInfo {

    /**
     * The preset point code of camera
     */
    private int id;

    /**
     * The preset name of camera
     */
    private String presetName;

    /**
     * Has it been used,onvif driver maybe is null
     */
    private Boolean enabled;

}
