package com.github.ipcam.hikvision;

import com.github.ipcam.entity.comm.STRUCTURE_CONTEXT;
import com.sun.jna.Structure;

/**
 * NET_DVR_THERMOMETRY_PRESETINFO_PARAM
 *
 * @author echils
 */
public class NET_DVR_THERMOMETRY_PRESETINFO_PARAM extends Structure {

    /**
     * Enable: 0- No, 1- Yes
     */
    public byte byEnabled;

    /**
     * Rule ID, starting at 1 (List internal validates data), 0 means invalid
     */
    public byte byRuleID;

    /**
     * Distance, unit: m, value range: [0, 10000]
     */
    public short wDistance;

    /**
     * Emissivity (the ability of an object to radiate energy to two decimal places), value range: [0.01, 1.00]
     */
    public float fEmissivity;

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes = new byte[3];

    /**
     * Reflection Temperature Enable: 0- No, 1- Yes
     */
    public byte byReflectiveEnabled;

    /**
     * Reflection temperature, accurate to one decimal place
     */
    public float fReflectiveTemperature;

    /**
     * Rule name
     */
    public byte[] szRuleName = new byte[STRUCTURE_CONTEXT.NAME_LEN];

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes1 = new byte[63];

    /**
     * Rule calibration type: 0-point, 1-box, 2-line
     */
    public byte byRuleCalibType;

    /**
     * Point temperature measurement coordinates (in effect when the rule calibration type is "point")
     */
    public NET_VCA_POINT struPoint;

    /**
     * Area, line (applies when the rule identifies type as "box" or "line")
     */
    public NET_VCA_POLYGON struRegion;

}
