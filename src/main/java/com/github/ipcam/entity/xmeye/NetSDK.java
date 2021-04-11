package com.github.ipcam.entity.xmeye;

import com.github.ipcam.entity.jnax.W32API;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
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

    int H264_DVR_Init(fDisConnect disConnect, int user);


    boolean H264_DVR_Cleanup();


    int H264_DVR_GetLastError();


    boolean H264_DVR_SetConnectTime(int waitTime, int tryTimes);


    long H264_DVR_Login(String ip, int port, String userName, String password,
                        H264_DVR_DEVICEINFO deviceInfo, IntByReference error, int socketType);


    int H264_DVR_Logout(long userHandle);


    long H264_DVR_RealPlay(long userHandle, H264_DVR_CLIENTINFO clientInfo);


    boolean H264_DVR_StopRealPlay(long realHandle, W32API.HWND hWnd);


    boolean H264_DVR_SetRealDataCallBack(long realHandle, fRealDataCallBack realData, int user);


    boolean H264_DVR_CatchPic(long userHandle, int channel, String fileName, int type);


    boolean H264_DVR_MakeKeyFrame(long userHandle, int channel, int stream);


    interface fDisConnect extends StdCallLibrary.StdCallCallback {
        void invoke(int userHandle, String dvRIP, int nDVRPort, int user);
    }

    interface fRealDataCallBack extends StdCallLibrary.StdCallCallback {
        void invoke(long realHandle, int dwDataType, Pointer pBuffer,
                    long buffSize, long user);
    }

}
