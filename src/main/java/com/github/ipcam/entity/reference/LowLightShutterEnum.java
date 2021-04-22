package com.github.ipcam.entity.reference;

/**
 * LowLightShutterEnum
 *
 * @author echils
 * @since 2020-03-19 13:36
 */
public enum LowLightShutterEnum {

    /**
     * Close
     */
    OFF(0),

    /**
     * SLOW SHUTTER x 2
     */
    SLOWSHUTTER2(1),

    /**
     * SLOW SHUTTER x 3
     */
    SLOWSHUTTER3(2),

    /**
     * SLOW SHUTTER x 4
     */
    SLOWSHUTTER4(3),

    /**
     * SLOW SHUTTER x 6
     */
    SLOWSHUTTER6(4),

    /**
     * SLOW SHUTTER x 8
     */
    SLOWSHUTTER8(5),

    /**
     * SLOW SHUTTER x 12
     */
    SLOWSHUTTER12(6),

    /**
     * SLOW SHUTTER x 16
     */
    SLOWSHUTTER16(7),

    /**
     * SLOW SHUTTER x 24
     */
    SLOWSHUTTER24(8),

    /**
     * SLOW SHUTTER x 32
     */
    SLOWSHUTTER32(9),

    /**
     * SLOW SHUTTER x 48
     */
    SLOWSHUTTER48(10),

    /**
     * SLOW SHUTTER x 64
     */
    SLOWSHUTTER64(11),

    /**
     * SLOW SHUTTER x 96
     */
    SLOWSHUTTER96(12),

    /**
     * SLOW SHUTTER x 128
     */
    SLOWSHUTTER128(13),

    /**
     * SLOW SHUTTER x 256
     */
    SLOWSHUTTER256(14),

    /**
     * SLOW SHUTTER x 512
     */
    SLOWSHUTTER512(15);


    private byte level;

    LowLightShutterEnum(int level) {
        this.level = (byte) level;
    }

    public byte getLevel() {
        return level;
    }

}
