package com.github.ipcam;

import com.github.ipcam.entity.*;
import com.github.ipcam.entity.comm.ByteArrayStructure;
import com.github.ipcam.entity.exception.CameraConnectionException;
import com.github.ipcam.entity.exception.HikException;
import com.github.ipcam.entity.hikvision.*;
import com.github.ipcam.entity.infos.CameraInfo;
import com.github.ipcam.entity.infos.NVRChannelInfo;
import com.github.ipcam.entity.infos.PresetPointInfo;
import com.github.ipcam.entity.reference.*;
import com.github.ipcam.support.CameraSupportedDriver;
import com.github.ipcam.support.ICameraNVRSupport;
import com.github.ipcam.support.ICameraPTZSupport;
import com.github.ipcam.support.ICameraThermalSupport;
import com.github.ipcam.utils.XmlToJsonUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.IntByReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.github.ipcam.entity.comm.StructureContext.*;
import static com.github.ipcam.entity.hikvision.HCNetSDK.hcNetSDK;
import static com.github.ipcam.entity.hikvision.NetCommonInvoke.*;
import static com.github.ipcam.utils.FileUtils.createParentDirectory;
import static java.util.Calendar.HOUR_OF_DAY;

/**
 * HikvisionCameraConnection
 *
 * @author echils
 * @since 2021-04-12 22:09:49
 */
public class HikvisionCameraConnection extends AbstractCameraConnection implements ICameraPTZSupport,
        ICameraThermalSupport, ICameraNVRSupport {


    private static final Logger logger = LoggerFactory.getLogger(HikvisionCameraConnection.class);

    public HikvisionCameraConnection(NetworkCamera networkCamera) {
        super(networkCamera);
    }


    @Override
    public void connect() {
        logger.info("Connecting to the hikvision camera...");
        if (this.isConnected()) {
            logger.error("Already connected to hikvision camera with：{}", networkCamera);
            throw new CameraConnectionException("Already connected to hikvision camera");
        }
        this.userHandle = this.login(networkCamera.getIp(), networkCamera.getPort(),
                networkCamera.getUsername(), networkCamera.getPassword());
        logger.info("Connect to the hikvision camera success");
        if (userHandle < 0) {
            throw new CameraConnectionException("Connect to hikvision camera failed");
        }
    }


    @Override
    public void close() throws CameraConnectionException {
        logger.info("Disconnecting from the hikvision camera {}...", networkCamera.getIp());
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
                logger.info("Disconnect from the hikvision camera success");
            } catch (Exception e) {
                logger.error("Disconnect from the hikvision camera failed:{}", networkCamera.getIp());
                throw new CameraConnectionException(e);
            }
        }
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
        //Device initialize
        if (!hcNetSDK.NET_DVR_Init()) {
            throw new HikException(getErrorMsg());
        }
        //Set the network connection timeout and the number of connection attempts.
        if (!hcNetSDK.NET_DVR_SetConnectTime(waitTime, tryTime)) {
            throw new HikException(getErrorMsg());
        }
        //Device register
        int userHandle = hcNetSDK.NET_DVR_Login_V30(ip, port, username, password, deviceInfo);
        if (userHandle == FAILED) {
            throw new HikException(getErrorMsg());
        }
        this.userHandle = (long) userHandle;
        return userHandle;
    }


    /**
     * Logout from network camera
     */
    public void logout() {
        if (hcNetSDK.NET_DVR_Logout(Math.toIntExact(userHandle))) {
            if (!hcNetSDK.NET_DVR_Cleanup()) {
                throw new HikException(getErrorMsg());
            }
        }
    }

    @Override
    public List<String> getChannels() {
        List<String> channelList = new LinkedList<>();
        //get the device reference config
        NET_DVR_IPPARACFG param = new NET_DVR_IPPARACFG();
        param.write();
        boolean ipSupport = hcNetSDK.NET_DVR_GetDVRConfig(Math.toIntExact(userHandle), NET_DVR_GET_IPPARACFG, 0,
                param.getPointer(), param.size(), new IntByReference(DEFAULT_BUFFER));
        param.read();
        if (!ipSupport) {
            //analog channel
            for (int channel = INIT; channel < deviceInfo.byChanNum; channel++) {
                channelList.add("A" + (channel + deviceInfo.byStartChan));
            }
        } else {
            //analog channel
            for (int channel = INIT; channel < deviceInfo.byChanNum; channel++) {
                if (param.byAnalogChanEnable[channel] == 1) {
                    channelList.add("I" + (channel + deviceInfo.byStartChan));
                }
            }
            //ip channel
            for (int channel = INIT; channel < MAX_IP_CHANNEL; channel++) {
                if (param.struIPChanInfo[channel].byEnable == 1) {
                    channelList.add("D" + (channel + deviceInfo.byStartChan));
                }
            }
        }
        logger.info("Camera {} current channel list:{}", networkCamera.getIp(), channelList);
        return channelList;
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

        Long previewHandle = previewMap.computeIfAbsent(channel, c -> {
            LPNET_DVR_PREVIEWINFO previewInfo = new LPNET_DVR_PREVIEWINFO();
            //get real channel
            previewInfo.lChannel = handleChannel(channel);

            //choose the stream type
            previewInfo.hPlayWnd = null;
            previewInfo.dwStreamType = streamType.key();

            //get the preview handle
            previewInfo.write();
            int realPlayV40 = hcNetSDK.NET_DVR_RealPlay_V40(Math.toIntExact(userHandle),
                    previewInfo, null, null);
            if (realPlayV40 == FAILED) {
                throw new HikException(getErrorMsg());
            }
            return (long) realPlayV40;
        });

        AtomicInteger count = new AtomicInteger();
        userHandleMap.forEach((user, map) -> count.addAndGet(map.size()));
        logger.info("Camera {} current number of previews occupied:{}", networkCamera.getIp(), count.get());
        return previewHandle;
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
            if (!hcNetSDK.NET_DVR_StopRealPlay(Math.toIntExact(previewMap.get(channel)))) {
                throw new HikException(getErrorMsg());
            }
        }
    }


    @Override
    public void changePassword(String channel, String newPassword) {
        if ("".equals(newPassword) || null == newPassword) {
            throw new CameraConnectionException("New password can not be blank");
        }
        newPassword = newPassword.trim();
        if (newPassword.length() > PASSWD_LEN || newPassword.length() < MIN_PASSWD_LEN) {
            throw new CameraConnectionException("New password length must be between 8-16");
        }

        NET_DVR_USER_V30 param = new NET_DVR_USER_V30();
        if (!hcNetSDK.NET_DVR_GetDVRConfig(userHandle.intValue(), NET_DVR_GET_USERCFG_V30, handleChannel(channel),
                param.getPointer(), ISAPI_DATA_LEN, new IntByReference(param.size()))) {
            throw new HikException(getErrorMsg());
        }
        param.read();
        param.struUser[0].sPassword = new byte[PASSWD_LEN];
        param.struUser[0].sPassword = newPassword.getBytes();
        param.write();
        if (!hcNetSDK.NET_DVR_SetDVRConfig(userHandle.intValue(), NET_DVR_SET_USERCFG_V30, handleChannel(channel),
                param, param.size())) {
            throw new HikException(getErrorMsg());
        }
    }

    @Override
    public CameraInfo getBasicInfo(String channel) {
        NET_DVR_DEVICECFG_V40 deviceConfig = new NET_DVR_DEVICECFG_V40();
        deviceConfig.dwSize = deviceConfig.size();
        deviceConfig.write();
        if (!hcNetSDK.NET_DVR_GetDVRConfig(userHandle.intValue(), NET_DVR_GET_DEVICECFG_V40, handleChannel(channel),
                deviceConfig.getPointer(), ISAPI_DATA_LEN, new IntByReference(deviceConfig.size()))) {
            throw new HikException(getErrorMsg());
        }
        CameraInfo cameraInfo = new CameraInfo();
        deviceConfig.read();
        cameraInfo.setIp(networkCamera.getIp());
        cameraInfo.setUsername(networkCamera.getUsername());
        cameraInfo.setPassword(networkCamera.getPassword());
        cameraInfo.setName(new String(deviceConfig.sDVRName).trim());
        cameraInfo.setModelNo(new String(deviceConfig.byDevTypeName).trim());
        cameraInfo.setSerialNo(new String(deviceConfig.sSerialNumber).trim());
        cameraInfo.setManufacturer(CameraSupportedDriver.HIKVISION);
        return cameraInfo;
    }

    @Override
    public void imageOutput(String channel, String targetPath) {
        createParentDirectory(targetPath);
        if (!hcNetSDK.NET_DVR_CaptureJPEGPicture(Math.toIntExact(userHandle), handleChannel(channel),
                new NET_DVR_JPEGPARA(PICTURE_SIZE, PICTURE_QUALITY), targetPath)) {
            throw new HikException(getErrorMsg());
        }
    }

    @Override
    public void videoDownload(String channel, Date startTime, Date endTime, String targetPath) throws InterruptedException {
        logger.info("Camera:{} video output with channel:{} startTime:{} endTime{}",
                networkCamera.getIp(), channel, startTime, endTime);
        if (findRecord(Math.toIntExact(userHandle), channel, startTime, endTime) == NET_DVR_FILE_NOFIND) {
            logger.warn("No video to download with ip:{} channel:{} startTime:{} endTime{}",
                    networkCamera.getIp(), channel, startTime, endTime);
            return;
        }
        // set the file splitSize
        NET_DVR_LOCAL_GENERAL_CFG cfg = new NET_DVR_LOCAL_GENERAL_CFG();
        cfg.byNotSplitRecordFile = SPLIT;
        hcNetSDK.NET_DVR_SetSDKLocalCfg(NET_DVR_LOCAL_CFG_TYPE_GENERAL, cfg);

        //start to download file
        createParentDirectory(targetPath);
        int downloadHandle = hcNetSDK.NET_DVR_GetFileByTime(Math.toIntExact(userHandle), handleChannel(channel),
                transferTime(startTime), transferTime(endTime), targetPath);
        if (downloadHandle == FAILED) {
            throw new HikException(getErrorMsg());
        } else {
            //breakpoint continuation is not supported
            boolean result = hcNetSDK.NET_DVR_PlayBackControl(downloadHandle, DownloadEnum.NET_DVR_PLAYSTART.key(),
                    INIT, null);
            if (!result) {
                throw new HikException(getErrorMsg());
            }
        }
        boolean downloading = true;
        while (downloading) {
            int number = hcNetSDK.NET_DVR_GetDownloadPos(downloadHandle);
            logger.debug("video startTime:{} to endTime:{} download progress:{}", startTime, endTime, number);
            if (number == FAILED) {
                throw new HikException(getErrorMsg());
            } else if (number != COMPLETED) {
                Thread.sleep(1000);
            } else {
                downloading = false;
            }
        }
        if (!hcNetSDK.NET_DVR_StopGetFile(downloadHandle)) {
            throw new HikException(getErrorMsg());
        }
    }

    @Override
    public void audioOutput(String targetPath, int duration) throws InterruptedException {

        //get compress param
        NET_DVR_COMPRESSION_AUDIO compressionAudio = new NET_DVR_COMPRESSION_AUDIO();
        if (!hcNetSDK.NET_DVR_GetCurrentAudioCompress(Math.toIntExact(userHandle), compressionAudio)) {
            throw new HikException(getErrorMsg());
        }

        if (compressionAudio.byAudioEncType != PCM && compressionAudio.byAudioEncType != AAC) {
            throw new CameraConnectionException("The current version only support PCM or AAC");
        }

        //voice send
        int voiceHandle = hcNetSDK.NET_DVR_StartVoiceCom_MR_V30(Math.toIntExact(userHandle), AUDIO_CHANNEL, null, null);
        if (voiceHandle == FAILED) {
            throw new HikException(getErrorMsg());
        }

        //write data
        String audioHandle = UUID.randomUUID().toString().replaceAll("-", "");
        try {
            audioOutputSateCache.put(audioHandle, true);
            createParentDirectory(targetPath);
            OutputStream outputStream = new FileOutputStream(new File(targetPath));
            audioOutputStreamManager.put(audioHandle, outputStream);
            VoiceDataCallBack callBack = new VoiceDataCallBack(outputStream, voiceHandle, audioHandle);
            if (!hcNetSDK.NET_DVR_SetVoiceDataCallBack(voiceHandle, true, callBack, null)) {
                throw new HikException(getErrorMsg());
            }
            audioCameraOutputHandleCache.put(audioHandle, (long) voiceHandle);
            Thread.sleep(duration);

            if (audioCameraOutputHandleCache.containsKey(audioHandle)) {
                if (!hcNetSDK.NET_DVR_StopVoiceCom(Math.toIntExact(audioCameraOutputHandleCache.get(audioHandle)))) {
                    throw new HikException(getErrorMsg());
                }
            }
        } catch (FileNotFoundException e) {
            throw new CameraConnectionException(e);
        } finally {
            audioCameraOutputHandleCache.remove(audioHandle);
            if (audioOutputSateCache.containsKey(audioHandle)) {
                audioOutputSateCache.put(audioHandle, false);
            }
            if (audioOutputStreamManager.containsKey(audioHandle)) {
                try {
                    audioOutputStreamManager.get(audioHandle).close();
                } catch (IOException e) {
                    throw new CameraConnectionException(e);
                }
            }
        }
    }

    @Override
    public String videoStreamOutput(String channel, StreamTypeEnum streamType, String path) {
        OutputStream outputStream = null;
        try {
            createParentDirectory(path);
            outputStream = new FileOutputStream(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return videoStreamOutput(channel, streamType, outputStream);
    }

    @Override
    public String videoStreamOutput(String channel, StreamTypeEnum streamType, OutputStream outputStream) {
        LPNET_DVR_PREVIEWINFO previewInfo = new LPNET_DVR_PREVIEWINFO();
        //preview config
        previewInfo.lChannel = handleChannel(channel);
        previewInfo.dwStreamType = streamType.key();
        previewInfo.hPlayWnd = null;

        //generate the video collection handle
        String dataOutputHandle = UUID.randomUUID().toString().replaceAll("-", "");
        videoOutputSateCache.put(dataOutputHandle, false);
        videoOutputStreamManager.put(dataOutputHandle, outputStream);
        RealDataCallBack realDataCallBack = new RealDataCallBack(Math.toIntExact(userHandle), dataOutputHandle,
                streamType.key(), channel, outputStream);

        //start to write
        previewInfo.write();
        int dataHandle = hcNetSDK.NET_DVR_RealPlay_V40(Math.toIntExact(userHandle),
                previewInfo, realDataCallBack, null);
        if (dataHandle == FAILED) {
            throw new HikException(getErrorMsg());
        }
        videoOutputHandleCache.put(dataOutputHandle, (long) dataHandle);
        return dataOutputHandle;
    }

    @Override
    public void stopVideoStreamOutput(String dataOutputHandle) {
        try {
            if (videoOutputHandleCache.containsKey(dataOutputHandle)) {
                if (!hcNetSDK.NET_DVR_StopRealPlay(Math.toIntExact(videoOutputHandleCache.get(dataOutputHandle)))) {
                    throw new HikException(getErrorMsg());
                }
            }
        } finally {
            if (videoOutputSateCache.containsKey(dataOutputHandle)) {
                videoOutputSateCache.put(dataOutputHandle, true);
            }
            videoOutputHandleCache.remove(dataOutputHandle);
            if (videoOutputStreamManager.containsKey(dataOutputHandle)) {
                if (videoOutputStreamManager.get(dataOutputHandle) != null) {
                    try {
                        videoOutputStreamManager.get(dataOutputHandle).close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public List<NVRChannelInfo> getChannelInfo() {
        String url = "GET /ISAPI/ContentMgmt/InputProxy/channels";
        ByteArrayStructure array = new ByteArrayStructure(url.length());
        System.arraycopy(url.getBytes(), 0, array.byValue, 0, url.length());
        array.write();

        NET_DVR_XML_CONFIG_INPUT input = new NET_DVR_XML_CONFIG_INPUT();
        input.read();
        input.dwSize = input.size();
        input.lpRequestUrl = array.getPointer();
        input.dwRequestUrlLen = url.getBytes().length;
        input.write();

        NET_DVR_XML_CONFIG_OUTPUT output = new NET_DVR_XML_CONFIG_OUTPUT();
        output.dwSize = output.size();
        output.lpOutBuffer = new ByteArrayStructure(ISAPI_DATA_LEN).getPointer();
        output.dwOutBufferSize = new ByteArrayStructure(ISAPI_DATA_LEN).size();
        output.lpStatusBuffer = new ByteArrayStructure(ISAPI_STATUS_LEN).getPointer();
        output.dwStatusSize = new ByteArrayStructure(ISAPI_STATUS_LEN).size();
        output.write();
        if (!hcNetSDK.NET_DVR_STDXMLConfig(userHandle.intValue(), input, output)) {
            throw new HikException(getErrorMsg());
        }
        output.read();
        JsonObject jsonObject = XmlToJsonUtils.toJson(
                new String(output.lpOutBuffer.getByteArray(0, output.dwReturnedXMLSize)).trim());
        if (jsonObject != null) {
            Object inputProxyChannel = jsonObject.get("InputProxyChannel");
            if (inputProxyChannel != null) {
                if (inputProxyChannel instanceof List) {
                    List<NVRChannelInfo> nvrChannelInfoList = new ArrayList<>();
                    List<Object> channelInfos = (List<Object>) inputProxyChannel;
                    for (Object channelInfo : channelInfos) {
                        nvrChannelInfoList.add(convert(channelInfo));
                    }
                    return nvrChannelInfoList;
                }
                return Collections.singletonList(convert(inputProxyChannel));
            }
        }
        return Collections.emptyList();
    }

    @Override
    public void control(String channel, PTZControlEnum controlEnum, int status, int speed) {
        if (!hcNetSDK.NET_DVR_PTZControlWithSpeed(Math.toIntExact(
                this.preview(channel, StreamTypeEnum.SUB_STREAM)), controlEnum.key(), status, speed)) {
            throw new HikException(getErrorMsg());
        }
    }

    @Override
    public void controlExpand(String channel, PTZControlEnum controlEnum, int status, int speed) {
        if (!hcNetSDK.NET_DVR_PTZControlWithSpeed_Other(
                Math.toIntExact(userHandle), handleChannel(channel), controlEnum.key(), status, speed)) {
            throw new HikException(getErrorMsg());
        }
    }

    @Override
    public void preset(String channel, PresetEnum presetEnum, int index) {
        if (!hcNetSDK.NET_DVR_PTZPreset(Math.toIntExact(
                this.preview(channel, StreamTypeEnum.SUB_STREAM)), presetEnum.key(), index)) {
            throw new HikException(getErrorMsg());
        }
    }

    @Override
    public void presetExpand(String channel, PresetEnum presetEnum, int index) {
        if (!hcNetSDK.NET_DVR_PTZPreset_Other
                (Math.toIntExact(userHandle), handleChannel(channel), presetEnum.key(), index)) {
            throw new HikException(getErrorMsg());
        }
    }


    @Override
    public void videoOutput(String channel, StreamTypeEnum streamType, String targetPath) {
        // set the file splitSize
        NET_DVR_LOCAL_GENERAL_CFG cfg = new NET_DVR_LOCAL_GENERAL_CFG();
        cfg.byNotSplitRecordFile = SPLIT;
        hcNetSDK.NET_DVR_SetSDKLocalCfg(NET_DVR_LOCAL_CFG_TYPE_GENERAL, cfg);

        // make key frame
        hcNetSDK.NET_DVR_MakeKeyFrame(userHandle.intValue(), handleChannel(channel));

        // start recording
        createParentDirectory(targetPath);
        if (!hcNetSDK.NET_DVR_SaveRealData_V30(Math.toIntExact(
                this.preview(channel, streamType)), STREAM_3GPP, targetPath)) {
            throw new HikException(getErrorMsg());
        }
    }

    @Override
    public void stopVideoOutput(String channel, StreamTypeEnum streamType) {
        if (!hcNetSDK.NET_DVR_StopSaveRealData(Math.toIntExact(
                this.preview(channel, streamType)))) {
            throw new HikException(getErrorMsg());
        }
    }

    @Override
    public void cruise(String channel, CruiseEnum cruiseEnum, int cruiseRoute, int cruisePoint, int value) {
        if (!hcNetSDK.NET_DVR_PTZCruise(Math.toIntExact(
                this.preview(channel, StreamTypeEnum.SUB_STREAM)), cruiseEnum.key(), cruiseRoute, cruisePoint, value)) {
            throw new HikException(getErrorMsg());
        }
    }

    @Override
    public void cruiseExpand(String channel, CruiseEnum cruiseEnum, int cruiseRoute, int cruisePoint, int value) {
        if (!hcNetSDK.NET_DVR_PTZCruise_Other(userHandle.intValue(), handleChannel(channel), cruiseEnum.key(), cruiseRoute, cruisePoint, value)) {
            throw new HikException(getErrorMsg());
        }
    }

    @Override
    public void track(String channel, TrackEnum trackEnum) {
        if (!hcNetSDK.NET_DVR_PTZTrack(Math.toIntExact(
                this.preview(channel, StreamTypeEnum.SUB_STREAM)), trackEnum.key())) {
            throw new HikException(getErrorMsg());
        }
    }

    @Override
    public void trackExpand(String channel, TrackEnum trackEnum) {
        if (!hcNetSDK.NET_DVR_PTZTrack_Other(userHandle.intValue(), handleChannel(channel), trackEnum.key())) {
            throw new HikException(getErrorMsg());
        }
    }

    @Override
    public void setPresetName(int index, String name) {

        NET_DVR_PRESET_NAME presetName = new NET_DVR_PRESET_NAME();
        presetName.dwSize = presetName.size();
        presetName.byName = name.getBytes();
        presetName.wPresetNum = (short) index;
        presetName.write();

        if (!hcNetSDK.NET_DVR_SetDVRConfig(userHandle.intValue(), NET_DVR_SET_PRESET_NAME, ACTION,
                presetName, presetName.size())) {
            throw new HikException(getErrorMsg());
        }
    }

    @Override
    public String getPresetName(int index) {
        PRESET_NAME_STRUCTURE name = new PRESET_NAME_STRUCTURE();
        IntByReference bytesReturned = new IntByReference();
        name.write();
        if (!hcNetSDK.NET_DVR_GetDVRConfig(userHandle.intValue(), NET_DVR_GET_PRESET_NAME, ACTION,
                name.getPointer(), new NET_DVR_PRESET_NAME().size() * 256, bytesReturned)) {
            throw new HikException(getErrorMsg());
        }
        name.read();
        try {
            return new String(name.name[index - 1].byName, "GBK");
        } catch (UnsupportedEncodingException e) {
            throw new CameraConnectionException(e);
        }
    }

    @Override
    public PTZScope getPresetScope(String channel) {
        NET_DVR_PTZSCOPE scope = new NET_DVR_PTZSCOPE();
        scope.write();
        Pointer pointer = scope.getPointer();
        if (!hcNetSDK.NET_DVR_GetDVRConfig(userHandle.intValue(), NET_DVR_GET_PTZSCOPE, handleChannel(channel),
                pointer, DEFAULT_BUFFER, new IntByReference(scope.size()))) {
            throw new HikException(getErrorMsg());
        }
        scope.read();
        PTZScope ptzScope = new PTZScope();
        ptzScope.setPanMin(scope.panPosMin);
        ptzScope.setPanMax(scope.panPosMax);
        ptzScope.setTiltMin(scope.tiltPosMin);
        ptzScope.setPanMax(scope.tiltPosMax);
        ptzScope.setZoomMin(scope.zoomPosMin);
        ptzScope.setZoomMax(scope.zoomPosMax);
        return ptzScope;
    }


    @Override
    public PTZ getCurrentPosition(String channel) {
        PTZ_POSITION_STRUCTURE position = new PTZ_POSITION_STRUCTURE();
        position.write();
        Pointer pointer = position.getPointer();
        if (!hcNetSDK.NET_DVR_GetDVRConfig(Math.toIntExact(userHandle), NET_DVR_GET_PTZPOS, handleChannel(channel),
                pointer, DEFAULT_BUFFER, new IntByReference(PTZ_BUFFER_SIZE))) {
            throw new HikException(getErrorMsg());
        }
        position.read();
        position = handlePTZ(position, false);
        PTZ networkCameraPTZ = new PTZ(position.panPos, position.tiltPos, position.zoomPos);
        logger.info("Camera:{} with channel {},current ptz{}", networkCamera.getIp(), channel, networkCameraPTZ);
        return networkCameraPTZ;
    }

    @Override
    public void gotoPresetPoint(String channel, int presetIndex, PTZ ptz) throws InterruptedException {
        logger.info("Camera:{} goto preset point with channel:{} and index:{}", networkCamera.getIp(), channel, presetIndex);
        this.presetExpand(channel, PresetEnum.GOTO_PRESET, presetIndex);
        long time = System.currentTimeMillis();
        while (true) {
            PTZ curPTZ = this.getCurrentPosition(channel);
            if (curPTZ.equals(ptz)) {
                break;
            }
            if (TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - time) > 30) {
                throw new CameraConnectionException("Goto preset timeout 30 seconds");
            }
            Thread.sleep(500);
        }
        logger.info("Goto preset complete. used {} seconds", TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - time));
    }

    @Override
    public void locate(String channel, int pan, int tilt, int zoom) throws InterruptedException {
        PTZ ptz = new PTZ(pan, tilt, zoom);
        PTZ_POSITION_STRUCTURE position = handlePTZ(new PTZ_POSITION_STRUCTURE(ACTION, pan, tilt, zoom), true);
        if (!hcNetSDK.NET_DVR_SetDVRConfig(Math.toIntExact(userHandle), NET_DVR_SET_PTZPOS, handleChannel(channel),
                position, position.size())) {
            throw new HikException(getErrorMsg());
        }
        long time = System.currentTimeMillis();
        while (true) {
            PTZ curPTZ = this.getCurrentPosition(channel);
            if (curPTZ.equals(ptz)) {
                break;
            }
            if (TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - time) > 30) {
                throw new CameraConnectionException("Goto position timeout 30 seconds");
            }
            Thread.sleep(500);
        }
        logger.info("Goto position complete. used {} seconds", TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - time));

    }

    @Override
    public List<PresetPointInfo> getActivatedPresetPoints(String channel) {
        logger.info("Camera:{} get preset points that have been used with channel:{}", networkCamera.getIp(), channel);

        long actualChannel = hcNetSDK.NET_DVR_SDKChannelToISAPI(userHandle, handleChannel(channel), true);
        if (actualChannel == FAILED) {
            throw new HikException(getErrorMsg());
        }

        String url = "GET /ISAPI/PTZCtrl/channels/" + actualChannel + "/presets";
        NET_DVR_XML_CONFIG_INPUT input = new NET_DVR_XML_CONFIG_INPUT();
        ByteArrayStructure array = new ByteArrayStructure(url.length());
        System.arraycopy(url.getBytes(), 0, array.byValue, 0, url.length());
        array.write();

        input.read();
        input.lpRequestUrl = array.getPointer();
        input.dwSize = input.size();
        input.dwRequestUrlLen = url.getBytes().length;
        input.write();

        NET_DVR_XML_CONFIG_OUTPUT output = new NET_DVR_XML_CONFIG_OUTPUT();
        output.dwSize = output.size();
        output.dwOutBufferSize = new ByteArrayStructure(ISAPI_DATA_LEN).size();
        output.lpOutBuffer = new ByteArrayStructure(ISAPI_DATA_LEN).getPointer();
        output.lpStatusBuffer = new ByteArrayStructure(ISAPI_STATUS_LEN).getPointer();
        output.dwStatusSize = new ByteArrayStructure(ISAPI_STATUS_LEN).size();
        output.write();
        if (!hcNetSDK.NET_DVR_STDXMLConfig(Math.toIntExact(userHandle), input, output)) {
            throw new HikException(getErrorMsg());
        }
        output.read();

        JsonObject jsonObject = XmlToJsonUtils.toJson(new String(output.lpOutBuffer
                .getByteArray(0, output.dwReturnedXMLSize)).trim());
        if (jsonObject != null) {
            Object ptzPreset = jsonObject.get(ISAPI_PTZ_PRESET);
            if (ptzPreset != null) {
                Gson gson = new Gson();
                List<PresetPointInfo> presetPointInfos;
                if (ptzPreset instanceof JsonArray) {
                    presetPointInfos = gson.fromJson(ptzPreset.toString(), new TypeToken<List<PresetPointInfo>>() {
                    }.getType());
                } else {
                    presetPointInfos =
                            Collections.singletonList(gson.fromJson(ptzPreset.toString(), new TypeToken<PresetPointInfo>() {
                            }.getType()));
                }
                return presetPointInfos;
            }
        }
        return null;
    }

    @Override
    public List<Temperature> measureAll() {
        return this.getTargetTemperature(0);
    }

    @Override
    public Temperature measure(int infraredNo) {
        List<Temperature> targetTemperatures = this.getTargetTemperature(infraredNo);
        if (targetTemperatures == null || targetTemperatures.size() == 0) {
            logger.error("Temperature get failed,infraredNo:{} maybe not configuration", infraredNo);
            throw new CameraConnectionException("Temperature get failed,infraredNo maybe not configuration, infraredNo " + infraredNo);
        }
        return targetTemperatures.get(0);
    }

    /**
     * Get the temperature from network camera
     *
     * @param ruleId infrared point id
     * @return
     */
    public List<Temperature> getTargetTemperature(int ruleId) {
        //Real-time temperature detection config
        NET_DVR_REALTIME_THERMOMETRY_COND cond = new NET_DVR_REALTIME_THERMOMETRY_COND();
        cond.dwSize = cond.size();
        if (ruleId >= MIN_RULE && ruleId <= MAX_RULE) {
            cond.byRuleID = (byte) ruleId;
        } else {
            throw new CameraConnectionException("Get temperature failed,ruleId out of bounds]");
        }
        cond.write();
        //temperatures callback
        RemoteConfigCallback callback = new RemoteConfigCallback();
        int remoteHandle = hcNetSDK.NET_DVR_StartRemoteConfig(Math.toIntExact(userHandle), NET_DVR_GET_REALTIME_THERMOMETRY,
                cond.getPointer(), cond.size(), callback, null);
        if (remoteHandle == FAILED) {
            throw new HikException(getErrorMsg());
        }
        try {
            Thread.sleep(DEFAULT_BUFFER);
        } catch (InterruptedException ignored) {
        } finally {
            if (!hcNetSDK.NET_DVR_StopRemoteConfig(remoteHandle)) {
                throw new HikException(getErrorMsg());
            }
        }
        return callback.getTemperatures();
    }

    @Override
    public void setInfraredPoint(Temperature temperature) {
        LPNET_DVR_STD_CONFIG stdConfig = new LPNET_DVR_STD_CONFIG();
        NET_DVR_THERMOMETRY_PRESETINFO infraredInfo = getInfraredInfo(Math.toIntExact(userHandle), temperature.getPresetNo());
        NET_DVR_THERMOMETRY_COND cond = new NET_DVR_THERMOMETRY_COND();
        //lpCondBuffer config
        cond.dwSize = cond.size();
        cond.wPresetNo = (short) temperature.getPresetNo();
        cond.write();
        stdConfig.lpCondBuffer = cond.getPointer();
        stdConfig.dwCondSize = cond.size();
        //ruleName
        try {

            byte[] newName = new byte[32];
            int length = StringUtils.isEmpty(temperature.getInfraredName()) ? 0 : temperature.getInfraredName().getBytes().length;
            for (int i = 0; i < newName.length; i++) {
                if (i < length) {
                    newName[i] = temperature.getInfraredName().getBytes()[i];
                } else {
                    newName[i] = 0;
                }
            }
            int index = temperature.getInfraredNo() - 1;
            infraredInfo.struPresetInfo[index].byRuleCalibType = (byte) ACTION;
            NET_VCA_POLYGON polygon = infraredInfo.struPresetInfo[index].struRegion;
            polygon.dwPointNum = INFRARED_POINT_NUM;
            NET_VCA_POINT[] structurePoints = polygon.struPos;
            Temperature.Region[] regions = temperature.getRegions();
            for (int i = 0; i < INFRARED_POINT_NUM; i++) {
                structurePoints[i].fX = (float) regions[i].getX();
                structurePoints[i].fY = (float) regions[i].getY();
            }
            polygon.struPos = structurePoints;
            infraredInfo.struPresetInfo[index].szRuleName = new String(newName).getBytes("GBK");
            infraredInfo.struPresetInfo[index].struRegion = polygon;
            infraredInfo.struPresetInfo[index].byEnabled = (byte) ACTION;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        stdConfig.dwInSize = infraredInfo.size();
        infraredInfo.write();
        stdConfig.lpInBuffer = infraredInfo.getPointer();
        stdConfig.write();
        if (!hcNetSDK.NET_DVR_SetSTDConfig(Math.toIntExact(userHandle), NET_DVR_SET_THERMOMETRY_PRESETINFO, stdConfig)) {
            throw new HikException(getErrorMsg());
        }
    }

    @Override
    public Temperature getInfraredPoint(int presetNo, int infraredNo) {
        NET_DVR_THERMOMETRY_PRESETINFO infraredInfo = getInfraredInfo(Math.toIntExact(userHandle), presetNo);
        NET_DVR_THERMOMETRY_PRESETINFO_PARAM param = infraredInfo.struPresetInfo[infraredNo - 1];

        if (param.byEnabled == (byte) ACTION) {
            Temperature temperature = new Temperature();
            temperature.setPresetNo(presetNo);
            temperature.setInfraredNo(infraredNo);
            temperature.setInfraredName(byteToStr(param.szRuleName, "GBK"));
            NET_VCA_POINT[] points = param.struRegion.struPos;
            for (int i = 0; i < INFRARED_POINT_NUM; i++) {
                temperature.getRegions()[i] = new Temperature.Region(numFormat(points[i].fX, 3), numFormat(points[i].fY, 3));
            }
            return temperature;
        }
        return null;
    }

    @Override
    public void deleteInfraredPoint(int presetNo, int infraredNo) {
        LPNET_DVR_STD_CONFIG stdConfig = new LPNET_DVR_STD_CONFIG();
        NET_DVR_THERMOMETRY_PRESETINFO infraredInfo = getInfraredInfo(Math.toIntExact(userHandle), presetNo);
        NET_DVR_THERMOMETRY_COND cond = new NET_DVR_THERMOMETRY_COND();
        NET_DVR_THERMOMETRY_PRESETINFO_PARAM param = infraredInfo.struPresetInfo[infraredNo - 1];


        //lpCondBuffer config
        cond.dwSize = cond.size();
        cond.wPresetNo = (short) presetNo;
        cond.write();
        stdConfig.lpCondBuffer = cond.getPointer();
        stdConfig.dwCondSize = cond.size();

        param.byEnabled = (byte) INIT;
        param.struRegion.struPos = new NET_VCA_POINT[VCA_MAX_POLYGON_POINT_NUM];
        param.szRuleName = new byte[NAME_LEN];

        infraredInfo.write();
        stdConfig.dwInSize = infraredInfo.size();
        stdConfig.lpInBuffer = infraredInfo.getPointer();
        stdConfig.write();
        if (!hcNetSDK.NET_DVR_SetSTDConfig(Math.toIntExact(userHandle), NET_DVR_SET_THERMOMETRY_PRESETINFO, stdConfig)) {
            throw new HikException(getErrorMsg());
        }
    }

    @Override
    public void reboot() {
        if (!hcNetSDK.NET_DVR_RebootDVR(userHandle.intValue())) {
            throw new HikException(getErrorMsg());
        }
    }

    @Override
    public void restore() {
        if (!hcNetSDK.NET_DVR_RestoreConfig(userHandle.intValue())) {
            throw new HikException(getErrorMsg());
        }
    }

    @Override
    public void setZoomLimit(String channel, int zoomValue) {
        long actualChannel = hcNetSDK.NET_DVR_SDKChannelToISAPI(userHandle, handleChannel(channel), true);
        if (actualChannel == FAILED) {
            throw new HikException(getErrorMsg());
        }

        String url = "PUT /ISAPI/Image/channels/" + actualChannel + "/ZoomLimit";
        ByteArrayStructure array = new ByteArrayStructure(url.length());
        System.arraycopy(url.getBytes(), 0, array.byValue, 0, url.length());
        array.write();

        String request = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<ZoomLimit version=\"2.0\" xmlns=\"http://www.hikvision.com/ver20/XMLSchema\">\n" +
                "<ZoomLimitRatio>" + zoomValue + "</ZoomLimitRatio>\n" +
                "</ZoomLimit>";
        ByteArrayStructure requestArray = new ByteArrayStructure(request.length());
        System.arraycopy(url.getBytes(), 0, array.byValue, 0, url.length());
        requestArray.write();
        NET_DVR_XML_CONFIG_INPUT input = new NET_DVR_XML_CONFIG_INPUT();
        input.dwSize = input.size();
        input.lpRequestUrl = array.getPointer();
        input.dwRequestUrlLen = url.getBytes().length;
        input.lpInBuffer = request;
        input.dwInBufferSize = request.getBytes().length;
        input.write();

        NET_DVR_XML_CONFIG_OUTPUT output = new NET_DVR_XML_CONFIG_OUTPUT();
        output.dwSize = output.size();
        output.write();
        if (!hcNetSDK.NET_DVR_STDXMLConfig(userHandle.intValue(), input, output)) {
            throw new HikException(getErrorMsg());
        }
    }

    @Override
    public void openLensInitialization(String channel) {
        setLensInitialization(userHandle.intValue(), channel, true);
        close();
    }

    @Override
    public void closeLensInitialization(String channel) {
        setLensInitialization(userHandle.intValue(), channel, false);
        close();
    }


    private void setLensInitialization(int userHandle, String channel, boolean open) {
        long actualChannel = hcNetSDK.NET_DVR_SDKChannelToISAPI(userHandle, handleChannel(channel), true);
        if (actualChannel == FAILED) {
            throw new HikException(getErrorMsg());
        }
        String url = "PUT /ISAPI/Image/channels/" + actualChannel + "/lensInitialization";
        ByteArrayStructure array = new ByteArrayStructure(url.length());
        System.arraycopy(url.getBytes(), 0, array.byValue, 0, url.length());
        array.write();

        String request = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<LensInitialization version=\"2.0\" xmlns=\"http://www.hikvision.com/ver20/XMLSchema\">\n" +
                "<enabled>" + open + "</enabled>\n" +
                "</LensInitialization>";
        ByteArrayStructure requestArray = new ByteArrayStructure(request.length());
        System.arraycopy(url.getBytes(), 0, array.byValue, 0, url.length());
        requestArray.write();
        NET_DVR_XML_CONFIG_INPUT input = new NET_DVR_XML_CONFIG_INPUT();
        input.dwSize = input.size();
        input.lpRequestUrl = array.getPointer();
        input.dwRequestUrlLen = url.getBytes().length;
        input.lpInBuffer = request;
        input.dwInBufferSize = request.getBytes().length;
        input.write();

        NET_DVR_XML_CONFIG_OUTPUT output = new NET_DVR_XML_CONFIG_OUTPUT();
        output.dwSize = output.size();
        output.write();
        if (!hcNetSDK.NET_DVR_STDXMLConfig(userHandle, input, output)) {
            throw new HikException(getErrorMsg());
        }
    }

    @Override
    public void setMaxElevationAngle(String channel, int elevationValue) {
        long actualChannel = hcNetSDK.NET_DVR_SDKChannelToISAPI(userHandle, handleChannel(channel), true);
        if (actualChannel == FAILED) {
            throw new HikException(getErrorMsg());
        }
        String url = "PUT /ISAPI/PTZCtrl/channels/" + actualChannel + "/maxelevation";
        ByteArrayStructure array = new ByteArrayStructure(url.length());
        System.arraycopy(url.getBytes(), 0, array.byValue, 0, url.length());
        array.write();

        String request =
                "<MaxElevation version=\"2.0\" xmlns=\"http://www.hikvision.com/ver20/XMLSchema\">" +
                        "<mElevation>" + elevationValue + "</mElevation>" +
                        "</MaxElevation>";
        ByteArrayStructure requestArray = new ByteArrayStructure(request.length());
        System.arraycopy(url.getBytes(), 0, array.byValue, 0, url.length());
        requestArray.write();
        NET_DVR_XML_CONFIG_INPUT input = new NET_DVR_XML_CONFIG_INPUT();
        input.lpRequestUrl = array.getPointer();
        input.dwSize = input.size();
        input.lpInBuffer = request;
        input.dwInBufferSize = request.getBytes().length;
        input.dwRequestUrlLen = url.getBytes().length;
        input.write();

        NET_DVR_XML_CONFIG_OUTPUT output = new NET_DVR_XML_CONFIG_OUTPUT();
        output.dwSize = output.size();
        output.write();
        if (!hcNetSDK.NET_DVR_STDXMLConfig(userHandle.intValue(), input, output)) {
            throw new HikException(getErrorMsg());
        }
        logout();
    }

    @Override
    public void setScreen(String channel, ScreenEffectEnum screenEffectEnum, int value) {
        NET_DVR_CAMERAPARAMCFG_EX param = getCameraConfig(userHandle.intValue(), channel);
        switch (screenEffectEnum) {
            case BRIGHT:
                param.struVideoEffect.byBrightnessLevel = (byte) value;
                break;
            case HUE:
                param.struVideoEffect.byHueLevel = (byte) value;
                break;
            case CONTRAST:
                param.struVideoEffect.byContrastLevel = (byte) value;
                break;
            case SATURATION:
                param.struVideoEffect.bySaturationLevel = (byte) value;
                break;
            case SHARPNESS:
                param.struVideoEffect.bySharpnessLevel = (byte) value;
                break;
        }
        if (!hcNetSDK.NET_DVR_SetDVRConfig(userHandle.intValue(), NET_DVR_CAMERAPARAMCFG_EX_SET, handleChannel(channel),
                param, param.size())) {
            throw new HikException(getErrorMsg());
        }
    }

    @Override
    public ScreenEffect getScreen(String channel) {
        NET_DVR_CAMERAPARAMCFG_EX param = getCameraConfig(userHandle.intValue(), channel);
        ScreenEffect videoEffect = new ScreenEffect();
        videoEffect.setBrightness(param.struVideoEffect.byBrightnessLevel);
        videoEffect.setContrast(param.struVideoEffect.byContrastLevel);
        videoEffect.setSaturation(param.struVideoEffect.bySaturationLevel);
        videoEffect.setHue(param.struVideoEffect.byHueLevel);
        videoEffect.setSharpness(param.struVideoEffect.bySharpnessLevel);
        return videoEffect;
    }

    @Override
    public void resetScreen(String channel) {
        setScreen(channel, ScreenEffectEnum.BRIGHT, 50);
        setScreen(channel, ScreenEffectEnum.HUE, 50);
        setScreen(channel, ScreenEffectEnum.CONTRAST, 50);
        setScreen(channel, ScreenEffectEnum.SATURATION, 50);
        setScreen(channel, ScreenEffectEnum.SHARPNESS, 50);
    }

    @Override
    public void setDayNightConversionMode(String channel, DayNightEnum dayNightEnum) {
        NET_DVR_CAMERAPARAMCFG_EX param = getCameraConfig(userHandle.intValue(), channel);
        param.struDayNight.byDayNightFilterType = dayNightEnum.getValue();
        if (!hcNetSDK.NET_DVR_SetDVRConfig(userHandle.intValue(), NET_DVR_CAMERAPARAMCFG_EX_SET, handleChannel(channel),
                param, param.size())) {
            throw new HikException(getErrorMsg());
        }
    }

    @Override
    public void setCustomDayNightConversionMode(String channel, Calendar dayStart, Calendar dayEnd) {
        NET_DVR_CAMERAPARAMCFG_EX param = getCameraConfig(userHandle.intValue(), channel);
        param.struDayNight.byDayNightFilterType = CUSTOM;
        param.struDayNight.byBeginTime = (byte) dayStart.get(HOUR_OF_DAY);
        param.struDayNight.byBeginTimeMin = (byte) dayStart.get(Calendar.MINUTE);
        param.struDayNight.byBeginTimeSec = (byte) dayStart.get(Calendar.SECOND);
        param.struDayNight.byEndTime = (byte) dayEnd.get(HOUR_OF_DAY);
        param.struDayNight.byEndTimeMin = (byte) dayEnd.get(Calendar.MINUTE);
        param.struDayNight.byEndTimeSec = (byte) dayEnd.get(Calendar.SECOND);

        if (!hcNetSDK.NET_DVR_SetDVRConfig(userHandle.intValue(), NET_DVR_CAMERAPARAMCFG_EX_SET, handleChannel(channel),
                param, param.size())) {
            throw new HikException(getErrorMsg());
        }
    }

    @Override
    public void setBackLightCompensationMode(String channel, BackLightEnum backLightEnum) {
        NET_DVR_CAMERAPARAMCFG_EX param = getCameraConfig(userHandle.intValue(), channel);
        param.struBackLight.byBacklightMode = backLightEnum.getValue();
        param.struBackLight.byBacklightLevel = DEFAULT_BACK_LIGHT_LEVEL;
        if (!hcNetSDK.NET_DVR_SetDVRConfig(userHandle.intValue(), NET_DVR_CAMERAPARAMCFG_EX_SET, handleChannel(channel),
                param, param.size())) {
            throw new HikException(getErrorMsg());
        }
    }

    @Override
    public void setBackLightCompensationMode(String channel, BackLightEnum backLightEnum, int value) {
        NET_DVR_CAMERAPARAMCFG_EX param = getCameraConfig(userHandle.intValue(), channel);
        param.struBackLight.byBacklightMode = backLightEnum.getValue();

        if (value >= 0 && value <= 15) {
            param.struBackLight.byBacklightLevel = (byte) value;
        } else {
            param.struBackLight.byBacklightLevel = DEFAULT_BACK_LIGHT_LEVEL;
        }
        if (!hcNetSDK.NET_DVR_SetDVRConfig(userHandle.intValue(), NET_DVR_CAMERAPARAMCFG_EX_SET, handleChannel(channel),
                param, param.size())) {
            throw new HikException(getErrorMsg());
        }
    }

    @Override
    public void setWhiteBalanceMode(String channel, WhiteBalanceEnum whiteBalanceEnum) {
        NET_DVR_CAMERAPARAMCFG_EX param = getCameraConfig(userHandle.intValue(), channel);
        param.struWhiteBalance.byWhiteBalanceMode = whiteBalanceEnum.getKey();

        if (!hcNetSDK.NET_DVR_SetDVRConfig(userHandle.intValue(), NET_DVR_CAMERAPARAMCFG_EX_SET, handleChannel(channel),
                param, param.size())) {
            throw new HikException(getErrorMsg());
        }
    }

    @Override
    public void openStrongLightInhibitionMode(String channel) {
        NET_DVR_CAMERAPARAMCFG_EX param = getCameraConfig(userHandle.intValue(), channel);
        param.struVideoEffect.byEnableFunc = LightEnum.STRONG_LIGHT_INHIBITION_ON.getValue();

        if (!hcNetSDK.NET_DVR_SetDVRConfig(userHandle.intValue(), NET_DVR_CAMERAPARAMCFG_EX_SET, handleChannel(channel),
                param, param.size())) {
            throw new HikException(getErrorMsg());
        }
    }

    @Override
    public void closeStrongLightInhibitionMode(String channel) {
        openPreventOverexposureMode(channel);
    }

    @Override
    public void openPreventOverexposureMode(String channel) {
        NET_DVR_CAMERAPARAMCFG_EX param = getCameraConfig(userHandle.intValue(), channel);
        param.struVideoEffect.byEnableFunc = LightEnum.PREVENT_OVEREXPOSURE_ON.getValue();

        if (!hcNetSDK.NET_DVR_SetDVRConfig(userHandle.intValue(), NET_DVR_CAMERAPARAMCFG_EX_SET, handleChannel(channel),
                param, param.size())) {
            throw new HikException(getErrorMsg());
        }
    }

    @Override
    public void closePreventOverexposureMode(String channel) {
        openStrongLightInhibitionMode(channel);
    }

    @Override
    public void closeLightInhibitionAndPreventOverexposureMode(String channel) {
        NET_DVR_CAMERAPARAMCFG_EX param = getCameraConfig(userHandle.intValue(), channel);
        param.struVideoEffect.byEnableFunc = LightEnum.ALL_OFF.getValue();

        if (!hcNetSDK.NET_DVR_SetDVRConfig(userHandle.intValue(), NET_DVR_CAMERAPARAMCFG_EX_SET, handleChannel(channel),
                param, param.size())) {
            throw new HikException(getErrorMsg());
        }
    }

    @Override
    public void setWideDynamicMode(String channel, WDREnum wdrEnum) {
        NET_DVR_CAMERAPARAMCFG_EX param = getCameraConfig(userHandle.intValue(), channel);
        param.struWdr.byWDREnabled = wdrEnum.getKey();

        if (!hcNetSDK.NET_DVR_SetDVRConfig(userHandle.intValue(), NET_DVR_CAMERAPARAMCFG_EX_SET, handleChannel(channel),
                param, param.size())) {
            throw new HikException(getErrorMsg());
        }
    }

    @Override
    public void setWideDynamicMode(String channel, WDREnum wdrEnum, int value) {
        NET_DVR_CAMERAPARAMCFG_EX param = getCameraConfig(userHandle.intValue(), channel);
        param.struWdr.byWDRLevel1 = (byte) value;

        if (!hcNetSDK.NET_DVR_SetDVRConfig(userHandle.intValue(), NET_DVR_CAMERAPARAMCFG_EX_SET, handleChannel(channel),
                param, param.size())) {
            throw new HikException(getErrorMsg());
        }
    }

    @Override
    public void setNoiseReductionMode(String channel, NoiseReductionEnum noiseReductionEnum) {
        NET_DVR_CAMERAPARAMCFG_EX param = getCameraConfig(userHandle.intValue(), channel);
        param.struNoiseRemove.byDigitalNoiseRemoveEnable = noiseReductionEnum.getKey();

        if (!hcNetSDK.NET_DVR_SetDVRConfig(userHandle.intValue(), NET_DVR_CAMERAPARAMCFG_EX_SET, handleChannel(channel),
                param, param.size())) {
            throw new HikException(getErrorMsg());
        }
    }

    @Override
    public void setNoiseReductionValue(String channel, NoiseReductionLevelEnum noiseReductionLevelEnum, int value) {
        NET_DVR_CAMERAPARAMCFG_EX param = getCameraConfig(userHandle.intValue(), channel);
        switch (noiseReductionLevelEnum) {
            case DIGITAL_NOISE_REDUCTION_LEVEL:
                param.struNoiseRemove.byDigitalNoiseRemoveLevel = (byte) value;
                break;
            case AIRSPACE_NOISE_REDUCTION_LEVEL:
                param.struNoiseRemove.bySpectralLevel = (byte) value;
                break;
            case TIME_DOMAIN_NOISE_REDUCTION_LEVEL:
                param.struNoiseRemove.byTemporalLevel = (byte) value;
                break;
        }

        if (!hcNetSDK.NET_DVR_SetDVRConfig(userHandle.intValue(), NET_DVR_CAMERAPARAMCFG_EX_SET, handleChannel(channel),
                param, param.size())) {
            throw new HikException(getErrorMsg());
        }
    }

    @Override
    public void openDefogMode(String channel) {
        NET_DVR_CAMERAPARAMCFG_EX param = getCameraConfig(userHandle.intValue(), channel);
        param.struDefogCfg.byMode = OPEN_DEFOG;

        if (!hcNetSDK.NET_DVR_SetDVRConfig(userHandle.intValue(), NET_DVR_CAMERAPARAMCFG_EX_SET, handleChannel(channel),
                param, param.size())) {
            throw new HikException(getErrorMsg());
        }
    }

    @Override
    public void closeDefogMode(String channel) {
        NET_DVR_CAMERAPARAMCFG_EX param = getCameraConfig(userHandle.intValue(), channel);
        param.struDefogCfg.byMode = CLOSE_DEFOG;

        if (!hcNetSDK.NET_DVR_SetDVRConfig(userHandle.intValue(), NET_DVR_CAMERAPARAMCFG_EX_SET, handleChannel(channel),
                param, param.size())) {
            throw new HikException(getErrorMsg());
        }
    }

    @Override
    public void openElectronicStabilizationMode(String channel) {
        NET_DVR_CAMERAPARAMCFG_EX param = getCameraConfig(userHandle.intValue(), channel);
        param.struElectronicStabilization.byEnable = OPEN_ELECTRONIC_STABILIZATION;

        if (!hcNetSDK.NET_DVR_SetDVRConfig(userHandle.intValue(), NET_DVR_CAMERAPARAMCFG_EX_SET, handleChannel(channel),
                param, param.size())) {
            throw new HikException(getErrorMsg());
        }
    }

    @Override
    public void closeElectronicStabilizationMode(String channel) {
        NET_DVR_CAMERAPARAMCFG_EX param = getCameraConfig(userHandle.intValue(), channel);
        param.struElectronicStabilization.byEnable = CLOSE_ELECTRONIC_STABILIZATION;

        if (!hcNetSDK.NET_DVR_SetDVRConfig(userHandle.intValue(), NET_DVR_CAMERAPARAMCFG_EX_SET, handleChannel(channel),
                param, param.size())) {
            throw new HikException(getErrorMsg());
        }
    }

    @Override
    public void setExposureMode(String channel, ExposureMode exposureMode) {
        NET_DVR_AEMODECFG param = getExposureConfig(userHandle.intValue(), channel);
        param.byExposureModeSet = exposureMode.getKey();

        if (!hcNetSDK.NET_DVR_SetDVRConfig(userHandle.intValue(), NET_DVR_AEMODECFG_SET, handleChannel(channel),
                param, param.size())) {
            throw new HikException(getErrorMsg());
        }
    }

    @Override
    public void setExposureParam(String channel, ExposureParam exposureParam, int value) {
        NET_DVR_AEMODECFG param = getExposureConfig(userHandle.intValue(), channel);
        switch (exposureParam) {
            case EXPOSURE_LEVEL:
                param.byExposureLevel = (byte) value;
                break;
            case MAX_IRIS:
                param.byMaxIrisSet = (byte) value;
                break;
            case MIN_IRIS:
                param.byMinIrisSet = (byte) value;
                break;
            case MAX_SHUTTER:
                param.byMaxShutterSet = (byte) value;
                break;
            case MIN_SHUTTER:
                param.byMinShutterSet = (byte) value;
                break;
        }
        if (!hcNetSDK.NET_DVR_SetDVRConfig(userHandle.intValue(), NET_DVR_AEMODECFG_SET, handleChannel(channel),
                param, param.size())) {
            throw new HikException(getErrorMsg());
        }
    }

    @Override
    public void setFocusMode(String channel, FocusMode focusMode) {
        NET_DVR_FOCUSMODE_CFG mode = new NET_DVR_FOCUSMODE_CFG();
        if (!hcNetSDK.NET_DVR_GetDVRConfig(userHandle.intValue(), NET_DVR_FOCUSMODE_CFG_GET, handleChannel(channel),
                mode.getPointer(), mode.size(), new IntByReference())) {
            throw new HikException(getErrorMsg());
        }
        mode.read();
        mode.byFocusMode = focusMode.getKey();
        mode.write();
        if (!hcNetSDK.NET_DVR_SetDVRConfig(userHandle.intValue(), NET_DVR_FOCUSMODE_CFG_SET, handleChannel(channel),
                mode, mode.size())) {
            throw new HikException(getErrorMsg());
        }
    }

    @Override
    public void setFocusDistance(String channel, int value) {
        NET_DVR_FOCUSMODE_CFG mode = new NET_DVR_FOCUSMODE_CFG();
        if (!hcNetSDK.NET_DVR_GetDVRConfig(userHandle.intValue(), NET_DVR_FOCUSMODE_CFG_GET, handleChannel(channel),
                mode.getPointer(), mode.size(), new IntByReference())) {
            throw new HikException(getErrorMsg());
        }
        mode.read();
        mode.wMinFocusDistance = (short) value;
        mode.write();
        if (!hcNetSDK.NET_DVR_SetDVRConfig(userHandle.intValue(), NET_DVR_FOCUSMODE_CFG_SET, handleChannel(channel),
                mode, mode.size())) {
            throw new HikException(getErrorMsg());
        }
    }

    @Override
    public void resetLens(String channel) {
        if (!hcNetSDK.NET_DVR_ResetLens(userHandle.intValue(), handleChannel(channel))) {
            throw new HikException(getErrorMsg());
        }
    }

    @Override
    public void setLowIlluminationElectronicShutterMode(String channel, LowLightShutterEnum lowLightShutterEnum) {
        NET_DVR_LOW_LIGHT_CFG lowLight = new NET_DVR_LOW_LIGHT_CFG();
        if (!hcNetSDK.NET_DVR_GetDVRConfig(userHandle.intValue(), NET_DVR_LOW_LIGHT_CFG_GET, handleChannel(channel),
                lowLight.getPointer(), lowLight.size(), new IntByReference())) {
            throw new HikException(getErrorMsg());
        }
        lowLight.read();
        if (lowLightShutterEnum.getLevel() == LowLightShutterEnum.OFF.getLevel()) {
            lowLight.byLowLightLimt = lowLightShutterEnum.getLevel();
        } else {
            lowLight.byLowLightLimt = LOW_LIGHT_LIMIT_OPEN;
            lowLight.byLowLightLimtLevel = lowLightShutterEnum.getLevel();
        }
        lowLight.write();
        if (!hcNetSDK.NET_DVR_SetDVRConfig(userHandle.intValue(), NET_DVR_LOW_LIGHT_CFG_SET, handleChannel(channel),
                lowLight, lowLight.size())) {
            throw new HikException(getErrorMsg());
        }
    }

    @Override
    public void setVideoAndAudioMode(String channel, StreamTypeEnum streamTypeEnum, CompressionEnum compressionEnum, int value) {
        NET_DVR_COMPRESSIONCFG_V30 compression = getCompressionConfig(userHandle.intValue(), channel);
        NET_DVR_COMPRESSION_INFO_V30 info = null;
        switch (streamTypeEnum) {
            case MAIN_STREAM:
                info = compression.struNormHighRecordPara;
                break;
            case SUB_STREAM:
                info = compression.struNetPara;
                break;
        }

        switch (compressionEnum) {
            case STREAM_TYPE:
                info.byStreamType = (byte) value;
                break;
            case RESOLUTION:
                info.byResolution = (byte) value;
                break;
            case BITRATE_TYPE:
                info.byBitrateType = (byte) value;
                break;
            case PIC_QUALITY:
                info.byPicQuality = (byte) value;
                break;
            case VIDEO_BITRATE:
                info.dwVideoBitrate = value;
                break;
            case VIDEO_ENCODE_TYPE:
                info.byVideoEncType = (byte) value;
                break;
            case VIDEO_ENCODE_COMPLEXITY:
                info.byVideoEncComplexity = (byte) value;
                break;
            case ENABLE_SVC:
                info.byEnableSvc = (byte) value;
                break;
            case AUDIO_BIT_RATE:
                info.byAudioBitRate = (byte) value;
                break;
            case STEAM_SMOOTH:
                info.bySteamSmooth = (byte) value;
                break;
            case AUDIO_SAMPLING_RATE:
                info.byAudioSamplingRate = (byte) value;
                break;
            case VIDEO_FRAME_RATE:
                info.dwVideoFrameRate = value;
                break;
            case AUDIO_ENCODE_TYPE:
                info.byAudioEncType = (byte) value;
                break;
        }

        switch (streamTypeEnum) {
            case MAIN_STREAM:
                compression.struNormHighRecordPara = info;
                break;
            case SUB_STREAM:
                compression.struNetPara = info;
                break;
        }

        if (!hcNetSDK.NET_DVR_SetDVRConfig(userHandle.intValue(), NET_DVR_COMPRESSIONCFG_V30_SET, handleChannel(channel),
                compression, compression.size())) {
            throw new HikException(getErrorMsg());
        }
    }

    @Override
    public void setAudioInputConfig(String channel, AudioInputEnum audioInputEnum, int value) {
        NET_DVR_AUDIO_INPUT_PARAM param = getAudioInputConfig(userHandle.intValue(), channel);

        switch (audioInputEnum) {
            case AUDIO_INPUT_TYPE:
                param.byAudioInputType = (byte) value;
                break;
            case AUDIO_INPUT_VOLUME:
                param.byVolume = (byte) value;
                break;
            case AUDIO_INPUT_NOISE_FILTER:
                param.byEnableNoiseFilter = (byte) value;
                break;
        }
        param.write();
        if (!hcNetSDK.NET_DVR_SetDVRConfig(userHandle.intValue(), NET_DVR_AUDIO_INPUT_PARAM_SET, handleChannel(channel),
                param, param.size())) {
            throw new HikException(getErrorMsg());
        }
    }

    @Override
    public void setPTZOSDConfigMode(String channel, PtzOSDParam ptzOSDParam, int value) {
        NET_DVR_PTZ_OSDCFG osd = new NET_DVR_PTZ_OSDCFG();
        if (!hcNetSDK.NET_DVR_GetDVRConfig(userHandle.intValue(), NET_DVR_GET_PTZOSDCFG, handleChannel(channel),
                osd.getPointer(), ISAPI_DATA_LEN, new IntByReference(osd.size()))) {
            throw new HikException(getErrorMsg());
        }
        osd.read();
        switch (ptzOSDParam) {
            case PT_STATUS:
                osd.byPtStatus = (byte) value;
                break;
            case ZOOM_STATUS:
                osd.byZoomStatus = (byte) value;
                break;
            case PRESET_STATUS:
                osd.byPresetStatus = (byte) value;
                break;
        }
        osd.write();
        if (!hcNetSDK.NET_DVR_SetDVRConfig(userHandle.intValue(), NET_DVR_SET_PTZOSDCFG, handleChannel(channel),
                osd, osd.size())) {
            throw new HikException(getErrorMsg());
        }
    }


    private static class VoiceDataCallBack implements HCNetSDK.VoiceDataCallBack {

        private OutputStream device;
        private int voiceHandle;
        private Pointer buffer;
        private int dwBufSize;
        private String uniqueness;

        {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (audioOutputSateCache.get(uniqueness) != null) {
                        while (audioOutputSateCache.get(uniqueness)) {
                            if (buffer != null && buffer.getCharArray(0, dwBufSize) != null) {
                                try {
                                    Thread.sleep(3000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                if (audioCameraOutputHandleCache.get(uniqueness) != null) {
                                    if (!hcNetSDK.NET_DVR_VoiceComSendData(voiceHandle, buffer, dwBufSize)) {
                                        if (hcNetSDK.NET_DVR_GetLastError() != 0) {
                                            throw new HikException(getErrorMsg());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }).start();
        }

        public VoiceDataCallBack(OutputStream outputStream, int voiceHandle, String uniqueness) {
            this.device = outputStream;
            this.voiceHandle = voiceHandle;
            this.uniqueness = uniqueness;
        }

        @Override
        public void invoke(int lVoiceComHandle, Pointer buffer, int dwBufSize, byte byAudioFlag, Pointer pUser) {
            if (byAudioFlag == NET_DVR_DEVICE_AUDIO) {
                this.buffer = buffer;
                this.dwBufSize = dwBufSize;
                byte[] data = buffer.getByteArray(0, dwBufSize);

                try {
                    device.write(data);
                    device.flush();
                } catch (Exception ex) {
                    try {
                        device.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ex.printStackTrace();
                }
            }
        }

    }

    private static class RealDataCallBack implements HCNetSDK.FRealDataCallBack_V30 {

        private OutputStream outputStream;
        private String channel;
        private int userId;
        private String dataOutputHandle;
        private int streamType;

        {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (videoOutputSateCache.containsKey(dataOutputHandle)) {
                        while (!videoOutputSateCache.get(dataOutputHandle)) {
                            try {
                                Thread.sleep(1000);
                                if (streamType == StreamTypeEnum.MAIN_STREAM.key()) {
                                    hcNetSDK.NET_DVR_MakeKeyFrame(userId, handleChannel(channel));
                                } else {
                                    hcNetSDK.NET_DVR_MakeKeyFrameSub(userId, handleChannel(channel));
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }).start();
        }

        public RealDataCallBack(int userHandle, String dataOutputHandle, int streamType, String channel, OutputStream outputStream) {
            this.userId = userHandle;
            this.channel = channel;
            this.streamType = streamType;
            this.dataOutputHandle = dataOutputHandle;
            this.outputStream = outputStream;
        }

        @Override
        public void invoke(int lRealHandle, int dwDataType, ByteByReference pBuffer, int dwBufSize, Pointer pUser) {
            byte[] byteArray;
            boolean stopVideo = videoOutputSateCache.get(dataOutputHandle);
            try {
                switch (dwDataType) {
                    case NET_DVR_SYSHEAD:
                        if (dwBufSize > 0) {
                            byteArray = pBuffer.getPointer().getByteArray(0, Math.toIntExact(dwBufSize));
                            if (outputStream != null && !stopVideo) {
                                outputStream.write(byteArray);
                            }
                        }
                        break;
                    case NET_DVR_STREAMDATA:
                        if (!stopVideo && outputStream != null) {
                            if (dwBufSize > 0) {
                                byteArray = pBuffer.getPointer().getByteArray(0, Math.toIntExact(dwBufSize));
                                outputStream.write(byteArray);
                            }
                        }
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static class RemoteConfigCallback implements HCNetSDK.FremoteConfigCallback {

        private Map<String, Temperature> temperatureMap = new HashMap<>();

        public List<Temperature> getTemperatures() {
            return temperatureMap.values().stream().filter(t -> t.getInfraredNo() != 0).collect(Collectors.toList());
        }

        @Override
        public void invoke(int dwType, Pointer lpBuffer, int dwBufLen, Pointer pUserData) {

            switch (dwType) {
                case NET_SDK_CALLBACK_TYPE_DATA:
                    byte[] byteArray = lpBuffer.getByteArray(0, dwBufLen);
                    NET_DVR_THERMOMETRY_UPLOAD thermometry = new NET_DVR_THERMOMETRY_UPLOAD();
                    thermometry.write();
                    thermometry.getPointer().write(0, byteArray, 0, thermometry.size());
                    thermometry.read();

                    String ruleId = String.valueOf(thermometry.byRuleID);
                    if (!temperatureMap.containsKey(ruleId)) {
                        temperatureMap.put(ruleId, convert(thermometry));
                    }
                    break;
            }
        }

    }
}
