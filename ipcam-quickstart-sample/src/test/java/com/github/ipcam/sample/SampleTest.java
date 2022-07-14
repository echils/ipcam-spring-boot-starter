package com.github.ipcam.sample;

import com.github.ipcam.sample.service.DefaultCameraService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * SampleTest
 *
 * @author echils
 */
@SpringBootTest
public class SampleTest {

    @Autowired
    private DefaultCameraService cameraService;

    @Test
    public void test() {
        System.out.println(cameraService.getCameraBasicInfo());
        System.out.println(cameraService.getChannels());
    }

}