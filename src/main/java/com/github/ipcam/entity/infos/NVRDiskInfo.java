package com.github.ipcam.entity.infos;

import lombok.Data;

/**
 * The disk info of NVR
 *
 * @author echils
 */
@Data
public class NVRDiskInfo {

    /**
     * The id of the disk
     */
    private int id;

    /**
     * The type of the disk
     */
    private int type;

    /**
     * The total capacity of the disk,unit: MB
     */
    private int capacity;

    /**
     * The free capacity of the disk,unit: MB
     */
    private int freeSpace;

}
