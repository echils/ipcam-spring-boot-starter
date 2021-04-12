package com.github.ipcam.entity;

import lombok.Data;

/**
 * The channel info of the NVR
 *
 * @author echils
 * @since 2020-07-13 10:26
 */
@Data
public class NVRChannelInfo {

    /**
     * The code of the channel
     */
    private String channelNo;

    /**
     * The name of the channel
     */
    private String channelName;

    /**
     * The ip of the channel
     */
    private String ipv4;

    /**
     * The username of the channel
     */
    private String username;

    /**
     * The device channel code of the channel
     */
    private String deviceChannelNo;

    /**
     * The manager port of the channel
     */
    private int managerPort;

    /**
     * The stream type of the channel
     */
    private String streamType;

    /**
     * The proxy protocol of the channel
     */
    private String proxyProtocol;

    /**
     * The address format type of the channel
     */
    private String addressFormatType;

}
