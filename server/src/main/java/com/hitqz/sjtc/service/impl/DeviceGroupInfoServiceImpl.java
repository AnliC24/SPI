package com.hitqz.sjtc.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hitqz.sjtc.dto.deviceGroup.DeviceGroupInfoDetail;
import com.hitqz.sjtc.dto.deviceGroup.DeviceGroupPageCondition;
import com.hitqz.sjtc.entity.DeviceGroupInfo;
import com.hitqz.sjtc.mapper.mysql.DeviceGroupInfoMapper;
import com.hitqz.sjtc.service.DeviceGroupInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceGroupInfoServiceImpl extends ServiceImpl<DeviceGroupInfoMapper, DeviceGroupInfo> implements DeviceGroupInfoService {

    @Override
    public Page<DeviceGroupInfoDetail> selectDeviceGroupPage(Page<DeviceGroupInfoDetail> page, DeviceGroupPageCondition param) {
        return baseMapper.selectDeviceGroupPage(page,param);
    }

    @Override
    public int deleteDeviceGroup(String deviceGroupId) {
        return baseMapper.deleteDeviceGroup(deviceGroupId);
    }

    @Override
    public List<DeviceGroupInfoDetail> getDeviceGroupInfoAll() {
        return baseMapper.getDeviceGroupInfoAll();
    }
}
