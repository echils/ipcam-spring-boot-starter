package com.github.ipcam;


import com.github.ipcam.entity.NetworkCamera;

/**
 * ICameraSupplier
 *
 * @author echils
 */
public interface ICameraSupplier<T> {


    /**
     * Get the network camera with the identification
     *
     * @param identification the unique identification of the camera
     * @return {@link NetworkCamera}
     */
    NetworkCamera apply(T identification);


}
