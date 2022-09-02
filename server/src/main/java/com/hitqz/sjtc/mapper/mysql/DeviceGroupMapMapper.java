package com.hitqz.sjtc.mapper.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hitqz.sjtc.entity.BaseDeviceInfo;
import com.hitqz.sjtc.entity.DeviceGroupMap;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeviceGroupMapMapper extends BaseMapper<DeviceGroupMap> {

    //设备批量绑定设备组
    int batchBindDeviceGroup(List<String> deviceIds,String deviceGroupId);

    //设备解除设备组绑定
    int unBindDeviceGroup(String deviceId);

    //获取当前设备绑定的设备组
    List<String> isBindDeviceGroup(String deviceId);

    //根据设备组编号,批量删除设备绑定
    int batchUnBindDevice(String deviceGroupId);

    //设备组是否存在绑定设备
    List<String> isBindDevice(String deviceGroupId);

    boolean saveBatch(List<DeviceGroupMap> deviceGroupMaps);

    List<BaseDeviceInfo> getDeviceIdByGroupAddress(String groupAddress);
}
