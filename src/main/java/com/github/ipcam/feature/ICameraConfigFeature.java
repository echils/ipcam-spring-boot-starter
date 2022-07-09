package com.github.ipcam.feature;

import com.github.ipcam.entity.ScreenEffect;
import com.github.ipcam.entity.exception.CameraNotSupportException;
import com.github.ipcam.entity.reference.*;

import java.util.Calendar;

/**
 * ICameraConfigFeature
 *
 * @author echils
 */
public interface ICameraConfigFeature {


    /**
     * Change the password of the camera
     *
     * @param channel     the channel of camera
     * @param newPassword the new password of camera
     */
    default void changePassword(String channel, String newPassword) {
        throw new CameraNotSupportException();
    }


    /**
     * Reboot the camera
     */
    default void reboot() {
        throw new CameraNotSupportException();
    }


    /**
     * Restore the original configuration
     */
    default void restore() {
        throw new CameraNotSupportException();
    }


    /**
     * Config the zoom limit
     *
     * @param channel   the camera ip channel
     * @param zoomValue the support value of the camera
     */
    default void setZoomLimit(String channel, int zoomValue) {
        throw new CameraNotSupportException();
    }


    /**
     * Open the lens initialization
     *
     * @param channel the camera ip channel
     */
    default void openLensInitialization(String channel) {
        throw new CameraNotSupportException();
    }


    /**
     * Close the lens initialization
     *
     * @param channel the camera ip channel
     */
    default void closeLensInitialization(String channel) {
        throw new CameraNotSupportException();
    }


    /**
     * Config the max elevation angle
     *
     * @param channel        the camera ip channel
     * @param elevationValue the support value of the camera
     */
    default void setMaxElevationAngle(String channel, int elevationValue) {
        throw new CameraNotSupportException();
    }


    /**
     * Config screen param of the camera
     *
     * @param channel          the camera ip channel
     * @param screenEffectEnum {@link ScreenEffectEnum}
     * @param value            the support value of the camera
     */
    default void setScreen(String channel, ScreenEffectEnum screenEffectEnum, int value) {
        throw new CameraNotSupportException();
    }


    /**
     * Get screen param of the camera
     *
     * @param channel the camera ip channel
     * @return {@link ScreenEffect}
     */
    default ScreenEffect getScreen(String channel) {
        throw new CameraNotSupportException();
    }


    /**
     * Reset screen param of the camera
     *
     * @param channel the camera ip channel
     */
    default void resetScreen(String channel) {
        throw new CameraNotSupportException();
    }


    /**
     * Config the day and night conversion mode of the camera
     *
     * @param channel      the camera ip channel
     * @param dayNightEnum {@link DayNightEnum}
     */
    default void setDayNightConversionMode(String channel, DayNightEnum dayNightEnum) {
        throw new CameraNotSupportException();
    }


    /**
     * Config the day and night conversion mode of the camera with custom
     *
     * @param channel  the camera ip channel
     * @param dayStart start time of day
     * @param dayEnd   end time of day
     */
    default void setCustomDayNightConversionMode(String channel, Calendar dayStart, Calendar dayEnd) {
        throw new CameraNotSupportException();
    }


    /**
     * Config the back light compensation mode of the camera
     *
     * @param channel       the camera ip channel
     * @param backLightEnum {@link BackLightEnum}
     */
    default void setBackLightCompensationMode(String channel, BackLightEnum backLightEnum) {
        throw new CameraNotSupportException();
    }


    /**
     * Config the back light compensation mode of the camera with custom
     *
     * @param channel       the camera ip channel
     * @param backLightEnum {@link BackLightEnum}
     * @param value         the support value of the camera
     */
    default void setBackLightCompensationMode(String channel, BackLightEnum backLightEnum, int value) {
        throw new CameraNotSupportException();
    }


    /**
     * Config the back light compensation mode of the camera
     *
     * @param channel          the camera ip channel
     * @param whiteBalanceEnum {@link WhiteBalanceEnum}
     */
    default void setWhiteBalanceMode(String channel, WhiteBalanceEnum whiteBalanceEnum) {
        throw new CameraNotSupportException();
    }


    /**
     * Open the strong light inhibition mode of the camera
     *
     * @param channel the camera ip channel
     */
    default void openStrongLightInhibitionMode(String channel) {
        throw new CameraNotSupportException();
    }


    /**
     * Close the strong light inhibition mode of the camera
     *
     * @param channel the camera ip channel
     */
    default void closeStrongLightInhibitionMode(String channel) {
        throw new CameraNotSupportException();
    }


    /**
     * Open the prevent overexposure mode of the camera
     *
     * @param channel the camera ip channel
     */
    default void openPreventOverexposureMode(String channel) {
        throw new CameraNotSupportException();
    }


    /**
     * Close the prevent overexposure mode of the camera
     *
     * @param channel the camera ip channel
     */
    default void closePreventOverexposureMode(String channel) {
        throw new CameraNotSupportException();
    }


    /**
     * At the same time, turn off the overexposure mode and strong light suppression mode of the camera
     *
     * @param channel the camera ip channel
     */
    default void closeLightInhibitionAndPreventOverexposureMode(String channel) {
        throw new CameraNotSupportException();
    }


    /**
     * Config wide dynamic mode of the camera
     *
     * @param channel the camera ip channel
     * @param wdrEnum {@link WDREnum}
     */
    default void setWideDynamicMode(String channel, WDREnum wdrEnum) {
        throw new CameraNotSupportException();
    }


    /**
     * Config wide dynamic mode of the camera
     *
     * @param channel the camera ip channel
     * @param wdrEnum {@link WDREnum}
     * @param value   the support value of the camera
     */
    default void setWideDynamicMode(String channel, WDREnum wdrEnum, int value) {
        throw new CameraNotSupportException();
    }


    /**
     * Config noise reduction mode of the camera
     *
     * @param channel            the camera ip channel
     * @param noiseReductionEnum {@link NoiseReductionEnum}
     */
    default void setNoiseReductionMode(String channel, NoiseReductionEnum noiseReductionEnum) {
        throw new CameraNotSupportException();
    }


    /**
     * Config noise reduction value of the camera
     *
     * @param channel                 the camera ip channel
     * @param noiseReductionLevelEnum {@link NoiseReductionLevelEnum}
     * @param value                   the support value of the camera
     */
    default void setNoiseReductionValue(String channel, NoiseReductionLevelEnum noiseReductionLevelEnum, int value) {
        throw new CameraNotSupportException();
    }


    /**
     * Open defog mode of the camera
     *
     * @param channel the camera ip channel
     */
    default void openDefogMode(String channel) {
        throw new CameraNotSupportException();
    }


    /**
     * Close defog mode of the camera
     *
     * @param channel the camera ip channel
     */
    default void closeDefogMode(String channel) {
        throw new CameraNotSupportException();
    }


    /**
     * Open electronic stabilization mode of the camera
     *
     * @param channel the camera ip channel
     */
    default void openElectronicStabilizationMode(String channel) {
        throw new CameraNotSupportException();
    }


    /**
     * Close electronic stabilization mode of the camera
     *
     * @param channel the camera ip channel
     */
    default void closeElectronicStabilizationMode(String channel) {
        throw new CameraNotSupportException();
    }


    /**
     * Config exposure mode of the camera
     *
     * @param channel      the camera ip channel
     * @param exposureMode {@link ExposureMode}
     */
    default void setExposureMode(String channel, ExposureMode exposureMode) {
        throw new CameraNotSupportException();
    }


    /**
     * Config exposure param of the camera
     *
     * @param channel       the camera ip channel
     * @param exposureParam {@link ExposureParam}
     * @param value         the support value of the camera
     */
    default void setExposureParam(String channel, ExposureParam exposureParam, int value) {
        throw new CameraNotSupportException();
    }


    /**
     * Config focus mode of the camera
     *
     * @param channel   the camera ip channel
     * @param focusMode {@link FocusMode}
     */
    default void setFocusMode(String channel, FocusMode focusMode) {
        throw new CameraNotSupportException();
    }


    /**
     * Config focus distance of the camera
     *
     * @param channel the camera ip channel
     * @param value   the support value of the camera
     */
    default void setFocusDistance(String channel, int value) {
        throw new CameraNotSupportException();
    }


    /**
     * Reset lens of the camera
     *
     * @param channel the camera ip channel
     */
    default void resetLens(String channel) {
        throw new CameraNotSupportException();
    }


    /**
     * Config low illumination electronic shutter mode of the camera
     *
     * @param channel             the camera ip channel
     * @param lowLightShutterEnum {@link LowLightShutterEnum}
     */
    default void setLowIlluminationElectronicShutterMode(String channel, LowLightShutterEnum lowLightShutterEnum) {
        throw new CameraNotSupportException();
    }


    /**
     * Config video and audio mode of the camera
     *
     * @param channel         the camera ip channel
     * @param streamTypeEnum  {@link StreamTypeEnum}
     * @param compressionEnum {@link CompressionEnum}
     * @param value           the support value of the camera
     */
    default void setVideoAndAudioMode(String channel, StreamTypeEnum streamTypeEnum, CompressionEnum compressionEnum, int value) {
        throw new CameraNotSupportException();
    }


    /**
     * Config audio input mode if the camera
     *
     * @param channel        the camera ip channel
     * @param audioInputEnum {@link AudioInputEnum}
     * @param value          the support value of the camera
     */
    default void setAudioInputConfig(String channel, AudioInputEnum audioInputEnum, int value) {
        throw new CameraNotSupportException();
    }


    /**
     * Config osd mode of the camera ptz
     *
     * @param channel     the camera ip channel
     * @param ptzOSDParam {@link PtzOSDParam}
     * @param value       the support value of the camera
     */
    default void setPTZOSDConfigMode(String channel, PtzOSDParam ptzOSDParam, int value) {
        throw new CameraNotSupportException();
    }


}