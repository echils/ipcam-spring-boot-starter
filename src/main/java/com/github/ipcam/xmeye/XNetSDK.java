package com.github.ipcam.xmeye;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

/**
 * XNetSDK
 *
 * @author echils
 */
public interface XNetSDK extends Library {

    XNetSDK xNetSDK = (XNetSDK) Native.loadLibrary("XNetSDK",
            XNetSDK.class);

    /**
     * Encrypted the password of the device
     */
    boolean XSDK_EncryptPassword(String inputPswBuf, Pointer outputPswBuf, int PswSize);

}
