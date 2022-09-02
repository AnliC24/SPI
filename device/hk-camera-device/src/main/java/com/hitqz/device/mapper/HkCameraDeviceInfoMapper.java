package com.hitqz.device.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hitqz.device.entity.HkCameraDeviceInfo;
import com.hitqz.sjtc.core.sdk.BaseDevice;
import com.hitqz.sjtc.core.sdk.VariousDeviceDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HkCameraDeviceInfoMapper extends BaseMapper<HkCameraDeviceInfo> {

    //获取设备详情
    HkCameraDeviceInfo getHkCameraDeviceInfo(String deviceId);

    int addHkCameraDeviceInfo(VariousDeviceDto variousDeviceDto);

    //删除指定设备
    int deleteHkCameraDevice(String deviceId);

    //编辑设备信息
    int editDeviceInfo(BaseDevice baseDevice);

    BaseDevice getDeviceInfoByDeviceId(String deviceId);
}
