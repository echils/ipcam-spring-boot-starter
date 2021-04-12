package com.github.ipcam;


/**
 * ICameraSupportDriver
 *
 * @author echils
 * @since 2020-03-27 16:42
 */
public interface ICameraSupportDriver {

    /**
     * Network camera support
     *
     * @return {@link CameraSupportedDriver}
     */
    CameraSupportedDriver support();

}
