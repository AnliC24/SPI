package com.hitqz.sjtc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("设备组信息")
@TableName("device_group_map")
public class DeviceGroupMap implements Serializable {

    @ApiModelProperty("主键编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("设备编号")
    @TableField("device_id")
    private String deviceId;

    @ApiModelProperty("设备组编号")
    @TableField("device_group_id")
    private String deviceGroupId;

    public DeviceGroupMap() {
    }

    public DeviceGroupMap(String deviceId, String deviceGroupId) {
        this.deviceId = deviceId;
        this.deviceGroupId = deviceGroupId;
    }

    public DeviceGroupMap(Integer id, String deviceId, String deviceGroupId) {
        this.id = id;
        this.deviceId = deviceId;
        this.deviceGroupId = deviceGroupId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceGroupId() {
        return deviceGroupId;
    }

    public void setDeviceGroupId(String deviceGroupId) {
        this.deviceGroupId = deviceGroupId;
    }
}
