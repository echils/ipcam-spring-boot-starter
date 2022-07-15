package com.github.ipcam.entity;

import lombok.Data;

/**
 * The channel info of the NVR
 *
 * @author echils
 */
@Data
public class NVRChannelInfo {

    /**
     * The channel code of the nvr
     */
    private String channelNo;

    /**
     * The channel name of the nvr
     */
    private String channelName;

    /**
     * The ip of the dvr
     */
    private String ipv4;

    /**
     * The username of the dvr
     */
    private String username;

    /**
     * The channel code of the dvr
     */
    private String deviceChannelNo;

    /**
     * The manager port of the dvr
     */
    private int managerPort;

    /**
     * The proxy protocol of the dvr
     */
    private String proxyProtocol;

}
