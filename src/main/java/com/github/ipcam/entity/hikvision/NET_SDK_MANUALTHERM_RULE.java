package com.github.ipcam.entity.hikvision;

import com.github.ipcam.entity.comm.StructureContext;
import com.sun.jna.Structure;

/**
 * NET_SDK_MANUALTHERM_RULE
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_SDK_MANUALTHERM_RULE extends Structure {

    /**
     * Rule ID, 0- means invalid, starts at 1
     */
    public byte byRuleID;

    /**
     * Enable: 0- No, 1- Yes
     */
    public byte byEnable;

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes1 = new byte[2];

    /**
     * Rule name
     */
    public byte[] szRuleName = new byte[StructureContext.NAME_LEN];

    /**
     * Rule calibration type: 0-point, 1-box, 2-line
     */
    public byte byRuleCalibType;

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes2 = new byte[3];

    /**
     * Point temperature measurement is effective when the calibration type is 0-point
     */
    public NET_SDK_POINT_THERMOMETRY struPointTherm;

    /**
     * Area temperature measurement, when the calibration type is 1-frame, 2-line effective
     */
    public NET_SDK_REGION_THERMOMETRY struRegionTherm;

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes = new byte[512];

}
