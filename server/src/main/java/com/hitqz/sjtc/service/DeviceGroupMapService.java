package com.hitqz.sjtc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hitqz.sjtc.entity.BaseDeviceInfo;
import com.hitqz.sjtc.entity.DeviceGroupMap;

import java.util.List;

public interface DeviceGroupMapService extends IService<DeviceGroupMap> {
    //设备批量绑定设备组
    int batchBindDeviceGroup(List<String> deviceIds,String deviceGroupId);

    //设备解除设备组绑定
    int unBindDeviceGroup(String deviceId);

    //获取当前设备绑定的设备组
    List<String> isBindDeviceGroup(String deviceId);

    //根据设备组编号,批量删除设备绑定
    int batchUnBindDevice(String deviceGroupId);

    List<String> isBindDevice(String deviceGroupId);

    //根据设备组 group_address 类型,获取所有该设备组下的设备编号
    List<BaseDeviceInfo> getDeviceIdByGroupAddress(String groupAddress);

}
