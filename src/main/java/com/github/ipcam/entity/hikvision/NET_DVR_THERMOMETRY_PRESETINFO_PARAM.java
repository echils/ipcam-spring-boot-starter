package com.github.ipcam.entity.hikvision;

import com.github.ipcam.entity.comm.StructureContext;
import com.sun.jna.Structure;

/**
 * NET_DVR_THERMOMETRY_PRESETINFO_PARAM
 *
 * @author echils
 * @since 2020-03-19 13:42
 */
public class NET_DVR_THERMOMETRY_PRESETINFO_PARAM extends Structure {

    public byte byEnabled;

    public byte byRuleID;

    public short wDistance;

    public float fEmissivity;

    public byte[] byRes = new byte[3];

    public byte byReflectiveEnabled;

    public float fReflectiveTemperature;

    public byte[] szRuleName = new byte[StructureContext.NAME_LEN];

    public byte[] byRes1 = new byte[63];

    public byte byRuleCalibType;

    public NET_VCA_POINT struPoint;

    public NET_VCA_POLYGON struRegion;

}
