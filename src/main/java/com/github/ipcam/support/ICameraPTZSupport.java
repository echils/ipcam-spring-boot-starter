package com.github.ipcam.support;

import com.github.ipcam.entity.PTZ;
import com.github.ipcam.entity.exception.CameraNotSupportException;
import com.github.ipcam.entity.infos.PresetPointInfo;
import com.github.ipcam.entity.reference.CruiseEnum;
import com.github.ipcam.entity.reference.PTZControlEnum;
import com.github.ipcam.entity.reference.PresetEnum;
import com.github.ipcam.entity.reference.TrackEnum;

import java.util.List;


/**
 * ICameraPTZSupport
 *
 * @author echils
 * @since 2020-03-27 16:38
 */
public interface ICameraPTZSupport {


    /**
     * Network camera control operation
     *
     * @param channel     camera ip channel
     * @param controlEnum control command
     * @param status      camera runtime status
     * @param speed       camera runtime speed
     */
    default void control(String channel, PTZControlEnum controlEnum, int status, int speed) {
        throw new CameraNotSupportException();
    }


    /**
     * Network camera control operation without previewHandle
     *
     * @param channel     camera ip channel
     * @param controlEnum control command
     * @param status      camera runtime status
     * @param speed       camera runtime speed
     */
    default void controlExpand(String channel, PTZControlEnum controlEnum, int status, int speed) {
        throw new CameraNotSupportException();
    }


    /**
     * Network camera preset operation
     *
     * @param channel    camera ip channel
     * @param presetEnum camera preset command
     * @param index      camera preset index
     */
    default void preset(String channel, PresetEnum presetEnum, int index) {
        throw new CameraNotSupportException();
    }


    /**
     * Network camera preset operation without previewHandle
     *
     * @param channel    camera ip channel
     * @param presetEnum camera preset command
     * @param index      camera preset index
     */
    default void presetExpand(String channel, PresetEnum presetEnum, int index) {
        throw new CameraNotSupportException();
    }


    /**
     * Network camera cruise operation
     *
     * @param channel     camera ip channel
     * @param cruiseEnum  {@link CruiseEnum}
     * @param cruiseRoute the index of currently configured cruise route
     * @param cruisePoint the point index of the currently configured cruise route
     * @param value       different cruise commands have different values: preset point (maximum 300), time (maximum 255), speed (maximum 40)
     */
    default void cruise(String channel, CruiseEnum cruiseEnum, int cruiseRoute, int cruisePoint, int value) {
        throw new CameraNotSupportException();
    }


    /**
     * Network camera cruise operation without previewHandle
     *
     * @param channel     camera ip channel
     * @param cruiseEnum  {@link CruiseEnum}
     * @param cruiseRoute the index of currently configured cruise route
     * @param cruisePoint the point index of the currently configured cruise route
     * @param value       different cruise commands have different values: preset point (maximum 300), time (maximum 255), speed (maximum 40)
     */
    default void cruiseExpand(String channel, CruiseEnum cruiseEnum, int cruiseRoute, int cruisePoint, int value) {
        throw new CameraNotSupportException();
    }


    /**
     * Network camera track operation
     *
     * @param channel   camera ip channel
     * @param trackEnum {@link TrackEnum}
     */
    default void track(String channel, TrackEnum trackEnum) {
        throw new CameraNotSupportException();
    }


    /**
     * Network camera track operation without previewHandle
     *
     * @param channel   camera ip channel
     * @param trackEnum {@link TrackEnum}
     */
    default void trackExpand(String channel, TrackEnum trackEnum) {
        throw new CameraNotSupportException();
    }


    /**
     * Config the name of the preset point (NVR not support)
     *
     * @param index the index of the preset point
     * @param name  the name of the preset point
     */
    default void setPresetName(int index, String name) {
        throw new CameraNotSupportException();
    }


    /**
     * Get the name of the preset point (NVR not support)
     *
     * @param index the index of the preset point
     * @return the name of the preset point
     */
    default String getPresetName(int index) {
        throw new CameraNotSupportException();
    }


    /**
     * Get current position of the network camera
     *
     * @param channel camera ip channel
     * @return {@link PTZ}
     */
    default PTZ getCurrentPosition(String channel) {
        throw new CameraNotSupportException();
    }


    /**
     * Goto the preset point of the network camera,PTZ will used to check
     *
     * @param channel     camera ip channel
     * @param presetIndex the presetIndex to be reached
     * @param ptz         the position to be reached
     * @throws InterruptedException
     */
    default void gotoPresetPoint(String channel, int presetIndex, PTZ ptz) throws InterruptedException {
        throw new CameraNotSupportException();
    }


    /**
     * Quick positioning camera by PTZ
     * When you call a method, you must pass the value  of the PTZ that you actually want to locate
     *
     * @param channel camera ip channel
     * @param pan     horizontal position
     * @param tilt    Vertical position
     * @param zoom    Zoom value
     */
    default void locate(String channel, int pan, int tilt, int zoom) throws InterruptedException {
        throw new CameraNotSupportException();
    }


    /**
     * Gets information about all preset points that have been used,
     * including the preset point name and the preset point id
     *
     * @param channel camera ip channel
     * @return {@link PresetPointInfo}
     */
    default List<PresetPointInfo> getActivatedPresetPoints(String channel) {
        throw new CameraNotSupportException();
    }


}
