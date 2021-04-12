package com.github.ipcam;


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
     * take photo from the camera,The path must contain the file name
     *
     * @param channel    The channel of network camera
     * @param targetPath The full path where the file is saved
     */
    void imageOutput(String channel, String targetPath);


    /**
     * video collect from the camera or nvr,The path must contain the file name
     *
     * @param channel    The channel of network camera
     * @param startTime  Start time of collect
     * @param endTime    End time of collect
     * @param targetPath The full path where the file is saved
     * @throws InterruptedException see {@link InterruptedException}
     */
    void videoOutput(String channel, Date startTime, Date endTime, String targetPath) throws InterruptedException;


    /**
     * audio collect from the camera,The path must contain the file name
     *
     * @param targetPath The full path where the file is saved
     * @param duration   Collect duration
     * @throws InterruptedException see {@link InterruptedException}
     */
    void audioOutput(String targetPath, int duration) throws InterruptedException;


    /**
     * Output the video stream byte data to the specified file, and the interface will
     * return an operation handle that can be used to interrupt the output operation at any time
     *
     * @param channel    The channel of network camera
     * @param streamType {@link StreamTypeEnum}
     * @param path       picture save path contains filename
     * @return an operation handle that can be used to interrupt the output operation at any time
     */
    String videoStreamOutput(String channel, StreamTypeEnum streamType, String path);


    /**
     * Output the video stream byte data to the specified stream, and the interface will
     * return an operation handle that can be used to interrupt the output operation at any time
     *
     * @param channel      The channel of network camera
     * @param streamType   {@link StreamTypeEnum}
     * @param outputStream The output stream
     * @return an operation handle that can be used to interrupt the output operation at any time
     */
    String videoStreamOutput(String channel, StreamTypeEnum streamType, OutputStream outputStream);


    /**
     * Cut off video stream output operation
     *
     * @param dataOutputHandle the return value of {@link #videoStreamOutput}
     */
    void stopVideoStreamOutput(String dataOutputHandle);

}
