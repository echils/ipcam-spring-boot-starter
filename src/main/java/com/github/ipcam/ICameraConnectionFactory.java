package com.github.ipcam;


/**
 * ICameraConnectionFactory
 *
 * @author echils
 * @since 2020-03-19 18:09
 */
public interface ICameraConnectionFactory {


    /**
     * create connection of network camera
     *
     * @param camera connection network information.
     */
    ICameraConnection create(NetworkCamera camera) throws Exception;


    /**
     * destroy object
     *
     * @param connection
     */
    void destroy(ICameraConnection connection);


}
