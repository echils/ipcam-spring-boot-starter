package com.github.ipcam;


import com.github.ipcam.support.*;

/**
 * ICameraConnection
 *
 * @author echils
 * @since 2020-03-27 16:11
 */
public interface ICameraConnection extends ICameraCloseable, ICameraSupportDriver, ICameraBasicSupport,
        ICameraConfigSupport, ICameraNVRSupport, ICameraOutputSupport, ICameraPTZSupport, ICameraThermalSupport {

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
