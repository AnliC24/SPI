package com.hitqz.visual.starter;

import com.hitqz.visual.starter.annotation.DetectExceptionAspect;
import com.hitqz.visual.starter.components.DefaultVisualHttpClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

@Configuration
@ConditionalOnWebApplication
@Import({DefaultVisualHttpClient.class, RestTemplate.class, DetectExceptionAspect.class})
public class VisualServiceAutoConfiguration {

    @Bean
    public VisualService visualService(){
        return new VisualService();
    }
}
