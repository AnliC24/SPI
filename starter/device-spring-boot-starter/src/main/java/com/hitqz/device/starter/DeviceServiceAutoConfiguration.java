package com.hitqz.device.starter;

import com.hitqz.device.starter.annotation.OptRouterAspect;
import com.hitqz.device.starter.batch.BatchNumber;
import com.hitqz.device.starter.batch.LeftTimeWheel;
import com.hitqz.device.starter.batch.RightTimeWheel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DeviceRouter.class})
public class DeviceServiceAutoConfiguration {

    @Bean
    public BatchNumber leftTimeWheel(){
        return new LeftTimeWheel();
    }

    @Bean
    public BatchNumber rightTimeWheel(){
        return new RightTimeWheel();
    }

    @Bean
    public OptRouterAspect optLoginAspect(){
        return new OptRouterAspect();
    }

    @Bean
    public DeviceService deviceService(){
        return new DeviceService();
    }

}
