package com.github.ipcam.entity.hikvision;

import com.github.ipcam.entity.comm.StructureContext;
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

    public int dwRelativeTime;

    public int dwAbsTime;

    public byte[] szRuleName = new byte[StructureContext.NAME_LEN];

    public byte byRuleID;

    public byte byRuleCalibType;

    public short wPresetNo;

    public NET_DVR_POINT_THERM_CFG struPointThermCfg = new NET_DVR_POINT_THERM_CFG();

    public NET_DVR_LINEPOLYGON_THERM_CFG struLinePolygonThermCfg = new NET_DVR_LINEPOLYGON_THERM_CFG();

    public byte byThermometryUnit;

    public byte byDataType;

    public byte byRes1;

    public byte bySpecialPointThermType;

    public byte fCenterPointTemperature;

    public byte fHighestPointTemperature;

    public byte fLowestPointTemperature;

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes = new byte[112];

}
