package com.github.ipcam.entity.reference;

/**
 * PTZControlEnum
 *
 * @author echils
 * @since 2020-03-19 13:36
 */
public enum PTZControlEnum {

    /**
     * "Start"
     */
    PTZ_START(0),

    /**
     * "Stop"
     */
    PTZ_STOP(1),

    /**
     * Switch on the light
     */
    LIGHT_PWRON(2),

    /**
     * Switch on the wiper
     */
    WIPER_PWRON(3),

    /**
     * Switch on the fan
     */
    FAN_PWRON(4),

    /**
     * Switch on the heater
     */
    HEATER_PWRON(5),

    /**
     * Increase the zoom
     */
    ZOOM_IN(11),

    /**
     * Decrease the zoom
     */
    ZOOM_OUT(12),

    /**
     * Focus near
     */
    FOCUS_NEAR(13),

    /**
     * Focus far
     */
    FOCUS_FAR(14),

    /**
     * Enlarge the aperture
     */
    IRIS_OPEN(15),

    /**
     * Reduce the aperture
     */
    IRIS_CLOSE(16),

    /**
     * Up
     */
    TILT_UP(21),

    /**
     * Down
     */
    TILT_DOWN(22),

    /**
     * Left
     */
    PAN_LEFT(23),

    /**
     * Right
     */
    PAN_RIGHT(24),

    /**
     * Up and Left
     */
    UP_LEFT(25),

    /**
     * Up and Right
     */
    UP_RIGHT(26),

    /**
     * Down and Left
     */
    DOWN_LEFT(27),

    /**
     * Down and Right
     */
    DOWN_RIGHT(28);

    private int key;

    PTZControlEnum(int key) {
        this.key = key;
    }

    public Integer key() {
        return key;
    }

}
