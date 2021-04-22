package com.github.ipcam.entity.xmeye;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

/**
 * XNetSDK
 *
 * @author echils
 * @since 2020-06-01 11:07
 */
public interface XNetSDK extends Library {

    XNetSDK xNetSDK = (XNetSDK) Native.loadLibrary("XNetSDK",
            XNetSDK.class);

    /**
     * Encrypted the password of the device
     */
    boolean XSDK_EncryptPassword(String inputPswBuf, Pointer outputPswBuf, int PswSize);

}
