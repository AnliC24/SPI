package com.hitqz.dict.starter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.*;

@Configuration
@ConditionalOnWebApplication
public class SysDictServiceAutoConfiguration{

    @Bean
    public SysDictService sysDictService(){
        return new SysDictService();
    }

}
