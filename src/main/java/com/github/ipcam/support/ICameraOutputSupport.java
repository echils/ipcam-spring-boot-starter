package com.github.ipcam.support;


import com.github.ipcam.entity.exception.CameraNotSupportException;
import com.github.ipcam.entity.reference.StreamTypeEnum;

import java.io.OutputStream;
import java.util.Date;

/**
 * ICameraOutputSupport
 *
 * @author echils
 * @since 2020-03-27 16:36
 */
public interface ICameraOutputSupport {


    /**
     * Take photo from the camera,The path must contain the file name
     *
     * @param channel    The channel of network camera
     * @param targetPath The full path where the file is saved
     */
    default void imageOutput(String channel, String targetPath) {
        throw new CameraNotSupportException();
    }


    /**
     * Video collect from camera
     *
     * @param channel    The channel of network camera
     * @param streamType {@link StreamTypeEnum}
     * @param targetPath The full path where the file is saved
     */
    default void videoOutput(String channel, StreamTypeEnum streamType, String targetPath) {
        throw new CameraNotSupportException();
    }


    /**
     * Stop video collect operation
     *
     * @param channel    The channel of network camera
     * @param streamType {@link StreamTypeEnum}
     */
    default void stopVideoOutput(String channel, StreamTypeEnum streamType) {
        throw new CameraNotSupportException();
    }


    /**
     * Video download from the camera or nvr,The path must contain the file name
     *
     * @param channel    The channel of network camera
     * @param startTime  Start time of collect
     * @param endTime    End time of collect
     * @param targetPath The full path where the file is saved
     * @throws InterruptedException see {@link InterruptedException}
     */
    default void videoDownload(String channel, Date startTime, Date endTime, String targetPath) throws InterruptedException {
        throw new CameraNotSupportException();
    }


    /**
     * Audio collect from the camera,The path must contain the file name
     *
     * @param targetPath The full path where the file is saved
     * @param duration   Collect duration
     * @throws InterruptedException see {@link InterruptedException}
     */
    default void audioOutput(String targetPath, int duration) throws InterruptedException {
        throw new CameraNotSupportException();
    }


    /**
     * Output the video stream byte data to the specified file, and the interface will
     * return an operation handle that can be used to interrupt the output operation at any time
     *
     * @param channel    The channel of network camera
     * @param streamType {@link StreamTypeEnum}
     * @param path       picture save path contains filename
     * @return an operation handle that can be used to interrupt the output operation at any time
     */
    default String videoStreamOutput(String channel, StreamTypeEnum streamType, String path) {
        throw new CameraNotSupportException();
    }


    /**
     * Output the video stream byte data to the specified stream, and the interface will
     * return an operation handle that can be used to interrupt the output operation at any time
     *
     * @param channel      The channel of network camera
     * @param streamType   {@link StreamTypeEnum}
     * @param outputStream The output stream
     * @return an operation handle that can be used to interrupt the output operation at any time
     */
    default String videoStreamOutput(String channel, StreamTypeEnum streamType, OutputStream outputStream) {
        throw new CameraNotSupportException();
    }


    /**
     * Cut off video stream byte output operation
     *
     * @param dataOutputHandle the return value of {@link #videoStreamOutput}
     */
    default void stopVideoStreamOutput(String dataOutputHandle) {
        throw new CameraNotSupportException();
    }


}
