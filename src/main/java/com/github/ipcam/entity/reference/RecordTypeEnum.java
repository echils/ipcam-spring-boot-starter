package com.github.ipcam.entity.reference;

/**
 * RecordTypeEnum
 *
 * @author echils
 * @since 2020-03-19 13:36
 */
public enum RecordTypeEnum {

    MANUAL(0, "Manual Video"),
    ALARM(1, "Alarm Video"),
    POSTBACK(2, "Manual Video"),
    SIGNAL(3, "signal Video"),
    MOVE(4, "move Video"),
    OCCLUSION(5, "Occlusion Video");

    private int key;
    private String implication;

    RecordTypeEnum(int key, String implication) {
        this.key = key;
        this.implication = implication;
    }

    public Integer key() {
        return key;
    }
}
