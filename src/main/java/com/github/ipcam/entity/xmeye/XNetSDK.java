package com.github.ipcam.entity.xmeye;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;

/**
 * XNetSDK
 *
 * @author echils
 * @since 2020-06-01 11:07
 */
public interface XNetSDK extends Library {

    XNetSDK xNetSDK = (XNetSDK) Native.loadLibrary("XNetSDK",
            XNetSDK.class);

    boolean XSDK_DevModifyIPConfig(long userHandle, Structure pConfig, int nConfigLen, int nSeq, int nTimeout);

    boolean XSDK_EncryptPassword(String inputPswBuf, Pointer outputPswBuf, int PswSize);


}
