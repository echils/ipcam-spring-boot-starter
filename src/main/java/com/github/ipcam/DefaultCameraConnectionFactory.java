package com.github.ipcam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * DefaultCameraConnectionFactory
 *
 * @author echils
 * @since 2020-03-23 16:46
 */
public class DefaultCameraConnectionFactory implements ICameraConnectionFactory {


    private Logger logger = LoggerFactory.getLogger(DefaultCameraConnectionFactory.class);


    @Override
    public ICameraConnection create(NetworkCamera camera) {
//        NetworkCameraConnection networkCameraConnection = new NetworkCameraConnection(camera);
//        logger.debug("Create connection success with network camera:{}", camera);
//        return networkCameraConnection;
        return null;
    }


    @Override
    public void destroy(ICameraConnection connection) {
        if (connection != null && connection.isConnected()) {
            connection.close();
        }
    }

}
