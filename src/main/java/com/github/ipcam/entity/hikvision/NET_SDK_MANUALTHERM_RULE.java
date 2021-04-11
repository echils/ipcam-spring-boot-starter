package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * NET_SDK_MANUALTHERM_RULE
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_SDK_MANUALTHERM_RULE extends Structure {

    public byte byRuleID;
    public byte byEnable;
    public byte[] byRes1 = new byte[2];
    public byte[] szRuleName = new byte[NetworkCameraContext.NAME_LEN];
    public byte byRuleCalibType;
    public byte[] byRes2 = new byte[3];
    public NET_SDK_POINT_THERMOMETRY struPointTherm;
    public NET_SDK_REGION_THERMOMETRY struRegionTherm;
    public byte[] byRes = new byte[512];
}
