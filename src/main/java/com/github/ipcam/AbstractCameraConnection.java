package com.github.ipcam;

import com.github.ipcam.entity.NetworkCamera;
import com.github.ipcam.entity.CameraDriver;

import java.io.OutputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * AbstractCameraConnection
 *
 * @author echils
 */
public abstract class AbstractCameraConnection implements ICameraConnection {


    /**
     * preview cache of network camera
     */
    static final Map<String, Map<Long, Map<String, Long>>> previewCache = new ConcurrentHashMap<>();

    /**
     * Audio output state of network camera
     */
    static final Map<String, Boolean> audioOutputSateCache = new ConcurrentHashMap<>();

    /**
     * Audio output handle of network camera
     */
    static final Map<String, Long> audioCameraOutputHandleCache = new ConcurrentHashMap<>();

    /**
     * Audio output stream of network camera
     */
    static final Map<String, OutputStream> audioOutputStreamManager = new ConcurrentHashMap<>();

    /**
     * Video output state of network camera
     */
    static final Map<String, Boolean> videoOutputSateCache = new ConcurrentHashMap<>();

    /**
     * Video output handle of network camera
     */
    static final Map<String, Long> videoOutputHandleCache = new ConcurrentHashMap<>();

    /**
     * Video output stream of network camera
     */
    static final Map<String, OutputStream> videoOutputStreamManager = new ConcurrentHashMap<>();

    /**
     * userHandle of the network camera
     */
    Long userHandle;

    /**
     * network camera info
     */
    NetworkCamera networkCamera;

    /**
     * healthy state
     */
    private boolean health = true;


    AbstractCameraConnection(NetworkCamera networkCamera) {
        this.networkCamera = networkCamera;
    }

    @Override
    public boolean isConnected() {
        return userHandle != null && userHandle >= 0;
    }

    @Override
    public boolean isClosed() {
        return !isConnected();
    }

    @Override
    public boolean isRobust() {
        return health;
    }

    @Override
    public void makeWeak() {
        this.health = false;
    }

    @Override
    public CameraDriver support() {
        return networkCamera.getDriverType();
    }

}
