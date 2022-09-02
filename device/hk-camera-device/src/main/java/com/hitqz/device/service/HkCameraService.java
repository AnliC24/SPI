package com.hitqz.device.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hitqz.device.entity.HkCameraDeviceInfo;
import com.hitqz.sjtc.core.model.PingEntity;
import com.hitqz.sjtc.core.sdk.BaseDevice;
import com.hitqz.sjtc.core.sdk.VariousDeviceDto;

public interface HkCameraService extends IService<HkCameraDeviceInfo> {

    HkCameraDeviceInfo getHkCameraDeviceInfo(String deviceId);

    //测试是否能正常登录设备
    HkCameraDeviceInfo test(String deviceId);

    //注册海康设备
    int addHkCameraDeviceInfo(VariousDeviceDto variousDeviceDto);

    //删除海康设备
    int deleteHkCameraDevice(String deviceId);

    //根据 id 查询海康设备
    BaseDevice getDeviceInfoByDeviceId(String deviceId);

    //编辑设备信息
    int editDeviceInfo(BaseDevice baseDevice);

    //测试调用海康拍照
    boolean getPicture(String deviceId,int lUserId,String batchNumber);

    //测试调用海康预览抓图功能
    boolean getPicturePlay(String deviceId,int lUserId,String batchNumber);
	
	//ping 指令功能
    PingEntity ping(String deviceId);
}
