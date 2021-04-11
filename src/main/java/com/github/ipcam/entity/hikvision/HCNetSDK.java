package com.github.ipcam.entity.hikvision;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.StdCallLibrary;

/**
 * HCNetSDK
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public interface HCNetSDK extends Library {


    HCNetSDK hcNetSDK = (HCNetSDK) Native.loadLibrary("hcnetsdk",
            HCNetSDK.class);

    boolean NET_DVR_Init();

    boolean NET_DVR_Cleanup();

    boolean NET_DVR_Logout(int userHandle);

    int NET_DVR_Login_V30(String ip, int port, String username, String password,
                          NET_DVR_DEVICEINFO_V30 deviceInfo);

    int NET_DVR_GetLastError();

    String NET_DVR_GetErrorMsg(Pointer errorNo);

    int NET_DVR_RealPlay_V40(int userHandle, LPNET_DVR_PREVIEWINFO previewInfo,
                             FRealDataCallBack_V30 dataCallBack, Pointer user);

    boolean NET_DVR_StopRealPlay(int realHandle);

    boolean NET_DVR_SetSDKLocalCfg(int enumType, Structure buffer);

    boolean NET_DVR_SaveRealData_V30(int realHandle, int transType, String fileName);

    boolean NET_DVR_StopSaveRealData(int realHandle);

    boolean NET_DVR_MakeKeyFrame(int userHandle, int channel);

    boolean NET_DVR_MakeKeyFrameSub(int userHandle, int channel);

    boolean NET_DVR_PTZControl(int realHandle, int ptzCommand, int stop);

    boolean NET_DVR_PTZPreset(int realHandle, int presetCommand, int presetIndex);

    boolean NET_DVR_PTZCruise(int realHandle, int ptzCruiseCommand, int cruiseRoute, int cruisePoint, int input);

    boolean NET_DVR_PTZTrack(int realHandle, int ptzTrackCommand);

    boolean NET_DVR_PTZControlWithSpeed(int realHandle, int ptzCommand, int stop, int speed);

    int NET_DVR_FindFile(int userHandle, int channel, int fileType, NET_DVR_TIME startTime, NET_DVR_TIME stopTime);

    int NET_DVR_FindNextFile(int findHandle, NET_DVR_FIND_DATA findData);

    boolean NET_DVR_FindClose_V30(int findHandle);

    boolean NET_DVR_PlayBackControl(int playHandle, int controlCode, int inValue, IntByReference outValue);

    int NET_DVR_GetFileByTime(int userHandle, int channel, NET_DVR_TIME startTime,
                              NET_DVR_TIME stopTime, String savedFileName);

    boolean NET_DVR_SetConnectTime(int waitTime, int tryTimes);

    boolean NET_DVR_StopGetFile(int fileHandle);

    int NET_DVR_GetDownloadPos(int fileHandle);

    boolean NET_DVR_StartDVRRecord(int userHandle, int channel, int recordType);

    boolean NET_DVR_StopDVRRecord(int userHandle, int channel);

    boolean NET_DVR_CaptureJPEGPicture(int userHandle, int channel, NET_DVR_JPEGPARA jpegPara, String fileName);

    boolean NET_DVR_GetDVRConfig(int userHandle, int command, int channel, Pointer buffer,
                                 int outBufferSize, IntByReference bytesReturned);

    boolean NET_DVR_SetDVRConfig(int userHandle, int command, int channel, Structure buffer, int inBufferSize);

    boolean NET_DVR_ResetLens(int userHandle, int channel);

    boolean NET_DVR_StopVoiceCom(int voiceComHandle);

    boolean NET_DVR_GetCurrentAudioCompress(int userHandle, NET_DVR_COMPRESSION_AUDIO compressionAudio);

    int NET_DVR_StartVoiceCom_MR_V30(int userHandle, int voiceChan, VoiceDataCallBack voiceDataCallBack, Pointer user);

    boolean NET_DVR_SetVoiceDataCallBack(int voiceComHandle, boolean needCBNoEncData,
                                         VoiceDataCallBack voiceDataCallBack, Pointer user);

    boolean NET_DVR_VoiceComSendData(int voiceComHandle, Pointer sendBuf, int bufSize);

    boolean NET_DVR_GetSTDConfig(int userHandle, int dwCommand, Pointer lpConfigParam);

    int NET_DVR_StartRemoteConfig(int userHandle, int command, Pointer buffer, int inBufferLen,
                                  FremoteConfigCallback stateCallback, Pointer userData);

    boolean NET_DVR_SetDVRMessageCallBack_V31(FMSGCallBack_V31 messageCallBack, Pointer user);

    int NET_DVR_SetupAlarmChan_V41(int userHandle, NET_DVR_SETUPALARM_PARAM setupParam);

    boolean NET_DVR_CloseAlarmChan_V30(int alarmHandle);

    boolean NET_DVR_StopRemoteConfig(int handle);

    boolean NET_DVR_SetSTDConfig(int userHandle, int command, LPNET_DVR_STD_CONFIG configParam);

    interface FMSGCallBack_V31 extends StdCallLibrary.StdCallCallback {
        boolean invoke(int command, NET_DVR_ALARMER alarm, Pointer alarmInfo, int dwBufLen, Pointer user);
    }

    interface FremoteConfigCallback extends StdCallLibrary.StdCallCallback {
        void invoke(int type, Pointer buffer, int bufLen, Pointer userData);
    }

    interface VoiceDataCallBack extends StdCallLibrary.StdCallCallback {
        void invoke(int voiceComHandle, Pointer recvDataBuffer, int bufSize, byte audioFlag, Pointer user);
    }

    interface FRealDataCallBack_V30 extends StdCallLibrary.StdCallCallback {
        void invoke(int realHandle, int dataType, ByteByReference buffer, int bufSize, Pointer user) throws InterruptedException;
    }

    boolean NET_DVR_STDXMLConfig(int lUserID, NET_DVR_XML_CONFIG_INPUT lpInputParam, NET_DVR_XML_CONFIG_OUTPUT lpOutputParam);

    long NET_DVR_SDKChannelToISAPI(long lUserID, long lInChannel, boolean bSDKToISAPI);

    boolean NET_DVR_PTZControlWithSpeed_Other(int userHandel, int channel, int dwPTZCommand, int dwStop, int dwSpeed);

    boolean NET_DVR_PTZControl_Other(int userHandel, int channel, int command, int dwStop);

    boolean NET_DVR_PTZPreset_Other(int userHandel, int channel, int command, int dwPresetIndex);

    boolean NET_DVR_PTZCruise_Other(int userHandel, int channel, int dwPTZCruiseCmd, int byCruiseRoute, int byCruisePoint, int wInput);

    boolean NET_DVR_PTZTrack_Other(int userHandel, int channel, int dwPTZTrackCmd);


}
