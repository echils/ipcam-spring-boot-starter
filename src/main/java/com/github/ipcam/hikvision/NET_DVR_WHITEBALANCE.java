package com.github.ipcam.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_WHITEBALANCE
 *
 * @author echils
 */
public class NET_DVR_WHITEBALANCE extends Structure {

    /**
     * 0- Manual white balance (MWB), 1- Automatic white balance 1 (AWB1, small range), 2- Automatic white balance 2
     * (AWB2, wide range, 2200K-15000K), 3- Locked white balance (Locked WB), 4- Outdoor, 5- Indoor, 6- Fluorescent,
     * 7- Sodium, 8- Auto-Track, 9- One Push, 10- Auto-Outdoor, 11- Auto-SodiumLight, 12- Mercury Lamp
     * 13- Automatic white balance (AUTO), 14- Incandescentlamp, 15- Warm Light Lamp, 16- Natural Light
     */
    public byte byWhiteBalanceMode;

    /**
     * Manual white balance is effective, manual white balance R gain
     */
    public byte byWhiteBalanceModeRGain;

    /**
     * Efficient when manual white balance, manual white balance B gain
     */
    public byte byWhiteBalanceModeBGain;

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes = new byte[5];

}
