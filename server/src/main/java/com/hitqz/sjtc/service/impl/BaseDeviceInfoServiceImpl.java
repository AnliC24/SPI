package com.hitqz.sjtc.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hitqz.device.starter.DeviceService;
import com.hitqz.sjtc.core.util.MyBeanUtils;
import com.hitqz.sjtc.dto.device.BaseDeviceInfoDto;
import com.hitqz.sjtc.dto.device.DevicePageCondition;
import com.hitqz.sjtc.core.sdk.VariousDeviceDto;
import com.hitqz.sjtc.entity.BaseDeviceInfo;
import com.hitqz.sjtc.mapper.mysql.BaseDeviceInfoMapper;
import com.hitqz.sjtc.service.BaseDeviceInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.Date;

@Service
public class BaseDeviceInfoServiceImpl extends ServiceImpl<BaseDeviceInfoMapper, BaseDeviceInfo> implements BaseDeviceInfoService {

    @Resource
    private DeviceService deviceService;

    @Override
    public Page<BaseDeviceInfoDto> selectDevicePage(Page<BaseDeviceInfoDto> page, DevicePageCondition param) {
        return baseMapper.selectDevicePage(page,param);
    }

    @Override
    public int updateDeviceLoginStatus(String deviceId, String loginStatus, Date modifyTime) {
        return baseMapper.updateDeviceLoginStatus(deviceId,loginStatus,modifyTime);
    }

    //可能有事务问题,还未发现
    @Transactional
    @Override
    public boolean registerDeviceInfo(VariousDeviceDto variousDeviceDto) {
        //注册基础信息表
        BaseDeviceInfo baseDevice = MyBeanUtils.getBeanByCopyProperties(variousDeviceDto,new BaseDeviceInfo());
        baseDevice.setNetworkConnect("0ms");
        baseMapper.addBaseDeviceInfo(baseDevice);
        String deviceType = variousDeviceDto.getDeviceType();
        //注册详情信息表
        return deviceService.registerDeviceInfo(deviceType,variousDeviceDto);
    }

    @Transactional
    @Override
    public int deleteDeviceInfo(String deviceId,String deviceType) {
        //删除设备基础信息表
        int row1 = baseMapper.deleteDeviceInfo(deviceId);
        //删除设备详情信息表
        boolean row2 = deviceService.deleteDevice(deviceType,deviceId);
        return row1>0&&row2?1:0;
    }

    @Override
    public BaseDeviceInfo getDeviceInfo(String deviceId) {
        return baseMapper.getDeviceInfo(deviceId);
    }

    @Override
    public int updateDeviceNetWork(String network,String deviceId) {
        return baseMapper.updateDeviceNetWork(network,deviceId);
    }
}
