package com.hitqz.sjtc.dto.deviceGroup;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("设备组交互实体--新增/编辑")
public class DeviceGroupInfoDto implements Serializable {

    @ApiModelProperty("主键编号,设备组编辑时一定要带id")
    private Integer id;

    @ApiModelProperty("设备组编号,新增时不需要,编辑时一定要带")
    private String deviceGroupId;

    private String deviceGroupName;

    private String deviceGroupDesc;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeviceGroupId() {
        return deviceGroupId;
    }

    public void setDeviceGroupId(String deviceGroupId) {
        this.deviceGroupId = deviceGroupId;
    }

    public String getDeviceGroupName() {
        return deviceGroupName;
    }

    public void setDeviceGroupName(String deviceGroupName) {
        this.deviceGroupName = deviceGroupName;
    }

    public String getDeviceGroupDesc() {
        return deviceGroupDesc;
    }

    public void setDeviceGroupDesc(String deviceGroupDesc) {
        this.deviceGroupDesc = deviceGroupDesc;
    }
}
