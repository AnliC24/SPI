package com.hitqz.sjtc.mapper.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hitqz.sjtc.dto.device.BaseDeviceInfoDto;
import com.hitqz.sjtc.dto.device.DevicePageCondition;
import com.hitqz.sjtc.entity.BaseDeviceInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.Date;

@Mapper
public interface BaseDeviceInfoMapper extends BaseMapper<BaseDeviceInfo> {

    Page<BaseDeviceInfoDto> selectDevicePage(@Param("page") Page<BaseDeviceInfoDto> page, @Param("param") DevicePageCondition param);

    int updateDeviceLoginStatus(String deviceId, String loginStatus, Date modifyTime);

    int addBaseDeviceInfo(BaseDeviceInfo baseDeviceInfo);

    int deleteDeviceInfo(String deviceId);

    BaseDeviceInfo getDeviceInfo(String deviceId);

    int updateDeviceNetWork(String network,String deviceId);
}
