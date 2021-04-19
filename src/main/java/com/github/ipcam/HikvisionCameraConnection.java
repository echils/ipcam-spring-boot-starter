package com.github.ipcam;

import com.github.ipcam.entity.*;
import com.github.ipcam.entity.exception.CameraConnectionException;
import com.github.ipcam.entity.exception.HikException;
import com.github.ipcam.entity.hikvision.*;
import com.github.ipcam.entity.reference.*;
import com.github.ipcam.support.CameraSupportedDriver;
import com.github.ipcam.support.ICameraNVRSupport;
import com.github.ipcam.support.ICameraPTZSupport;
import com.github.ipcam.support.ICameraThermalSupport;
import com.github.ipcam.utils.XmlToJsonUtils;
import com.google.gson.JsonObject;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.IntByReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static com.github.ipcam.entity.NetworkCameraContext.*;
import static com.github.ipcam.entity.hikvision.HCNetSDK.hcNetSDK;
import static com.github.ipcam.entity.hikvision.HikManager.*;
import static com.github.ipcam.utils.FileUtils.createParentDirectory;

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
    public long login(String ip, int port, String username, String password) {
        return this.login(ip, port, username, password, LOGIN_DEFAULT_WAIT_TIME, LOGIN_DEFAULT_TRY_TIME);
    }


    @Override
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


    @Override
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

    @Override
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

    @Override
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
    public void cruise(CruiseEnum cruiseEnum, int cruiseRoute, int cruisePoint, int value) {

    }

    @Override
    public void cruiseExpand(String channel, CruiseEnum cruiseEnum, int cruiseRoute, int cruisePoint, int value) {

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

    }

    @Override
    public String getPresetName(int index) {
        return null;
    }

    @Override
    public PTZScope getPresetScope(String channel) {
        return null;
    }

    @Override
    public List<Temperature> measureAll() {
        return null;
    }

    @Override
    public Temperature measure(int infraredNo) {
        return null;
    }

    @Override
    public String getInfraredPointName(int presetNo, int infraredNo) {
        return null;
    }

    @Override
    public void setInfraredPointName(int presetNo, int infraredNo, String name) {

    }

    @Override
    public PTZ getCurrentPosition(String channel) {
        return null;
    }

    @Override
    public void gotoPresetPoint(String channel, int presetIndex, PTZ ptz) throws InterruptedException {

    }

    @Override
    public void locate(String channel, int pan, int tilt, int zoom) throws InterruptedException {

    }

    @Override
    public List<PresetPointInfo> getActivatedPresetPoints(String channel) {
        return null;
    }


    @Override
    public void setInfraredPoint(Temperature temperature) {

    }

    @Override
    public Temperature getInfraredPoint(int presetNo, int infraredNo) {
        return null;
    }

    @Override
    public void deleteInfraredPoint(int presetNo, int infraredNo) {

    }

    @Override
    public void reboot() {

    }

    @Override
    public void restore() {

    }

    @Override
    public void setZoomLimit(String channel, int zoomValue) {

    }

    @Override
    public void openLensInitialization(String channel) {

    }

    @Override
    public void closeLensInitialization(String channel) {

    }

    @Override
    public void setMaxElevationAngle(String channel, int elevationValue) {

    }

    @Override
    public void setScreen(String channel, ScreenEffectEnum screenEffectEnum, int value) {

    }

    @Override
    public ScreenEffect getScreen(String channel) {
        return null;
    }

    @Override
    public void resetScreen(String channel) {

    }

    @Override
    public void setDayNightConversionMode(String channel, DayNightEnum dayNightEnum) {

    }

    @Override
    public void setCustomDayNightConversionMode(String channel, Calendar dayStart, Calendar dayEnd) {

    }

    @Override
    public void setBackLightCompensationMode(String channel, BackLightEnum backLightEnum) {

    }

    @Override
    public void setBackLightCompensationMode(String channel, BackLightEnum backLightEnum, int value) {

    }

    @Override
    public void setWhiteBalanceMode(String channel, WhiteBalanceEnum whiteBalanceEnum) {

    }

    @Override
    public void openStrongLightInhibitionMode(String channel) {

    }

    @Override
    public void closeStrongLightInhibitionMode(String channel) {

    }

    @Override
    public void openPreventOverexposureMode(String channel) {

    }

    @Override
    public void closePreventOverexposureMode(String channel) {

    }

    @Override
    public void closeLightInhibitionAndPreventOverexposureMode(String channel) {

    }

    @Override
    public void setWideDynamicMode(String channel, WDREnum wdrEnum) {

    }

    @Override
    public void setWideDynamicMode(String channel, WDREnum wdrEnum, int value) {

    }

    @Override
    public void setNoiseReductionMode(String channel, NoiseReductionEnum noiseReductionEnum) {

    }

    @Override
    public void setNoiseReductionValue(String channel, NoiseReductionLevelEnum noiseReductionLevelEnum, int value) {

    }

    @Override
    public void openDefogMode(String channel) {

    }

    @Override
    public void closeDefogMode(String channel) {

    }

    @Override
    public void openElectronicStabilizationMode(String channel) {

    }

    @Override
    public void closeElectronicStabilizationMode(String channel) {

    }

    @Override
    public void setExposureMode(String channel, ExposureMode exposureMode) {

    }

    @Override
    public void setExposureParam(String channel, ExposureParam exposureParam, int value) {

    }

    @Override
    public void setFocusMode(String channel, FocusMode focusMode) {

    }

    @Override
    public void setFocusDistance(String channel, int value) {

    }

    @Override
    public void resetLens(String channel) {

    }

    @Override
    public void setLowIlluminationElectronicShutterMode(String channel, LowLightShutterEnum lowLightShutterEnum) {

    }

    @Override
    public void setVideoAndAudioMode(String channel, StreamTypeEnum streamTypeEnum, CompressionEnum compressionEnum, int value) {

    }

    @Override
    public void setAudioInputConfig(String channel, AudioInputEnum audioInputEnum, int value) {

    }

    @Override
    public void setPTZOSDConfigMode(String channel, PtzOSDParam ptzOSDParam, int value) {

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
}
