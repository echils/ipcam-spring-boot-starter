package com.github.ipcam.sample;

import com.github.ipcam.entity.CameraDriver;
import com.github.ipcam.entity.NetworkCamera;
import com.github.ipcam.entity.PresetPointInfo;
import com.github.ipcam.entity.reference.PTZControlEnum;
import com.github.ipcam.onvif.OnvifExecutor;
import com.github.ipcam.onvif.OnvifResultData;
import com.github.ipcam.onvif.command.*;
import com.github.ipcam.onvif.models.OnvifMediaProfile;
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
    public void test() throws Exception {
        NetworkCamera networkCamera = new NetworkCamera("192.168.20.151", 80,
                "admin", "abcd1234", CameraDriver.ONVIF);
        OnvifExecutor onvifExecutor = new OnvifExecutor(networkCamera);
        System.out.println(onvifExecutor.execute(new DeviceInfoCommand()));
        System.out.println(onvifExecutor.execute(new GetServicesCommand()));
        List<OnvifMediaProfile> onvifMediaProfiles =
                onvifExecutor.execute(new GetMediaProfilesCommand()).getData();
        System.out.println(onvifMediaProfiles);
        if (!CollectionUtils.isEmpty(onvifMediaProfiles)) {
            OnvifMediaProfile mediaProfile = onvifMediaProfiles.get(0);
            System.out.println(onvifExecutor.execute(new GetPresetsCommand(mediaProfile)));
            System.out.println(onvifExecutor.execute(new GetCurrentPositionCommand(mediaProfile)));
            OnvifResultData<PresetPointInfo> pointResultData = onvifExecutor.execute(new SetPresetCommand(mediaProfile));
            System.out.println(pointResultData);
            int presetIndex = pointResultData.getData().getId();
            System.out.println(onvifExecutor.execute(new UpdatePresetCommand(mediaProfile, presetIndex)));
            System.out.println(onvifExecutor.execute(new GotoPresetCommand(mediaProfile, presetIndex)));
            System.out.println(onvifExecutor.execute(new RemovePresetCommand(mediaProfile, presetIndex)));
            System.out.println(onvifExecutor.execute(new ControlMoveCommand(mediaProfile, PTZControlEnum.UP_RIGHT, 3)));
            Thread.sleep(3000);
            System.out.println(onvifExecutor.execute(new ControlStopCommand(mediaProfile)));
        }
    }

}
