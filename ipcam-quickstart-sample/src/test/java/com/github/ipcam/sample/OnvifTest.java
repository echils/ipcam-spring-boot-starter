package com.github.ipcam.sample;

import com.github.ipcam.entity.CameraDriver;
import com.github.ipcam.entity.NetworkCamera;
import com.github.ipcam.entity.onvif.OnvifExecutor;
import com.github.ipcam.entity.onvif.command.*;
import com.github.ipcam.entity.onvif.modes.OnvifMediaProfile;
import com.github.ipcam.entity.reference.PTZControlEnum;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * OnvifTest
 *
 * @author echils
 */
@SpringBootTest
public class OnvifTest {

    @Test
    public void test() throws InterruptedException {
        NetworkCamera networkCamera = new NetworkCamera("192.168.20.151", 80,
                "admin", "abcd1234", CameraDriver.ONVIF);
//        NetworkCamera networkCamera = new NetworkCamera("192.168.20.70", 80,
//                "admin", "JunAseit2018!", CameraDriver.ONVIF);
        OnvifExecutor onvifExecutor = new OnvifExecutor(networkCamera);
//        System.out.println(onvifExecutor.execute(new DeviceInfoCommand()));
//        System.out.println(onvifExecutor.execute(new GetServicesCommand()));
//        System.out.println(onvifExecutor.execute(new GetVideoSourceCommand()));
        List<OnvifMediaProfile> onvifMediaProfiles =
                onvifExecutor.execute(new GetMediaProfilesCommand()).getData();
        if (!CollectionUtils.isEmpty(onvifMediaProfiles)) {
            OnvifMediaProfile mediaProfile = onvifMediaProfiles.get(0);
//            System.out.println(onvifExecutor.execute(new GetPresetsCommand(mediaProfile)));
//            System.out.println(onvifExecutor.execute(new GetCurrentPositionCommand(mediaProfile)));
//            System.out.println(onvifExecutor.execute(new SetPresetCommand(mediaProfile)));
//            System.out.println(onvifExecutor.execute(new UpdatePresetCommand(mediaProfile, 52)));
//            System.out.println(onvifExecutor.execute(new RemovePresetCommand(mediaProfile, 52)));
//            System.out.println(onvifExecutor.execute(new GotoPresetCommand(mediaProfile, 50)));
            System.out.println(onvifExecutor.execute(new ControlMoveCommand(mediaProfile, PTZControlEnum.UP_RIGHT, 3)));
            Thread.sleep(3000);
            System.out.println(onvifExecutor.execute(new ControlStopCommand(mediaProfile)));
        }
    }

}
