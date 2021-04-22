package com.github.ipcam.entity.reference;

/**
 * AudioInputEnum
 *
 * @author echils
 * @since 2020-03-19 13:36
 */
public enum AudioInputEnum {

    /**
     * Audio input type: 0-mic in, 1-line in
     */
    AUDIO_INPUT_TYPE,

    /**
     * Volume, value range: [0,100]
     */
    AUDIO_INPUT_VOLUME,

    /**
     * Whether to enable sound filtering: 0- off, 1- on
     */
    AUDIO_INPUT_NOISE_FILTER,

}
