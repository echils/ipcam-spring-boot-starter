package com.github.ipcam.entity.hikvision;

import com.github.ipcam.entity.comm.STRUCTURE_CONTEXT;
import com.sun.jna.Structure;


/**
 * NET_DVR_THERMOMETRY_UPLOAD
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_THERMOMETRY_UPLOAD extends Structure {

    /**
     * Structure size
     */
    public int dwSize;

    /**
     * The relative time scale
     */
    public int dwRelativeTime;

    /**
     * The absolute time scale
     */
    public int dwAbsTime;

    /**
     * Rule name
     */
    public byte[] szRuleName = new byte[STRUCTURE_CONTEXT.NAME_LEN];

    /**
     * Rules of the ID number
     */
    public byte byRuleID;

    /**
     * Rule calibration type: 0-point, 1-box, 2-line
     */
    public byte byRuleCalibType;

    /**
     * Preset point number
     */
    public short wPresetNo;

    /**
     * Point temperature measurement information is valid when ByrulecalibType is 0
     */
    public NET_DVR_POINT_THERM_CFG struPointThermCfg = new NET_DVR_POINT_THERM_CFG();

    /**
     * Frame/line temperature information, valid when ByrulecalibType is 1 or 2
     */
    public NET_DVR_LINEPOLYGON_THERM_CFG struLinePolygonThermCfg = new NET_DVR_LINEPOLYGON_THERM_CFG();

    /**
     * Temperature measurement units: 0-C (℃), 1-F (℉), 2-Kelvin (K)
     */
    public byte byThermometryUnit;

    /**
     * Data state type: 0- in detection, 1- start, 2- end
     */
    public byte byDataType;

    /**
     * Keep it, set it to 0
     */
    public byte byRes1;

    /**
     * Whether special point temperature measurement is supported, special points of different types are represented by bits:
     * BIT0 - Temperature measurement at central point: 0- Not supported, 1- Supported;
     * BIT1 - Maximum temperature measurement: 0- Not supported, 1- Supported;
     * BIT2 - Lowest point temperature measurement: 0- Unsupported, 1- Supported
     */
    public byte bySpecialPointThermType;

    /**
     * Center point temperature (determined by bySpecialPointThermType value to support center point),
     * accurate to one decimal point
     */
    public byte fCenterPointTemperature;

    /**
     * The highest point temperature (as determined by bySpecialPointThermType),
     * accurate to one decimal place
     */
    public byte fHighestPointTemperature;

    /**
     * The lowest point temperature (as determined by bySpecialPointThermType),
     * accurate to one decimal place
     */
    public byte fLowestPointTemperature;

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes = new byte[112];

}
