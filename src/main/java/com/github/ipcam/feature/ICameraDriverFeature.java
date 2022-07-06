package com.github.ipcam.feature;


import com.github.ipcam.entity.CameraDriver;

/**
 * ICameraDriverFeature
 *
 * @author echils
 */
public interface ICameraDriverFeature {

    /**
     * Network camera support
     *
     * @return {@link CameraDriver}
     */
    CameraDriver support();

}
