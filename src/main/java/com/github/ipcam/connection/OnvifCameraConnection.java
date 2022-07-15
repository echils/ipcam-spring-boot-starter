package com.github.ipcam.connection;

import com.github.ipcam.entity.CameraDriver;
import com.github.ipcam.entity.NetworkCamera;
import com.github.ipcam.entity.PTZ;
import com.github.ipcam.entity.exception.CameraConnectionException;
import com.github.ipcam.entity.exception.OnvifException;
import com.github.ipcam.entity.infos.CameraInfo;
import com.github.ipcam.entity.infos.PresetPointInfo;
import com.github.ipcam.entity.onvif.OnvifExecutor;
import com.github.ipcam.entity.onvif.OnvifResultData;
import com.github.ipcam.entity.onvif.command.*;
import com.github.ipcam.entity.onvif.modes.OnvifDeviceInfo;
import com.github.ipcam.entity.onvif.modes.OnvifMediaProfile;
import com.github.ipcam.entity.reference.PTZControlEnum;
import com.github.ipcam.entity.reference.PresetEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * OnvifCameraConnection
 *
 * @author echils
 */
public class OnvifCameraConnection extends AbstractCameraConnection {

    private static final Logger logger = LoggerFactory.getLogger(OnvifCameraConnection.class);

    private OnvifExecutor onvifExecutor;

    private Map<String, OnvifMediaProfile> mediaProfileMap = new HashMap<>();

    @Override
    public void connect(NetworkCamera camera) {
        logger.info("Connecting to the onvif camera...");
        if (this.isConnected()) {
            logger.error("Already connected to onvif camera with：{}", networkCamera);
            throw new CameraConnectionException("Already connected to onvif camera");
        }
        if (camera == null || camera.isIllegal()) {
            logger.error("The onvif camera is illegal：{}", camera);
            throw new CameraConnectionException("The onvif camera is illegal");
        }
        OnvifExecutor onvifExecutor = new OnvifExecutor(camera);
        OnvifResultData<List<OnvifMediaProfile>> onvifResultData
                = onvifExecutor.execute(new GetMediaProfilesCommand());
        if (!onvifResultData.isSuccess() && CollectionUtils.isEmpty(onvifResultData.getData())) {
            throw new CameraConnectionException("Connect to onvif camera failed");
        }
        logger.info("Connect to the onvif camera success");
        this.userHandle = 0L;
        this.networkCamera = camera;
        this.onvifExecutor = onvifExecutor;
        mediaProfileMap.putAll(onvifResultData.getData().stream().collect(Collectors.toMap(mediaProfile
                -> "A" + mediaProfile.getToken().split("_")[1], mediaProfile -> mediaProfile)));
    }


    @Override
    public void close() throws CameraConnectionException {
        logger.info("Disconnecting from the onvif camera {}...", networkCamera.getIp());
        if (this.isConnected()) onvifExecutor.destroy();
        logger.info("Disconnected from the onvif camera {}...", networkCamera.getIp());
    }

    @Override
    public List<String> getChannels() {
        Set<String> channelList = mediaProfileMap.keySet();
        logger.info("Camera {} current channel list:{}", networkCamera.getIp(), channelList);
        return new ArrayList<>(channelList);
    }

    @Override
    public CameraInfo getBasicInfo() {
        CameraInfo cameraInfo = new CameraInfo();
        OnvifResultData<OnvifDeviceInfo> resultData = onvifExecutor.execute(new DeviceInfoCommand());
        if (!resultData.isSuccess()) {
            throw new OnvifException(resultData.getMsg());
        }
        OnvifDeviceInfo onvifDeviceInfo = resultData.getData();
        cameraInfo.setIp(networkCamera.getIp());
        cameraInfo.setModelNo(onvifDeviceInfo.getModel());
        cameraInfo.setSerialNo(onvifDeviceInfo.getSerialNumber());
        cameraInfo.setUsername(networkCamera.getUsername());
        cameraInfo.setPassword(networkCamera.getPassword());
        return cameraInfo;
    }


    @Override
    public void imageOutput(String channel, String targetPath) {
        OnvifMediaProfile onvifMediaProfile = mediaProfileMap.get(channel);
        if (onvifMediaProfile == null) {
            throw new CameraConnectionException("No support the channel [" + channel + "]");
        }
        OnvifResultData<File> resultData = onvifExecutor
                .execute(new CaptureCommand(mediaProfileMap.get(channel), targetPath));
        if (!resultData.isSuccess()) {
            throw new OnvifException(resultData.getMsg());
        }
    }


    @Override
    public void control(String channel, PTZControlEnum controlEnum, int status, int speed) {
        Assert.notNull(controlEnum, "Preset command is null");
        OnvifMediaProfile onvifMediaProfile = mediaProfileMap.get(channel);
        if (onvifMediaProfile == null) {
            throw new CameraConnectionException("No support the channel [" + channel + "]");
        }
        if (PTZControlEnum.PTZ_STOP == controlEnum || PTZControlEnum.PTZ_STOP.key() == status) {
            OnvifResultData<Void> resultData = onvifExecutor.execute(new ControlStopCommand(onvifMediaProfile));
            if (!resultData.isSuccess()) {
                throw new OnvifException(resultData.getMsg());
            }
            return;
        }
        OnvifResultData<Void> resultData =
                onvifExecutor.execute(new ControlMoveCommand(onvifMediaProfile, controlEnum, speed));
        if (!resultData.isSuccess()) {
            throw new OnvifException(resultData.getMsg());
        }
    }


    @Override
    public int preset(String channel, PresetEnum presetCommand, int index) {
        Assert.notNull(presetCommand, "Preset command is null");
        OnvifMediaProfile onvifMediaProfile = mediaProfileMap.get(channel);
        if (onvifMediaProfile == null) {
            throw new CameraConnectionException("No support the channel [" + channel + "]");
        }
        switch (presetCommand) {
            case GOTO_PRESET:
                OnvifResultData<Void> gotoResultData =
                        onvifExecutor.execute(new GotoPresetCommand(onvifMediaProfile, index));
                if (!gotoResultData.isSuccess()) throw new OnvifException(gotoResultData.getMsg());
                break;
            case SET_PRESET:
                OnvifResultData<PresetPointInfo> setResultData =
                        onvifExecutor.execute(new SetPresetCommand(onvifMediaProfile));
                if (!setResultData.isSuccess()) throw new OnvifException(setResultData.getMsg());
                index = setResultData.getData().getId();
                break;
            case CLE_PRESET:
                OnvifResultData<Void> delResultData =
                        onvifExecutor.execute(new RemovePresetCommand(onvifMediaProfile, index));
                if (!delResultData.isSuccess()) throw new OnvifException(delResultData.getMsg());
                break;
        }
        return index;
    }


    @Override
    public PTZ getCurrentPosition(String channel) {
        OnvifMediaProfile onvifMediaProfile = mediaProfileMap.get(channel);
        if (onvifMediaProfile == null) {
            throw new CameraConnectionException("No support the channel [" + channel + "]");
        }
        OnvifResultData<PTZ> resultData =
                onvifExecutor.execute(new GetCurrentPositionCommand(mediaProfileMap.get(channel)));
        if (!resultData.isSuccess()) {
            throw new OnvifException(resultData.getMsg());
        }
        return resultData.getData();
    }


    @Override
    public void gotoPresetPoint(String channel, int presetIndex, PTZ ptz) throws InterruptedException {
        logger.info("Camera:{} goto preset point with channel:{} and index:{}", networkCamera.getIp(), channel, presetIndex);
        this.preset(channel, PresetEnum.GOTO_PRESET, presetIndex);
        long time = System.currentTimeMillis();
        while (true) {
            PTZ curPTZ = this.getCurrentPosition(channel);
            if (curPTZ.equals(ptz)) {
                break;
            }
            if (TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - time) > 30) {
                throw new CameraConnectionException("Goto preset timeout 30 seconds.");
            }
            Thread.sleep(500);
        }
        logger.info("Goto preset complete. used {} seconds", TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - time));
    }


    @Override
    public List<PresetPointInfo> getActivatedPresetPoints(String channel) {
        logger.info("Camera:{} get preset points that have been used with channel:{}", networkCamera.getIp(), channel);
        OnvifMediaProfile onvifMediaProfile = mediaProfileMap.get(channel);
        if (onvifMediaProfile == null) {
            throw new CameraConnectionException("No support the channel [" + channel + "]");
        }
        OnvifResultData<List<PresetPointInfo>> resultData =
                onvifExecutor.execute(new GetPresetsCommand(onvifMediaProfile));
        if (!resultData.isSuccess()) {
            throw new OnvifException(resultData.getMsg());
        }
        return resultData.getData();
    }

    @Override
    public CameraDriver support() {
        return CameraDriver.ONVIF;
    }

}
