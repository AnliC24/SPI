package com.hitqz.sjtc.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.hitqz.sjtc.core.sdk.BaseDevice;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

@TableName("base_device_info")
@ApiModel("设备基础信息表")
public class BaseDeviceInfo extends BaseDevice implements Serializable {

    @ApiModelProperty(value = "主键编号")
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "网络连接状态")
    @TableField("network_connect")
    private String networkConnect;

    @ApiModelProperty(value = "操作工序")
    @TableField("opt_process")
    private Integer optProcess;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNetworkConnect() {
        return networkConnect;
    }

    public void setNetworkConnect(String networkConnect) {
        this.networkConnect = networkConnect;
    }

    public Integer getOptProcess() {
        return optProcess;
    }

    public void setOptProcess(Integer optProcess) {
        this.optProcess = optProcess;
    }

}
