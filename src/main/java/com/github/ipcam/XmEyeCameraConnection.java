package com.github.ipcam;

import com.github.ipcam.entity.CameraDriver;
import com.github.ipcam.entity.NetworkCamera;
import com.github.ipcam.entity.comm.BYTE_ARRAY_STRUCTURE;
import com.github.ipcam.entity.exception.CameraConnectionException;
import com.github.ipcam.entity.exception.XmEyeException;
import com.github.ipcam.entity.infos.CameraInfo;
import com.github.ipcam.entity.reference.ResolutionEnum;
import com.github.ipcam.entity.reference.StreamTypeEnum;
import com.github.ipcam.entity.xmeye.CONF_MODIFY_PSW;
import com.github.ipcam.entity.xmeye.H264_DVR_CLIENTINFO;
import com.github.ipcam.entity.xmeye.H264_DVR_DEVICEINFO;
import com.sun.jna.ptr.IntByReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static com.github.ipcam.entity.comm.STRUCTURE_CONTEXT.*;
import static com.github.ipcam.entity.xmeye.NetSDK.netSDK;
import static com.github.ipcam.entity.xmeye.XNetSDK.xNetSDK;
import static com.github.ipcam.entity.xmeye.XmEyeManager.getErrorMsg;
import static com.github.ipcam.entity.xmeye.XmEyeManager.handleChannel;
import static com.github.ipcam.utils.FileUtils.getFileSuffix;
import static com.github.ipcam.utils.GraphicsUtils.createNewImage;

/**
 * XmEyeCameraConnection
 *
 * @author echils
 */
public class XmEyeCameraConnection extends AbstractCameraConnection {


    private static final Logger logger = LoggerFactory.getLogger(XmEyeCameraConnection.class);

    private H264_DVR_DEVICEINFO deviceInfo = new H264_DVR_DEVICEINFO();

    @Override
    public void connect(NetworkCamera camera) {
        logger.info("Connecting to the xmeye camera...");
        if (this.isConnected()) {
            logger.error("Already connected to xmeye camera with：{}", networkCamera);
            throw new CameraConnectionException("Already connected to xmeye camera");
        }
        if (camera == null || camera.isIllegal()) {
            logger.error("The xmeye camera is illegal：{}", camera);
            throw new CameraConnectionException("The xmeye camera is illegal");
        }
        this.userHandle = this.login(camera.getIp(), camera.getPort(),
                camera.getUsername(), camera.getPassword());
        if (userHandle < 0) {
            throw new CameraConnectionException("Connect to xmeye camera failed");
        }
        logger.info("Connect to the xmeye camera success");
        this.networkCamera = camera;
    }


    @Override
    public void close() throws CameraConnectionException {
        logger.info("Disconnecting from the xmeye camera {}...", networkCamera.getIp());
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
            } catch (Exception e) {
                logger.error("Disconnect from the xmeye camera failed:{}", networkCamera.getIp());
                throw new CameraConnectionException(e);
            }
        }
        logger.info("Disconnected from the xmeye camera {}", networkCamera.getIp());
    }


    /**
     * Login into network camera
     *
     * @param ip       The ip of camera
     * @param port     The port of camera
     * @param username The username of camera
     * @param password The password of camera
     */
    public long login(String ip, int port, String username, String password) {
        return this.login(ip, port, username, password, LOGIN_DEFAULT_WAIT_TIME, LOGIN_DEFAULT_TRY_TIME);
    }


    /**
     * Login into network camera with waitTime and tryTime
     *
     * @param ip       The ip of camera
     * @param port     The port of camera
     * @param username The username of camera
     * @param password The password of camera
     * @param waitTime The waitTime of camera
     * @param tryTime  The tryTime of camera
     */
    public long login(String ip, int port, String username, String password, int waitTime, int tryTime) {

        IntByReference error = new IntByReference(MEANINGLESS);

        //Device initialize
        if (netSDK.H264_DVR_Init(null, USER) <= XMEYE_FAILED) {
            throw new XmEyeException(getErrorMsg());
        }

        //Set the network connection timeout and the number of connection attempts.
        if (!netSDK.H264_DVR_SetConnectTime(waitTime, tryTime)) {
            throw new XmEyeException(getErrorMsg());
        }

        //Device register
        long userHandle = netSDK.H264_DVR_Login(ip, port, username, password, deviceInfo, error, TCPSOCKET);
        if (userHandle <= XMEYE_FAILED) {
            if (error.getValue() != MEANINGLESS) {
                throw new XmEyeException(getErrorMsg(error.getValue()));
            }
            throw new XmEyeException(getErrorMsg());
        }
        deviceInfo.read();
        this.userHandle = userHandle;
        return userHandle;
    }


    /**
     * Logout from network camera
     */
    public void logout() {
        if (netSDK.H264_DVR_Logout(userHandle) == ACTION) {
            if (!netSDK.H264_DVR_Cleanup()) {
                throw new XmEyeException(getErrorMsg());
            }
        }
    }


    @Override
    public List<String> getChannels() {
        return Collections.singletonList(DEFAULT_DVR_CHANNEL);
    }


    /**
     * Get a preview handle from camera
     *
     * @param channel    The channel of network camera
     * @param streamType {@link StreamTypeEnum}
     */
    public long preview(String channel, StreamTypeEnum streamType) {
        Map<Long, Map<String, Long>> userHandleMap = previewCache.computeIfAbsent(networkCamera.getIp(),
                ip -> new ConcurrentHashMap<>());
        Map<String, Long> previewMap = userHandleMap.computeIfAbsent(userHandle,
                user -> new ConcurrentHashMap<>());

        return previewMap.computeIfAbsent(channel, c -> {
            //device config
            H264_DVR_CLIENTINFO clientInfo = new H264_DVR_CLIENTINFO();
            clientInfo.nChannel = handleChannel(channel);
            clientInfo.nStream = streamType.key();
            clientInfo.nMode = TCPSOCKET;

            //start play
            long realPlayHandle = netSDK.H264_DVR_RealPlay(userHandle, clientInfo);
            if (realPlayHandle == FAILED) {
                throw new XmEyeException(getErrorMsg());
            }
            return realPlayHandle;
        });
    }


    /**
     * Release the preview handle of the camera
     *
     * @param channel The channel of network camera
     */
    public void release(String channel) {
        Map<Long, Map<String, Long>> userHandleMap = previewCache.computeIfAbsent(networkCamera.getIp(),
                ip -> new ConcurrentHashMap<>());
        Map<String, Long> previewMap = userHandleMap.computeIfAbsent(userHandle,
                user -> new ConcurrentHashMap<>());
        if (previewMap.containsKey(channel)) {
            if (!netSDK.H264_DVR_StopRealPlay(previewMap.get(channel), null)) {
                throw new XmEyeException(getErrorMsg());
            }
        }
    }


    @Override
    public void imageOutput(String channel, String targetPath) {
        String tmpPath = System.getProperty("java.io.tmpdir") + File.separator +
                UUID.randomUUID().toString().replaceAll("-", "") +
                getFileSuffix(targetPath);
        if (!netSDK.H264_DVR_CatchPic(userHandle, handleChannel(channel), tmpPath, USER)) {
            throw new XmEyeException(getErrorMsg());
        }
        String[] params = ResolutionEnum.PIXEL1080P.getResolution().split(ResolutionEnum.RESOLUTION_SEPARATOR);
        if (!createNewImage(new File(tmpPath), Integer.parseInt(params[0]), Integer.parseInt(params[1]), targetPath)) {
            logger.error("Xmeye image output failed:{}", networkCamera.getIp());
            throw new CameraConnectionException("Xmeye runtime error");
        }
    }


    @Override
    public void changePassword(String channel, String newPassword) {
        try {
            BYTE_ARRAY_STRUCTURE BYTEARRAYSTRUCTURE = new BYTE_ARRAY_STRUCTURE(NET_MAX_MAC_LEN);
            BYTEARRAYSTRUCTURE.write();
            if (!xNetSDK.XSDK_EncryptPassword(newPassword, BYTEARRAYSTRUCTURE.getPointer(), newPassword.length())) {
                throw new XmEyeException(getErrorMsg());
            }
            BYTEARRAYSTRUCTURE.read();
            newPassword = new String(BYTEARRAYSTRUCTURE.byValue).trim();

            BYTEARRAYSTRUCTURE = new BYTE_ARRAY_STRUCTURE(NET_MAX_MAC_LEN);
            BYTEARRAYSTRUCTURE.write();
            if (!xNetSDK.XSDK_EncryptPassword(networkCamera.getPassword(),
                    BYTEARRAYSTRUCTURE.getPointer(), networkCamera.getPassword().length())) {
                throw new XmEyeException(getErrorMsg());
            }
            BYTEARRAYSTRUCTURE.read();
            String oldPassword = new String(BYTEARRAYSTRUCTURE.byValue).trim();

            CONF_MODIFY_PSW psw = new CONF_MODIFY_PSW();
            psw.sUserName = getBytes(networkCamera.getUsername(), NET_MAX_MAC_LEN);
            psw.Password = getBytes(oldPassword, NET_MAX_MAC_LEN);
            psw.NewPassword = getBytes(newPassword, NET_MAX_MAC_LEN);
            psw.write();

            if (!netSDK.H264_DVR_SetDevConfig(userHandle, E_SDK_CONFIG_MODIFY_PSW, handleChannel(channel), psw, psw.size())) {
                throw new XmEyeException(getErrorMsg());
            }
        } finally {
            netSDK.H264_DVR_Logout(userHandle);
        }
    }


    @Override
    public CameraInfo getBasicInfo() {
        CameraInfo cameraInfo = new CameraInfo();
        cameraInfo.setIp(networkCamera.getIp());
        cameraInfo.setName(networkCamera.getIp());
        cameraInfo.setUsername(networkCamera.getUsername());
        cameraInfo.setPassword(networkCamera.getPassword());
        cameraInfo.setModelNo(new String(deviceInfo.sHardWare).trim());
        cameraInfo.setSerialNo(new String(deviceInfo.sSerialNumber).trim());
        return cameraInfo;
    }


    @Override
    public CameraDriver support() {
        return CameraDriver.XMEYE;
    }


    private byte[] getBytes(String str, int arrLen) {
        byte[] result = new byte[arrLen];
        byte[] strBytes = str.getBytes();
        if (arrLen < strBytes.length) {
            throw new CameraConnectionException("Array length is small");
        }
        for (int i = 0; i < strBytes.length; i++) {
            result[i] = strBytes[i];
        }
        return result;
    }

}
