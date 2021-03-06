package com.github.ipcam;


import com.github.ipcam.entity.NetworkCamera;

/**
 * ICameraConnectionFactory
 *
 * @author echils
 */
public interface ICameraConnectionFactory {


    /**
     * create connection of network camera
     *
     * @param camera {@link NetworkCamera}
     */
    ICameraConnection create(NetworkCamera camera) throws Exception;


    /**
     * destroy object
     *
     * @param connection {@link ICameraConnection}
     */
    void destroy(ICameraConnection connection);


}
