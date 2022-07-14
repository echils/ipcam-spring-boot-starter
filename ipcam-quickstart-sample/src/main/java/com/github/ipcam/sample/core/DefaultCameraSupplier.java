package com.github.ipcam.sample.core;

import com.github.ipcam.ICameraSupplier;
import com.github.ipcam.entity.CameraDriver;
import com.github.ipcam.entity.NetworkCamera;
import org.springframework.stereotype.Component;

/**
 * DefaultCameraSupplier
 *
 * @author echils
 */
@Component
public class DefaultCameraSupplier implements ICameraSupplier {

    @Override
    public NetworkCamera apply(String identification) {

        //return a fixed camera for sample,in business usage scenarios you can dynamically fetch from the database
        return new NetworkCamera("192.168.20.151", 8000,
                "admin", "abcd1234", CameraDriver.HIKVISION);
    }

}
