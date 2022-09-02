package com.hitqz.visual.starter.components;

import com.google.gson.Gson;
import com.hitqz.visual.starter.entity.VisualRequest;
import com.hitqz.visual.starter.entity.VisualResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import javax.annotation.Resource;

/**
 * 第三方调用的框架很多，例如httpClient，okHttp，restTemplate等
 * 这边默认实现一种 restTemplate
 * */
@Component
public class DefaultVisualHttpClient implements VisualHttpClient{

    @Resource
    private RestTemplate restTemplate;

    private final static String MEDIA_TYPE = "application/json;UTF-8";

    @Override
    public String request(VisualRequest http) {
        HttpHeaders headers = new HttpHeaders();
        //配置json报文格式
        MediaType contentType = MediaType.valueOf(MEDIA_TYPE);
        headers.setContentType(contentType);
        String requestJson = new Gson().toJson(http);
        HttpEntity<String> strEntity = new HttpEntity<>(requestJson,headers);
        String url = http.getUrl();
        return restTemplate.postForObject(url, strEntity, String.class);
    }

    @Override
    public VisualResponse mapResponse(String result) {
        return new Gson().fromJson(result,VisualResponse.class);
    }
}
