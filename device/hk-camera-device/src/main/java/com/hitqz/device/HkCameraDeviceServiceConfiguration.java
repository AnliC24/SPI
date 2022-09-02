package com.hitqz.device;

import com.hitqz.device.aspect.OptLoginAspect;
import com.hitqz.device.config.HkCameraConfig;
import com.hitqz.device.hknative.HkServiceImpl;
import com.hitqz.device.service.HkCameraService;
import com.hitqz.device.service.impl.HkCameraServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({HkCameraProperties.class,HkCameraServiceImpl.class,OptLoginAspect.class, HkServiceImpl.class})
public class HkCameraDeviceServiceConfiguration {

    @Bean
    public HkCameraConfig hkCameraConfig(HkCameraProperties hkCameraProperties){
        return new HkCameraConfig(hkCameraProperties);
    }

    @Bean
    public HkCameraDeviceStatement hkCameraDeviceService(HkCameraService hkCameraService){
        return new HkCameraDeviceStatement(hkCameraService);
    }
}
