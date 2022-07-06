package com.github.ipcam;


import com.github.ipcam.feature.*;

/**
 * ICameraConnection
 *
 * @author echils
 */
public interface ICameraConnection extends ICameraCloseable, ICameraBasicFeature, ICameraConfigFeature,
        ICameraNVRFeature, ICameraOutputFeature, ICameraPTZFeature, ICameraThermalFeature {

    /**
     * connect to network camera
     */
    void connect();

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
     * Mark the connection as weak, and weak connections will be recovered
     */
    void makeWeak();

}
