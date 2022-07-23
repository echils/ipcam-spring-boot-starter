package com.github.ipcam.entity;

import lombok.Data;

/**
 * CameraInfo
 *
 * @author echils
 */
@Data
public class CameraInfo {

    /**
     * The ip of the camera
     */
    private String ip;

    /**
     * The name of the camera
     */
    private String name = "UNKNOWN NAME";

    /**
     * The modelNo of the camera
     */
    private String modelNo;

    /**
     * The serialNo of the camera
     */
    private String serialNo;

    /**
     * The username of the camera
     */
    private String username;

    /**
     * The password of the camera
     */
    private String password;

    /**
     * The type of the camera
     */
    private Type cameraType = Type.IPC;

    /**
     * Maximum number of channels supported
     */
    private int supportChannelNum = 1;

    /**
     * Maximum number of disks supported
     */
    private int supportDiskNum;

    /**
     * The camera type
     */
    public enum Type {
        DVR, NVR, IPC, DVS, OTHER
    }

    public CameraInfo() {
    }

    public CameraInfo(String name, String modelNo, String serialNo, int cameraType, int supportChannelNum, int supportDiskNum) {
        this.name = name;
        this.modelNo = modelNo;
        this.serialNo = serialNo;
        setCameraType(cameraType);
        setSupportChannelNum(supportChannelNum);
        this.supportDiskNum = supportDiskNum;
    }


    private void setCameraType(int cameraType) {
        if (cameraType >= 1 && cameraType <= 50) {
            this.cameraType = Type.DVR;
        }
        if (cameraType >= 51 && cameraType <= 100) {
            this.cameraType = Type.DVS;
            return;
        }
        if (cameraType >= 101 && cameraType <= 150) {
            this.cameraType = Type.NVR;
            return;
        }
        if (cameraType >= 151 && cameraType <= 200) {
            this.cameraType = Type.IPC;
            return;
        }
        this.cameraType = Type.OTHER;
    }

    private void setSupportChannelNum(int supportChannelNum) {
        if (Type.NVR == cameraType) {
            this.supportChannelNum = supportChannelNum;
        } else {
            this.supportChannelNum = 1;
        }
    }
}
