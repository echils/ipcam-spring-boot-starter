package com.github.ipcam.connection;

import com.github.ipcam.ICameraConnection;
import com.github.ipcam.ICameraConnectionFactory;
import com.github.ipcam.entity.NetworkCamera;
import com.github.ipcam.exception.CameraConnectionException;

import java.util.ServiceLoader;


/**
 * DefaultCameraConnectionFactory
 *
 * @author echils
 */
public class DefaultCameraConnectionFactory implements ICameraConnectionFactory {


    @Override
    public ICameraConnection create(NetworkCamera camera) {
        for (AbstractCameraConnection connection : ServiceLoader.load(AbstractCameraConnection.class)) {
            if (connection.support() == camera.getDriverType()) {
                connection.connect(camera);
                return connection;
            }
        }
        throw new CameraConnectionException("Unsupported driver");
    }


    @Override
    public void destroy(ICameraConnection connection) {
        if (connection != null && !connection.isClosed()) connection.close();
    }

}
