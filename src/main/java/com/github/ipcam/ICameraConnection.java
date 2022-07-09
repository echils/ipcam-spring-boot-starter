package com.github.ipcam;


import com.github.ipcam.entity.NetworkCamera;
import com.github.ipcam.feature.*;

/**
 * ICameraConnection
 *
 * @author echils
 */
public interface ICameraConnection extends ICameraAutoCloseFeature, ICameraBasicFeature, ICameraConfigFeature,
        ICameraNVRFeature, ICameraOutputFeature, ICameraPTZFeature, ICameraThermalFeature {

    /**
     * connect to network camera
     *
     * @param networkCamera {@link NetworkCamera}
     */
    void connect(NetworkCamera networkCamera);

    /**
     * check session is connected
     */
    boolean isConnected();

    /**
     * check session is closed
     */
    boolean isClosed();

    /**
     * connection whether health
     */
    boolean isRobust();

    /**
     * Mark the connection as weak, and weak connections will be recycled
     */
    void makeWeak();

}
