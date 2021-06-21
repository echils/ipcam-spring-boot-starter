package com.github.ipcam;


import com.github.ipcam.support.*;

/**
 * ICameraConnection
 *
 * @author echils
 */
public interface ICameraConnection extends ICameraCloseable, ICameraBasicSupport, ICameraConfigSupport,
        ICameraNVRSupport, ICameraOutputSupport, ICameraPTZSupport, ICameraThermalSupport {

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
