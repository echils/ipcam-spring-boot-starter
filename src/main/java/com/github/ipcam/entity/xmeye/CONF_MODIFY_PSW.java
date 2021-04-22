package com.github.ipcam.entity.xmeye;

import com.sun.jna.Structure;

/**
 * CONF_MODIFY_PSW
 *
 * @author echils
 * @since 2020-06-01 11:31
 */
public class CONF_MODIFY_PSW extends Structure {

    /**
     * The username of the device
     */
    public byte[] sUserName = new byte[32];

    /**
     * The password of the device
     */
    public byte[] Password = new byte[32];

    /**
     * The new password of the device
     */
    public byte[] NewPassword = new byte[32];

}
