package com.github.ipcam.hikvision;

import com.sun.jna.Structure;

/**
 * NET_DVR_LOCAL_GENERAL_CFG
 *
 * @author echils
 */
public class NET_DVR_LOCAL_GENERAL_CFG extends Structure {

    /**
     * Exception callback type: 0- Exception callback through thread pool, 1- Exception callback directly to upper level
     */
    public byte byExceptionCbDirectly;

    /**
     * Play back and preview when saving to a local video file without slicing: 0- slicing (default), 1- unslicing
     */
    public byte byNotSplitRecordFile;

    /**
     * Keep
     */
    public byte[] bytes = new byte[6];

    /**
     * When slicing is enabled (ByNotSplitRecordFile = 0), preview and playback of the saved video file will be
     * automatically sliced. that is, the new file will be saved
     */
    public int i64FileSize;

    /**
     * Keep
     */
    public byte[] byRes1 = new byte[240];

}
