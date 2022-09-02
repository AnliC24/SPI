package com.hitqz.device.starter;

import com.google.common.collect.Maps;
import com.hitqz.sjtc.core.sdk.DeviceStatement;
import com.hitqz.sjtc.core.sdk.DeviceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DeviceRouter implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(DeviceRouter.class);

    private static final Map<String, DeviceStatement> routerMap = Maps.newHashMap();

    @Resource
    private List<DeviceStatement> deviceStatementList;

    @Override
    public void afterPropertiesSet(){
        for (DeviceStatement statement : deviceStatementList) {
            String deviceType = statement.getDeviceType();
            DeviceType type = DeviceType.getDeviceType(deviceType);
            log.info("已加载：{} {} {} 类型设备",deviceType,type.getName(),type.getDesc());
            routerMap.put(deviceType, statement);
        }
    }

    public DeviceStatement getDeviceStatement(String type) {
        if(!routerMap.containsKey(type)){
            log.error("{} 该类型的设备未注册",type);
            if(Optional.ofNullable(DeviceType.getDeviceType(type)).isPresent()){
                log.error("{} 该类型对应的设备也许是: {}",type, DeviceType.getDeviceType(type).getName());
            }
            throw new DeviceNotRegisterException(type+"类型的设备未注册");
        }
        return routerMap.get(type);
    }
}
