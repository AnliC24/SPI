package com.hitqz.sjtc.core.sdk;

import com.hitqz.sjtc.core.dict.SysDataDict;
import com.hitqz.sjtc.core.model.ResultObj;

import java.util.List;
/**
 * @author windc
 * */
public interface DeviceStatement {

    /**
     * 获取对应的设备类型
     * */
    String getDeviceType();

    /**
     * 通过设备编号获取设备信息
     * */
    BaseDevice getDeviceInfoByDeviceId(String deviceId);

    /**
     * 删除设备信息 --- 有风险的操作
     * */
    boolean deleteDeviceInfoByDeviceId(String deviceId);

    /**
     * 统计Device各类信息
     * */
    DeviceCount getDeviceCount();

    /**
     * 编辑设备信息
     * */
    boolean editDeviceInfo(BaseDevice baseDevice);


    boolean registerDevice(BaseDevice device);

    boolean batchRegisterDevice(List<BaseDevice> devices);

    /**
     * 下发指令
     * */
    ResultObj sendCommands(String deviceId, List<SysDataDict> commands, String batchNumber);

    /**
     * 解码器
     * */
    String deCodeDeviceProtocol();

    /**
     * 编码器
     * */
    String enCodeDeviceProtocol();
}
