package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_WHITEBALANCE
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_WHITEBALANCE extends Structure {
    public byte byWhiteBalanceMode;
    public byte byWhiteBalanceModeRGain;
    public byte byWhiteBalanceModeBGain;
    public byte[] byRes = new byte[5];
}
