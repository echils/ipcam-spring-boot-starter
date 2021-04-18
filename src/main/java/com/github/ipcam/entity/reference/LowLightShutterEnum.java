package com.github.ipcam.entity.reference;

/**
 * LowLightShutterEnum
 *
 * @author echils
 * @since 2020-03-19 13:36
 */
public enum LowLightShutterEnum {
    OFF(0, "关"),
    SLOWSHUTTER2(1, "慢快门*2"),
    SLOWSHUTTER3(2, "慢快门*3"),
    SLOWSHUTTER4(3, "慢快门*4"),
    SLOWSHUTTER6(4, "慢快门*6"),
    SLOWSHUTTER8(5, "慢快门*8"),
    SLOWSHUTTER12(6, "慢快门*12"),
    SLOWSHUTTER16(7, "慢快门*16"),
    SLOWSHUTTER24(8, "慢快门*24"),
    SLOWSHUTTER32(9, "慢快门*32"),
    SLOWSHUTTER48(10, "慢快门*48"),
    SLOWSHUTTER64(11, "慢快门*64"),
    SLOWSHUTTER96(12, "慢快门*96"),
    SLOWSHUTTER128(13, "慢快门*128"),
    SLOWSHUTTER256(14, "慢快门*256"),
    SLOWSHUTTER512(15, "慢快门*512");


    private byte level;
    private String desc;

    LowLightShutterEnum(int level, String desc) {
        this.level = (byte) level;
        this.desc = desc;
    }

    public byte getLevel() {
        return level;
    }
}
