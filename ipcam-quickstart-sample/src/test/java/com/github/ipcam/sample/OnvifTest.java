package com.github.ipcam.sample;

import com.github.ipcam.entity.CameraDriver;
import com.github.ipcam.entity.NetworkCamera;
import com.github.ipcam.entity.onvif.OnvifExecutor;
import com.github.ipcam.entity.onvif.command.DeviceInfoCommand;
import com.github.ipcam.entity.onvif.command.GetCurrentPositionCommand;
import com.github.ipcam.entity.onvif.command.GetPresetsCommand;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * OnvifTest
 *
 * @author echils
 */
@SpringBootTest
public class OnvifTest {

    @Test
    public void test() {
        NetworkCamera networkCamera = new NetworkCamera("192.168.20.151", 80,
                "admin", "abcd1234", CameraDriver.HIKVISION);
//        NetworkCamera networkCamera = new NetworkCamera("192.168.20.175", 80,
//                "admin", "JunAseit2018!", CameraDriver.HIKVISION);
//        NetworkCamera networkCamera = new NetworkCamera("192.168.6.9", 80,
//                "admin", "abcd1234", CameraDriver.HIKVISION);
        OnvifExecutor onvifExecutor = new OnvifExecutor(networkCamera);
        System.out.println(onvifExecutor.execute(new DeviceInfoCommand()));
        System.out.println(onvifExecutor.execute(new GetPresetsCommand()));
        System.out.println(onvifExecutor.execute(new GetCurrentPositionCommand()));
//        System.out.println(onvifExecutor.execute(new SetPresetCommand()));
    }

}
