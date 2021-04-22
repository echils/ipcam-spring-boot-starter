package com.github.ipcam.entity.xmeye;

import com.github.ipcam.entity.jnax.W32API;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.StdCallLibrary;

/**
 * NetSDK
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public interface NetSDK extends Library {


    NetSDK netSDK = (NetSDK) Native.loadLibrary("NetSdk",
            NetSDK.class);

    /**
     * Initialize the SDK and call other SDK functions.
     */
    int H264_DVR_Init(fDisConnect disConnect, int user);

    /**
     * Release the SDK resource and call it before the program ends.
     */
    boolean H264_DVR_Cleanup();

    /**
     * Returns the error code for the last operation.
     */
    int H264_DVR_GetLastError();

    /**
     * Sets the network connection timeout and the number of connection attempts.
     */
    boolean H264_DVR_SetConnectTime(int waitTime, int tryTimes);

    /**
     * User login
     */
    long H264_DVR_Login(String ip, int port, String userName, String password,
                        H264_DVR_DEVICEINFO deviceInfo, IntByReference error, int socketType);

    /**
     * User logout
     */
    int H264_DVR_Logout(long userHandle);

    /**
     * Real-time preview (support for multi-code streaming).
     */
    long H264_DVR_RealPlay(long userHandle, H264_DVR_CLIENTINFO clientInfo);

    /**
     * Stop previewing.
     */
    boolean H264_DVR_StopRealPlay(long previewHandle, W32API.HWND hWnd);

    /**
     * Config Video data callback
     */
    boolean H264_DVR_SetRealDataCallBack(long previewHandle, fRealDataCallBack realData, int user);

    /**
     * A single frame of data is captured
     */
    boolean H264_DVR_CatchPic(long userHandle, int channel, String fileName, int type);

    /**
     * The stream generates a key frame dynamically.
     */
    boolean H264_DVR_MakeKeyFrame(long userHandle, int channel, int stream);

    /**
     * Sets the configuration information for the device.
     */
    boolean H264_DVR_SetDevConfig(long lHandle, long dwCommand, int channel, Structure lpInBuffer, long dwInBufferSize);

    /**
     * Device connect callback
     */
    interface fDisConnect extends StdCallLibrary.StdCallCallback {
        void invoke(int userHandle, String dvRIP, int nDVRPort, int user);
    }

    /**
     * Video data callback
     */
    interface fRealDataCallBack extends StdCallLibrary.StdCallCallback {
        void invoke(long previewHandle, int dwDataType, Pointer pBuffer,
                    long buffSize, long user);
    }

}
