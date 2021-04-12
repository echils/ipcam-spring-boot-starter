package com.github.ipcam;


/**
 * ICameraConnection
 *
 * @author echils
 * @since 2020-03-27 16:11
 */
public interface ICameraConnection extends ICameraCloseable, ICameraSupportDriver, ICameraBasicFeature {

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
