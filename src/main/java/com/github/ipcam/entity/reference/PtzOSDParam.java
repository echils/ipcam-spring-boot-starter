package com.github.ipcam.entity.reference;

/**
 * PtzOSDParam
 *
 * @author echils
 * @since 2020-05-15 15:18
 */
public enum PtzOSDParam {
    /**
     * 镜头倍数显示: 1- 2秒，2- 5秒，3- 10秒，0xff- 常关，0- 常开，默认：2秒
     */
    ZOOM_STATUS,

    /**
     * 方位角显示: 1- 2秒，2- 5秒，3- 10秒，0xff- 常关，0- 常开，默认：2秒
     */
    PT_STATUS,

    /**
     * 预置点标题显示: 1- 2秒，2- 5秒，3- 10秒，0xff- 常关，0- 常开，默认：2秒
     */
    PRESET_STATUS
}
