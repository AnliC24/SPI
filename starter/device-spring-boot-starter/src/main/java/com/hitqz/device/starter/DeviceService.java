package com.hitqz.device.starter;

import com.hitqz.device.starter.annotation.OptRouter;
import com.hitqz.device.starter.dto.SendCommandsDto;
import com.hitqz.sjtc.core.dict.SysDataDict;
import com.hitqz.sjtc.core.model.ResultObj;
import com.hitqz.sjtc.core.sdk.BaseDevice;
import com.hitqz.sjtc.core.sdk.DeviceStatement;
import java.util.List;

/**
 * @author windc
 * 设备服务
 * */
public class DeviceService {

    public static DeviceStatement deviceStatement = null;

    /**
     * 获取对应设备详情
     * */
    @OptRouter
    public BaseDevice getDeviceDetail(String deviceId, String deviceType){
        return deviceStatement.getDeviceInfoByDeviceId(deviceId);
    }

    /**
     * 设备建档
     * */
    @OptRouter
    public boolean registerDeviceInfo(String deviceType,BaseDevice deviceInfo){
        return deviceStatement.registerDevice(deviceInfo);
    }

    /**
     * 根据设备编号，或设备序列号 获取对应的设备信息
     * */
    @OptRouter
    public BaseDevice getDeviceDetailByDeviceId(String deviceType, String deviceId){
        return deviceStatement.getDeviceInfoByDeviceId(deviceId);
    }

    /**
     * 根据设备编号 -- 编辑对应设备的基础信息
     * */
    @OptRouter
    public boolean editDevice(String deviceType,BaseDevice device){
        return deviceStatement.editDeviceInfo(device);
    }

    /**
     * 注销设备信息
     * */
    @OptRouter
    public boolean deleteDevice(String deviceType,String deviceId){
        return deviceStatement.deleteDeviceInfoByDeviceId(deviceId);
    }

    /**
     * 对设备发送指令
     * */
    @OptRouter
    public ResultObj sendCommands(String deviceType,
                                  String deviceId, SendCommandsDto sendCommandsDto){
        List<SysDataDict> commands = sendCommandsDto.getCommands();
        String batchNumber = sendCommandsDto.getBatchNumber();
        return deviceStatement.sendCommands(deviceId,commands,batchNumber);
    }


}
