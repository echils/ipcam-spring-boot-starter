package com.github.ipcam;


import com.github.ipcam.entity.PTZ;
import com.github.ipcam.entity.PresetPointInfo;
import com.github.ipcam.entity.reference.PTZControlEnum;
import com.github.ipcam.entity.reference.PresetEnum;

import java.util.List;

/**
 * ICameraPTZSupport
 *
 * @author echils
 * @since 2020-03-27 16:38
 */
public interface ICameraPTZSupport {


    /**
     * network camera control operation
     *
     * @param channel     camera ip channel
     * @param controlEnum control command
     * @param status      camera runtime status
     * @param speed       camera runtime speed
     */
    void control(String channel, PTZControlEnum controlEnum, int status, int speed);


    /**
     * network camera control operation without realHandle
     *
     * @param channel     camera ip channel
     * @param controlEnum control command
     * @param status      camera runtime status
     * @param speed       camera runtime speed
     */
    void controlExpand(String channel, PTZControlEnum controlEnum, int status, int speed);


    /**
     * network camera preset operation
     *
     * @param channel    camera ip channel
     * @param presetEnum camera preset command
     * @param index      camera preset index
     */
    void preset(String channel, PresetEnum presetEnum, int index);


    /**
     * network camera preset operation without realHandle
     *
     * @param channel    camera ip channel
     * @param presetEnum camera preset command
     * @param index      camera preset index
     */
    void presetExpand(String channel, PresetEnum presetEnum, int index);


    /**
     * get current position of the network camera
     *
     * @param channel camera ip channel
     * @return {@link PTZ}
     */
    PTZ getCurrentPosition(String channel);


    /**
     * goto the preset point of the network camera,PTZ will used to check
     *
     * @param channel     camera ip channel
     * @param presetIndex the presetIndex to be reached
     * @param ptz         the position to be reached
     * @throws InterruptedException
     */
    void gotoPresetPoint(String channel, int presetIndex, PTZ ptz) throws InterruptedException;


    /**
     * Quick positioning camera by PTZ
     * When you call a method, you must pass the value  of the PTZ that you actually want to locate
     *
     * @param channel camera ip channel
     * @param pan     horizontal position
     * @param tilt    Vertical position
     * @param zoom    Zoom value
     */
    void locate(String channel, int pan, int tilt, int zoom) throws InterruptedException;


    /**
     * Gets information about all preset points that have been used,
     * including the preset point name and the preset point id
     *
     * @param channel camera ip channel
     * @return {@link PresetPointInfo}
     */
    List<PresetPointInfo> getActivatedPresetPoints(String channel);

}
