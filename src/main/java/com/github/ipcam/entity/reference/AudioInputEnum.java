package com.github.ipcam.entity.reference;

/**
 * AudioInputEnum
 *
 * @author echils
 * @since 2020-03-19 13:36
 */
public enum AudioInputEnum {

    /**
     * 音频输入类型：0- mic in，1- line in
     */
    AUDIO_INPUT_TYPE,

    /**
     * 音量，取值范围：[0,100]
     */
    AUDIO_INPUT_VOLUME,

    /**
     * 是否开启声音过滤：0- 关，1- 开
     */
    AUDIO_INPUT_NOISE_FILTER,

}
