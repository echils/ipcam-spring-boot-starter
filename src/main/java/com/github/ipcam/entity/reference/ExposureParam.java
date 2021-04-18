package com.github.ipcam.entity.reference;

/**
 * ExposureParam
 *
 * @author echils
 * @since 2020-03-19 13:36
 */
public enum ExposureParam {

    EXPOSURE_LEVEL("曝光等级（曝光模式为自动、光圈优先、快门优先情况有效），取值范围：等级1~5，默认为4"),
    MAX_IRIS("最大光圈限制值(在自动曝光模式下生效)，取值范围：[0,100] "),
    MIN_IRIS("最小光圈限制值(在自动曝光模式下生效)，取值范围：[0,100] "),
    MAX_SHUTTER("最大快门限制(在自动曝光模式下生效)"),
    MIN_SHUTTER("最小快门限制(在自动曝光模式下生效)");


    private String desc;

    ExposureParam(String desc) {
        this.desc = desc;
    }
}
