package com.github.ipcam.support;


/**
 * ICameraSupportDriver
 *
 * @author echils
 */
public interface ICameraSupportDriver {

    /**
     * Network camera support
     *
     * @return {@link CameraSupportedDriver}
     */
    CameraSupportedDriver support();

}
