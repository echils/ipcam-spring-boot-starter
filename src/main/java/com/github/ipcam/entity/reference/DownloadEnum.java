package com.github.ipcam.entity.reference;

/**
 * DownloadEnum
 *
 * @author echils
 */
public enum DownloadEnum {

    /**
     * Start to download
     */
    NET_DVR_PLAYSTART(1),

    /**
     * Pause to download
     */
    NET_DVR_PLAYPAUSE(3),

    /**
     * Resume download
     */
    NET_DVR_PLAYRESTART(4);

    private int key;

    DownloadEnum(int key) {
        this.key = key;
    }

    public Integer key() {
        return key;
    }

}
