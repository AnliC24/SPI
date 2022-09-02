package com.hitqz.sjtc.dto.deviceGroup;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.Date;

@ApiModel("设备组详情")
public class DeviceGroupInfoDetail implements Serializable {

    private Integer id;

    private String deviceGroupId;

    private String deviceGroupName;

    private String deviceGroupDesc;

    private Integer bindDeviceCount;

    private String groupAddress;

    private String groupAddressCn;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date modifyTime;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBindDeviceCount() {
        return bindDeviceCount;
    }

    public void setBindDeviceCount(Integer bindDeviceCount) {
        this.bindDeviceCount = bindDeviceCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getGroupAddress() {
        return groupAddress;
    }

    public void setGroupAddress(String groupAddress) {
        this.groupAddress = groupAddress;
    }

    public String getGroupAddressCn() {
        return groupAddressCn;
    }

    public void setGroupAddressCn(String groupAddressCn) {
        this.groupAddressCn = groupAddressCn;
    }
}
