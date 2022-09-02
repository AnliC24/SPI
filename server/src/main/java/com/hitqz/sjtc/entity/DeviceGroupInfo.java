package com.hitqz.sjtc.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hitqz.sjtc.constant.table.TableOrFieldNameByMysql;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@TableName("device_group_info")
@ApiModel("设备组信息")
public class DeviceGroupInfo implements Serializable {

    @ApiModelProperty(value = "主键编号")
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("设备组编号")
    @TableField("device_group_id")
    private String deviceGroupId;

    @ApiModelProperty("设备组名称")
    @TableField("device_group_name")
    private String deviceGroupName;

    @ApiModelProperty("设备组描述")
    @TableField("device_group_desc")
    private String deviceGroupDesc;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "modify_time", fill = FieldFill.INSERT_UPDATE)
    private Date modifyTime;

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
