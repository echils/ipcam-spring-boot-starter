package com.github.ipcam.entity;

import lombok.Data;

/**
 * PresetPointInfo
 *
 * @author echils
 * @since 2021-04-11 15:07:00
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
     * Has it been used
     */
    private boolean enabled;

}
