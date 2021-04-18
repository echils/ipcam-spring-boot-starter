package com.github.ipcam.entity.reference;

/**
 * WhiteBalanceEnum
 *
 * @author echils
 * @since 2020-03-19 13:36
 */
public enum WhiteBalanceEnum {

    MWB(0, "手动白平衡"),
    AWB1(1, "自动白平衡1"),
    AWB2(2, "自动白平衡2"),
    LWB(3, "锁定白平衡"),
    OUTDOOR(4, "室外"),
    INDOOR(5, "室内"),
    FLUORESCENT(6, "日光灯"),
    SODIUM(7, "钠灯"),
    AUTO_TRACK(8, "自动跟踪"),
    ONEPUSH(9, "一次白平衡"),
    AUTO_OUTDOOR(10, "室外自动"),
    AUTO_SODIUM(11, "钠灯自动"),
    MERCURY(12, "水银灯模式"),
    AUTOMATIC(13, "自动白平衡"),
    INCANDESCENT(14, "白炽灯"),
    WARM(15, "暖光灯"),
    NATURAL(16, "自然光");

    private byte key;
    private String describe;

    public byte getKey() {
        return key;
    }

    WhiteBalanceEnum(int key, String describe) {
        this.key = (byte) key;
        this.describe = describe;
    }
}
