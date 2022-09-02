package com.hitqz.sjtc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hitqz.sjtc.entity.BaseDeviceInfo;
import com.hitqz.sjtc.entity.DeviceGroupMap;
import com.hitqz.sjtc.mapper.mysql.DeviceGroupMapMapper;
import com.hitqz.sjtc.service.DeviceGroupMapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceGroupMapServiceImpl extends ServiceImpl<DeviceGroupMapMapper, DeviceGroupMap> implements DeviceGroupMapService {

    private static final Logger log = LoggerFactory.getLogger(DeviceGroupMapServiceImpl.class);

    @Override
    public int batchBindDeviceGroup(List<String> deviceIds,String deviceGroupId) {
        if(deviceIds.size() <= 0){
            log.warn("deviceIds 不允许为空");
            return 0;
        }
        if(!StringUtils.hasLength(deviceGroupId)){
            log.warn("不允许即将绑定的deviceGroupId为空");
            return 0;
        }
        List<DeviceGroupMap> deviceGroupMaps = deviceIds.stream().map(item -> new DeviceGroupMap(item,deviceGroupId)).collect(Collectors.toList());
        boolean res = baseMapper.saveBatch(deviceGroupMaps);
        return res?1:0;
    }

    @Override
    public int unBindDeviceGroup(String deviceId) {
        return baseMapper.unBindDeviceGroup(deviceId);
    }

    @Override
    public List<String> isBindDeviceGroup(String deviceId) {
        return baseMapper.isBindDeviceGroup(deviceId);
    }

    @Override
    public int batchUnBindDevice(String deviceGroupId) {
        return baseMapper.batchUnBindDevice(deviceGroupId);
    }

    @Override
    public List<String> isBindDevice(String deviceGroupId) {
        return baseMapper.isBindDevice(deviceGroupId);
    }

    @Override
    public List<BaseDeviceInfo> getDeviceIdByGroupAddress(String groupAddress) {
        return baseMapper.getDeviceIdByGroupAddress(groupAddress);
    }
}
