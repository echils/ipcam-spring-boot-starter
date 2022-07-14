package com.github.ipcam.sample.service;

import com.github.ipcam.ICameraConnection;
import com.github.ipcam.entity.infos.CameraInfo;
import com.github.ipcam.pool.CameraConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * DefaultCameraService
 *
 * @author echils
 */
@Service
public class DefaultCameraService {

    @Autowired
    private CameraConnectionPool cameraConnectionPool;

    private static final String IDENTIFICATION = "Test";


    public CameraInfo getCameraBasicInfo() {
        ICameraConnection connection = null;
        try {
            connection = cameraConnectionPool.borrowObject(IDENTIFICATION);
            return connection.getBasicInfo();
        } finally {
            if (connection != null && connection.isConnected()) {
                cameraConnectionPool.returnObject(IDENTIFICATION, connection);
            }
        }
    }


    public List<String> getChannels() {
        ICameraConnection connection = null;
        try {
            connection = cameraConnectionPool.borrowObject(IDENTIFICATION);
            return connection.getChannels();
        } finally {
            if (connection != null && connection.isConnected()) {
                cameraConnectionPool.returnObject(IDENTIFICATION, connection);
            }
        }
    }


}
