package com.github.ipcam.entity.reference;

/**
 * ExposureMode
 *
 * @author echils
 * @since 2020-03-19 13:36
 */
public enum ExposureMode {

    MANUAL(0, "手动曝光"),
    AUTOMATIC(1, "自动曝光"),
    APERTURE_PRIORITY(2, "光圈优先"),
    SHUTTER_PRIORITY(3, "快门优先"),
    GAIN_PRIORITY(3, "增益优先 ");


    private byte key;
    private String desc;

    public byte getKey() {
        return key;
    }

    ExposureMode(int key, String desc) {
        this.key = (byte) key;
        this.desc = desc;
    }
}
