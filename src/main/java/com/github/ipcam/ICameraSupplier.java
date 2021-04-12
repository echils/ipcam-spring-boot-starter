package com.github.ipcam;


import com.github.ipcam.entity.NetworkCamera;

/**
 * ICameraSupplier
 *
 * @author echils
 * @since 2020-03-19 18:09
 */
public interface ICameraSupplier {


    /**
     * Get the network camera with the identification
     *
     * @param identification the unique identification of the camera
     * @return {@link NetworkCamera}
     */
    NetworkCamera apply(String identification);


}
