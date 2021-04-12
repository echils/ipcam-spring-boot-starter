package com.github.ipcam.entity.reference;

/**
 * DownloadEnum
 *
 * @author echils
 * @since 2020-03-19 13:36
 */
public enum DownloadEnum {

    NET_DVR_PLAYSTART(1, "Start to download"),
    NET_DVR_PLAYPAUSE(3, "Pause to download"),
    NET_DVR_PLAYRESTART(4, "Resume download");

    private int key;
    private String implication;

    DownloadEnum(int key, String implication) {
        this.key = key;
        this.implication = implication;
    }

    public Integer key() {
        return key;
    }
}
