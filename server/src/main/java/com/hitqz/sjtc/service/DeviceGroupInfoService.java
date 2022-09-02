package com.hitqz.sjtc.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hitqz.sjtc.dto.deviceGroup.DeviceGroupInfoDetail;
import com.hitqz.sjtc.dto.deviceGroup.DeviceGroupPageCondition;
import com.hitqz.sjtc.entity.DeviceGroupInfo;

import java.util.List;


public interface DeviceGroupInfoService extends IService<DeviceGroupInfo> {

    Page<DeviceGroupInfoDetail> selectDeviceGroupPage(Page<DeviceGroupInfoDetail> page, DeviceGroupPageCondition param);

    int deleteDeviceGroup(String deviceGroupId);

    List<DeviceGroupInfoDetail> getDeviceGroupInfoAll();

}
