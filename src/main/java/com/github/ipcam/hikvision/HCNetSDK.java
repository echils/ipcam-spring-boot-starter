package com.github.ipcam.hikvision;

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
 */
public interface HCNetSDK extends Library {


    HCNetSDK hcNetSDK = (HCNetSDK) Native.loadLibrary("hcnetsdk",
            HCNetSDK.class);

    /**
     * Initialize the SDK and call other SDK functions.
     */
    boolean NET_DVR_Init();

    /**
     * Release the SDK resource and call it before the program ends.
     */
    boolean NET_DVR_Cleanup();

    /**
     * Restart the device.
     */
    boolean NET_DVR_RebootDVR(int lUserID);

    /**
     * Restore device default parameters.
     */
    boolean NET_DVR_RestoreConfig(int lUserID);

    /**
     * User logout
     */
    boolean NET_DVR_Logout(int userHandle);

    /**
     * User login
     */
    int NET_DVR_Login_V30(String ip, int port, String username, String password,
                          NET_DVR_DEVICEINFO_V30 deviceInfo);

    /**
     * Returns the error code for the last operation.
     */
    int NET_DVR_GetLastError();

    /**
     * Returns the error code message for the last operation.
     */
    String NET_DVR_GetErrorMsg(Pointer errorNo);

    /**
     * Real-time preview (support for multi-code streaming).
     */
    int NET_DVR_RealPlay_V40(int userHandle, LPNET_DVR_PREVIEWINFO previewInfo,
                             FRealDataCallBack_V30 dataCallBack, Pointer user);

    /**
     * Stop previewing.
     */
    boolean NET_DVR_StopRealPlay(int previewHandle);

    /**
     * Set SDK local parameters.
     */
    boolean NET_DVR_SetSDKLocalCfg(int enumType, Structure buffer);

    /**
     * Capture data in the specified target encapsulation format and place it in the specified file.
     */
    boolean NET_DVR_SaveRealData_V30(int previewHandle, int transType, String fileName);

    /**
     * Stop data capture.
     */
    boolean NET_DVR_StopSaveRealData(int previewHandle);

    /**
     * The main stream generates a key frame dynamically.
     */
    boolean NET_DVR_MakeKeyFrame(int userHandle, int channel);

    /**
     * The sub stream generates a key frame dynamically.
     */
    boolean NET_DVR_MakeKeyFrameSub(int userHandle, int channel);

    /**
     * Pad head preset point operation (need to start preview).
     */
    boolean NET_DVR_PTZPreset(int previewHandle, int presetCommand, int presetIndex);

    /**
     * Tackle cruise operation, need to start preview.
     */
    boolean NET_DVR_PTZCruise(int previewHandle, int ptzCruiseCommand, int cruiseRoute, int cruisePoint, int input);

    /**
     * To operate the trunkhead track, you need to start the preview first.
     */
    boolean NET_DVR_PTZTrack(int previewHandle, int ptzTrackCommand);

    /**
     * Pan-head control operation with speed (need to start image preview).
     */
    boolean NET_DVR_PTZControlWithSpeed(int previewHandle, int ptzCommand, int stop, int speed);

    /**
     * Find equipment video files according to file type and time.
     */
    int NET_DVR_FindFile(int userHandle, int channel, int fileType, NET_DVR_TIME startTime, NET_DVR_TIME stopTime);

    /**
     * Fetch the file information found one by one.
     */
    int NET_DVR_FindNextFile(int findHandle, NET_DVR_FIND_DATA findData);

    /**
     * Close file lookup and release resources.
     */
    boolean NET_DVR_FindClose_V30(int findHandle);

    /**
     * Controls the state of video playback.
     */
    boolean NET_DVR_PlayBackControl(int playHandle, int controlCode, int inValue, IntByReference outValue);

    /**
     * Download video files by time.
     */
    int NET_DVR_GetFileByTime(int userHandle, int channel, NET_DVR_TIME startTime,
                              NET_DVR_TIME stopTime, String savedFileName);

    /**
     * Sets the network connection timeout and the number of connection attempts.
     */
    boolean NET_DVR_SetConnectTime(int waitTime, int tryTimes);

    /**
     * Stop downloading video files.
     */
    boolean NET_DVR_StopGetFile(int fileHandle);

    /**
     * Gets the current progress of downloading the video file.
     */
    int NET_DVR_GetDownloadPos(int fileHandle);

    /**
     * A single frame of data is captured and saved as a JPEG.
     */
    boolean NET_DVR_CaptureJPEGPicture(int userHandle, int channel, NET_DVR_JPEGPARA jpegPara, String fileName);

    /**
     * Gets the configuration information for the device.
     */
    boolean NET_DVR_GetDVRConfig(int userHandle, int command, int channel, Pointer buffer,
                                 int outBufferSize, IntByReference bytesReturned);

    /**
     * Sets the configuration information for the device.
     */
    boolean NET_DVR_SetDVRConfig(int userHandle, int command, int channel, Structure buffer, int inBufferSize);

    /**
     * Restore the default position of lens motor.
     */
    boolean NET_DVR_ResetLens(int userHandle, int channel);

    /**
     * Stop voice intercom or voice forwarding.
     */
    boolean NET_DVR_StopVoiceCom(int voiceComHandle);

    /**
     * Gets the currently effective intercom audio compression parameters.
     */
    boolean NET_DVR_GetCurrentAudioCompress(int userHandle, NET_DVR_COMPRESSION_AUDIO compressionAudio);

    /**
     * Start voice forwarding and get encoded audio data.
     */
    int NET_DVR_StartVoiceCom_MR_V30(int userHandle, int voiceChan, VoiceDataCallBack voiceDataCallBack, Pointer user);

    /**
     * Register the callback function to capture the voice data.
     */
    boolean NET_DVR_SetVoiceDataCallBack(int voiceComHandle, boolean needCBNoEncData,
                                         VoiceDataCallBack voiceDataCallBack, Pointer user);

    /**
     * Forward voice data.
     */
    boolean NET_DVR_VoiceComSendData(int voiceComHandle, Pointer sendBuf, int bufSize);

    /**
     * Gets the STD configuration information for the device.
     */
    boolean NET_DVR_GetSTDConfig(int userHandle, int dwCommand, Pointer lpConfigParam);

    /**
     * Start the remote configuration.
     */
    int NET_DVR_StartRemoteConfig(int userHandle, int command, Pointer buffer, int inBufferLen,
                                  FRemoteConfigCallback stateCallback, Pointer userData);

    /**
     * Stop the remote configuration.
     */
    boolean NET_DVR_StopRemoteConfig(int handle);

    /**
     * Sets the STD configuration information for the device.
     */
    boolean NET_DVR_SetSTDConfig(int userHandle, int command, LPNET_DVR_STD_CONFIG configParam);

    /**
     * ISAPI protocol command passthrough.
     */
    boolean NET_DVR_STDXMLConfig(int lUserID, NET_DVR_XML_CONFIG_INPUT lpInputParam, NET_DVR_XML_CONFIG_OUTPUT lpOutputParam);

    /**
     * Conversion between SDK channel number and ISAPI protocol channel number.
     */
    long NET_DVR_SDKChannelToISAPI(long lUserID, long lInChannel, boolean bSDKToISAPI);

    /**
     * Pan-head control operation with speed (without initiating image preview).
     */
    boolean NET_DVR_PTZControlWithSpeed_Other(int userHandel, int channel, int dwPTZCommand, int dwStop, int dwSpeed);

    /**
     * Pad head preset point operation.
     */
    boolean NET_DVR_PTZPreset_Other(int userHandel, int channel, int command, int dwPresetIndex);

    /**
     * Pan-head cruise operation.
     */
    boolean NET_DVR_PTZCruise_Other(int userHandel, int channel, int dwPTZCruiseCmd, int byCruiseRoute, int byCruisePoint, int wInput);

    /**
     * Pan-head track operation.
     */
    boolean NET_DVR_PTZTrack_Other(int userHandel, int channel, int dwPTZTrackCmd);

    /**
     * Remote config callback
     */
    interface FRemoteConfigCallback extends StdCallLibrary.StdCallCallback {
        void invoke(int type, Pointer buffer, int bufLen, Pointer userData);
    }

    /**
     * Voice callback
     */
    interface VoiceDataCallBack extends StdCallLibrary.StdCallCallback {
        void invoke(int voiceComHandle, Pointer recvDataBuffer, int bufSize, byte audioFlag, Pointer user);
    }

    /**
     * Video data callback
     */
    interface FRealDataCallBack_V30 extends StdCallLibrary.StdCallCallback {
        void invoke(int previewHandle, int dataType, ByteByReference buffer, int bufSize, Pointer user) throws InterruptedException;
    }


}
