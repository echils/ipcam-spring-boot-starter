package com.github.ipcam.entity.reference;

/**
 * 云台控制枚举
 *
 * @author echils
 * @since 2020-03-19 13:36
 */
public enum PTZControlEnum {

    PTZ_START(0, "Start"),
    PTZ_STOP(1, "Stop"),
    LIGHT_PWRON(2, "Switch on the light"),
    WIPER_PWRON(3, "Switch on the wiper"),
    FAN_PWRON(4, "Switch on the fan"),
    HEATER_PWRON(5, "Switch on the heater"),
    ZOOM_IN(11, "Increase the zoom"),
    ZOOM_OUT(12, "Decrease the zoom"),
    FOCUS_NEAR(13, "Focus near"),
    FOCUS_FAR(14, "Focus far"),
    IRIS_OPEN(15, "Enlarge the aperture"),
    IRIS_CLOSE(16, "Reduce the aperture"),
    TILT_UP(21, "Up"),
    TILT_DOWN(22, "Down"),
    PAN_LEFT(23, "Left"),
    PAN_RIGHT(24, "Right"),
    UP_LEFT(25, "Up and Left"),
    UP_RIGHT(26, "Up and Right"),
    DOWN_LEFT(27, "Down and Left"),
    DOWN_RIGHT(28, "Down and Right");

    private int key;
    private String implication;

    PTZControlEnum(int key, String implication) {
        this.key = key;
        this.implication = implication;
    }

    public Integer key() {
        return key;
    }
}
