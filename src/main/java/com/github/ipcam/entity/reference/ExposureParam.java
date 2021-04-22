package com.github.ipcam.entity.reference;

/**
 * ExposureParam
 *
 * @author echils
 * @since 2020-03-19 13:36
 */
public enum ExposureParam {

    /**
     * Exposure level (exposure mode is automatic, aperture priority,
     * shutter priority is valid), value range: level 1 to 5, the default is 4
     */
    EXPOSURE_LEVEL,

    /**
     * Maximum aperture limit (in automatic exposure mode), range: [0,100]
     */
    MAX_IRIS,

    /**
     * Minimum aperture limit (in automatic exposure mode), range: [0,100]
     */
    MIN_IRIS,

    /**
     * Maximum Shutter Limit (in Auto Exposure mode)
     */
    MAX_SHUTTER,

    /**
     * Minimum Shutter Limit (in Auto Exposure mode)
     */
    MIN_SHUTTER

}
