package com.github.ipcam.entity.hikvision;

import com.sun.jna.Structure;

/**
 * BYTE_ARRAY_STRUCTURE
 *
 * @author echils
 */
public class NET_DVR_REALTIME_THERMOMETRY_COND extends Structure {

    /**
     * Structure size
     */
    public int dwSize;

    /**
     * Channel number, starting at 1, 0xFFFFFF for all channels
     */
    public int dwChan = 2;

    /**
     * Rule ID, 0 means to get all rules, specific rule ID starts from 1
     */
    public byte byRuleID;

    /**
     * Long connection mode: 0- Reserved (compatible with older devices that do not support this feature),
     * 1- Timing mode, 2- Temperature difference mode
     * 1- Timing mode: the device uploads the highest temperature, lowest temperature and average temperature value
     * and temperature difference of each regular temperature measurement data every second.
     * 2- Temperature difference mode: if the temperature difference between the maximum or minimum temperature or
     * average temperature or temperature difference between the previous second and the next second is greater than
     * or equal to 2 degrees Celsius, then upload the maximum and minimum temperature and average temperature; If the
     * temperature difference is less than 2 degrees Celsius for more than or equal to one hour, the highest temperature,
     * lowest temperature, average temperature and temperature difference will be uploaded
     */
    public byte byMode = 0;

    /**
     * Keep it, set it to 0
     */
    public byte[] byRes = new byte[62];

}
