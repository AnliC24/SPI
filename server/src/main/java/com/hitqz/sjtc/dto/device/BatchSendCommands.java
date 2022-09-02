package com.hitqz.sjtc.dto.device;

import com.hitqz.sjtc.core.dict.SysDataDict;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class BatchSendCommands implements Serializable {

    @ApiModelProperty("设备编号")
    private String deviceId;

    @ApiModelProperty("设备类型")
    private String deviceType;

    @ApiModelProperty("下发指令")
    private List<SysDataDict> commands;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public List<SysDataDict> getCommands() {
        return commands;
    }

    public void setCommands(List<SysDataDict> commands) {
        this.commands = commands;
    }
}
