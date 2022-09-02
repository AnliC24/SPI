package com.hitqz.sjtc.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hitqz.sjtc.dto.device.BaseDeviceInfoDto;
import com.hitqz.sjtc.dto.device.DevicePageCondition;
import com.hitqz.sjtc.core.sdk.VariousDeviceDto;
import com.hitqz.sjtc.entity.BaseDeviceInfo;

import java.util.Date;

public interface BaseDeviceInfoService extends IService<BaseDeviceInfo> {

    Page<BaseDeviceInfoDto> selectDevicePage(Page<BaseDeviceInfoDto> page, DevicePageCondition param);

    int updateDeviceLoginStatus(String deviceId, String loginStatus, Date modifyTime);

    //新增设备
    boolean registerDeviceInfo(VariousDeviceDto variousDeviceDto);

    //注销设备功能
    int deleteDeviceInfo(String deviceId,String deviceType);

    //根据设备编号获取设备信息
    BaseDeviceInfo getDeviceInfo(String deviceId);

    //更新设备网络状态
    int updateDeviceNetWork(String network,String deviceId);
}
