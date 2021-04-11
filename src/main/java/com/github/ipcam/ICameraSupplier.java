package com.github.ipcam;


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
     * @return
     */
    NetworkCamera apply(String identification);


}
