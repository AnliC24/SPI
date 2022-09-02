package com.hitqz.sjtc.mapper.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hitqz.sjtc.dto.deviceGroup.DeviceGroupInfoDetail;
import com.hitqz.sjtc.dto.deviceGroup.DeviceGroupPageCondition;
import com.hitqz.sjtc.entity.DeviceGroupInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DeviceGroupInfoMapper extends BaseMapper<DeviceGroupInfo> {

    Page<DeviceGroupInfoDetail> selectDeviceGroupPage(@Param("page") Page<DeviceGroupInfoDetail> page, @Param("param") DeviceGroupPageCondition param);

    //删除设备组
    int deleteDeviceGroup(String deviceGroupId);

    List<DeviceGroupInfoDetail> getDeviceGroupInfoAll();

}
