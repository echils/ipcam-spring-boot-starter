package com.github.ipcam;

import com.github.ipcam.entity.*;
import com.github.ipcam.entity.exception.HikException;
import com.github.ipcam.entity.hikvision.*;
import com.github.ipcam.entity.reference.PTZControlEnum;
import com.github.ipcam.entity.reference.PresetEnum;
import com.github.ipcam.entity.reference.StreamTypeEnum;
import com.github.ipcam.support.ICameraNVRSupport;
import com.github.ipcam.support.ICameraPTZSupport;
import com.github.ipcam.support.ICameraThermalSupport;
import com.sun.jna.ptr.IntByReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.OutputStream;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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

    /**
     * Login into network camera with waitTime and tryTime
     *
     * @param ip       The ip of camera
     * @param port     The port of camera
     * @param username The username of camera
     * @param password The password of camera
     * @param waitTime The waitTime of camera
     * @param tryTime  The tryTime of camera
     * @return
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
        List<String> channelList = new LinkedList();
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
            throw new HikException("New password can not be blank");
        }
        newPassword = newPassword.trim();
        if (newPassword.length() > PASSWD_LEN || newPassword.length() < MIN_PASSWD_LEN) {
            throw new HikException("New password length must be between 8-16");
        }

        NET_DVR_USER_V30 param = new NET_DVR_USER_V30();
        if (!hcNetSDK.NET_DVR_GetDVRConfig(userHandle.intValue(), NET_DVR_GET_USERCFG_V30, handleChannel(channel),
                param.getPointer(), ISAPI_DATA_LEN, new IntByReference(param.size()))) {
            throw new HikException(getErrorMsg());
        }
        param.read();
        param.struUser[0].sPassword = new byte[PASSWD_LEN];
        param.write();
        param.struUser[0].sPassword = newPassword.getBytes();
        param.write();
        if (!hcNetSDK.NET_DVR_SetDVRConfig(userHandle.intValue(), NET_DVR_SET_USERCFG_V30, handleChannel(channel),
                param, param.size())) {
            throw new HikException(getErrorMsg());
        }
        if (!hcNetSDK.NET_DVR_GetDVRConfig(userHandle.intValue(), NET_DVR_GET_USERCFG_V30, handleChannel(channel),
                param.getPointer(), ISAPI_DATA_LEN, new IntByReference(param.size()))) {
            throw new HikException(getErrorMsg());
        }
    }

    @Override
    public CameraInfo getBasicInfo(String channel) {
        return null;
    }

    @Override
    public void imageOutput(String channel, String targetPath) {
        logger.info("Camera {} image output with channel:{}", networkCamera.getIp(), channel);
        createParentDirectory(targetPath);
        if (!hcNetSDK.NET_DVR_CaptureJPEGPicture(Math.toIntExact(userHandle), handleChannel(channel),
                new NET_DVR_JPEGPARA(PICTURE_SIZE, PICTURE_QUALITY), targetPath)) {
            throw new HikException(getErrorMsg());
        }
    }

    @Override
    public void videoOutput(String channel, Date startTime, Date endTime, String targetPath) throws InterruptedException {
//        String startDate = dateFormat.format(startTime);
//        String endDate = dateFormat.format(endTime);
//        logger.info("Camera:{} video output with channel:{} startTime:{} endTime{}", ipv4, channel, startDate, endDate);
//        if (findRecord(Math.toIntExact(userHandle), channel, startDate, endDate) == NET_DVR_FILE_NOFIND) {
//            logger.error("No video to download with ip:{} channel:{} startTime:{} endTime{}", ipv4, channel, startDate, endDate);
//            return;
//        }
//        // set the file splitSize
//        NET_DVR_LOCAL_GENERAL_CFG cfg = new NET_DVR_LOCAL_GENERAL_CFG();
//        cfg.byNotSplitRecordFile = SPLIT;
//        hcNetSDK.NET_DVR_SetSDKLocalCfg(NET_DVR_LOCAL_CFG_TYPE_GENERAL, cfg);
//
//        //start to download file
//        createParentDirectory(targetPath);
//        int downloadHandle = hcNetSDK.NET_DVR_GetFileByTime(Math.toIntExact(userHandle), handleChannel(channel),
//                transferTime(startDate), transferTime(endDate), targetPath);
//        if (downloadHandle == FAILED) {
//            throw new HikException(getErrorMsg());
//        } else {
//            //breakpoint continuation is not supported
//            boolean result = hcNetSDK.NET_DVR_PlayBackControl(downloadHandle, DownloadEnum.NET_DVR_PLAYSTART.key(),
//                    INIT, null);
//            if (!result) {
//                throw new HikException(getErrorMsg());
//            }
//        }
//        boolean downloading = true;
//        while (downloading) {
//            int number = hcNetSDK.NET_DVR_GetDownloadPos(downloadHandle);
//            logger.debug("video startTime:{} to endTime:{} download progress:{}", startDate, endDate, number);
//            if (number == FAILED) {
//                throw new HikException(getErrorMsg());
//            } else if (number != COMPLETED) {
//                Thread.sleep(1000);
//            } else {
//                downloading = false;
//            }
//        }
//        if (!hcNetSDK.NET_DVR_StopGetFile(downloadHandle)) {
//            throw new HikException(getErrorMsg());
//        }
    }

    @Override
    public void audioOutput(String targetPath, int duration) throws InterruptedException {

    }

    @Override
    public String videoStreamOutput(String channel, StreamTypeEnum streamType, String path) {
        return null;
    }

    @Override
    public String videoStreamOutput(String channel, StreamTypeEnum streamType, OutputStream outputStream) {
        return null;
    }

    @Override
    public void stopVideoStreamOutput(String dataOutputHandle) {

    }

    @Override
    public List<NVRChannelInfo> getChannelInfo() {
        return null;
    }

    @Override
    public void control(String channel, PTZControlEnum controlEnum, int status, int speed) {

    }

    @Override
    public void controlExpand(String channel, PTZControlEnum controlEnum, int status, int speed) {

    }

    @Override
    public void preset(String channel, PresetEnum presetEnum, int index) {

    }

    @Override
    public void presetExpand(String channel, PresetEnum presetEnum, int index) {

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
}
