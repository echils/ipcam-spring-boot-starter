package com.github.ipcam;

import com.github.ipcam.entity.NetworkCamera;
import com.github.ipcam.entity.exception.CameraConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.OutputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.github.ipcam.entity.NetworkCameraContext.FAILED;

/**
 * AbstractCameraConnection
 *
 * @author echils
 * @since 2021-04-12 21:54:14
 */
public abstract class AbstractCameraConnection implements ICameraConnection, ICameraOutputSupport{


    private static final Logger logger = LoggerFactory.getLogger(AbstractCameraConnection.class);

    /**
     * preview cache of network camera
     *
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


    public AbstractCameraConnection(NetworkCamera networkCamera) {
        this.networkCamera = networkCamera;
    }

    @Override
    public void connect() {
        logger.info("Connecting to the network camera...");
        if (this.isConnected()) {
            logger.error("already connected to network camera with：{}", networkCamera);
            throw new CameraConnectionException("Already connected to network camera");
        }
        this.userHandle = this.login(networkCamera.getIp(), networkCamera.getPort(),
                networkCamera.getUsername(), networkCamera.getPassword());
        logger.info("Connect to the network camera success");
        if (userHandle < 0) {
            throw new CameraConnectionException("Connect to network camera failed");
        }
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
    public void close() throws CameraConnectionException {
        logger.info("disconnecting from the network camera {}...",networkCamera.getIp());
        if (this.isConnected()) {
            try {
                Map<Long, Map<String, Long>> userHandleMap = previewCache.get(networkCamera.getIp());
                if (userHandleMap != null && userHandleMap.size() != 0) {
                    Map<String, Long> previewMap = userHandleMap.get(userHandle);
                    if (previewMap != null && previewMap.size() != 0) {
                        previewMap.forEach((k, v) -> this.release(k));
                        previewMap.clear();
                    }
                    userHandleMap.remove(userHandle);
                }

                this.logout();
                userHandle = (long) FAILED;
                logger.info("disconnect from the network camera success");
            } catch (Exception e) {
                logger.error("disconnect from the network camera failed:{}", networkCamera.getIp());
                throw new CameraConnectionException(e);
            }
        }
    }

    @Override
    public CameraSupportedDriver support() {
        return networkCamera.getDriverType();
    }

}
