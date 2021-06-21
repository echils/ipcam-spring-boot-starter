package com.github.ipcam;

import com.github.ipcam.entity.NetworkCamera;
import com.github.ipcam.entity.exception.CameraConnectionException;
import com.github.ipcam.support.CameraSupportedDriver;

import static com.github.ipcam.support.CameraSupportedDriver.HIKVISION;
import static com.github.ipcam.support.CameraSupportedDriver.XMEYE;


/**
 * DefaultCameraConnectionFactory
 *
 * @author echils
 */
public class DefaultCameraConnectionFactory implements ICameraConnectionFactory {


    @Override
    public ICameraConnection create(NetworkCamera camera) {
        ICameraConnection cameraConnection;
        CameraSupportedDriver driverType = camera.getDriverType();
        if (HIKVISION == driverType) {
            cameraConnection = new HikvisionCameraConnection(camera);
        } else if (XMEYE == driverType) {
            cameraConnection = new XmEyeCameraConnection(camera);
        } else {
            throw new CameraConnectionException("Unsupported driver");
        }
        cameraConnection.connect();
        return cameraConnection;
    }


    @Override
    public void destroy(ICameraConnection connection) {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

}
