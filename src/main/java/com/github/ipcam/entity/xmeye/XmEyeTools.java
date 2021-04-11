package com.github.ipcam.entity.xmeye;


import com.github.ipcam.entity.exception.XmEyeException;

import static com.github.ipcam.entity.exception.XmEyeException.ErrorMap;
import static com.github.ipcam.entity.xmeye.NetSDK.netSDK;

/**
 * XmEyeTools
 *
 * @author echils
 * @since 2020-03-25 13:41
 */
public class XmEyeTools {


    /**
     * Get exception code and message
     *
     * @return
     */
    public static String getErrorMsg() {
        return getErrorMsg(netSDK.H264_DVR_GetLastError());
    }


    /**
     * Get exception code and message
     *
     * @return
     */
    public static String getErrorMsg(int errorCode) {
        return "Error code：[" + errorCode + "],Error msg: [" + ErrorMap.get(errorCode) + "]";
    }


    /**
     * handle the chanelNum to get the realChanelNum
     * <p>
     * the "A" ,"I","D" Used to distinguish between different types of cameras
     * example：channelNum= 'D10',  After deal with  get the realChanelNum = '10';
     *
     * @param channelNum
     * @return
     */
    public static int handleChannel(String channelNum) {
        if (channelNum.equals("A1")) {
            return 0;
        }
        throw new XmEyeException("Error code：[null],Error msg: [channelNum error]");
    }


}
