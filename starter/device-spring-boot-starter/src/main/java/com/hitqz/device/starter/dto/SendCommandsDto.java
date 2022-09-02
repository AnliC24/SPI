package com.hitqz.device.starter.dto;

import com.hitqz.sjtc.core.dict.SysDataDict;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author windc
 * 发送指令所需的标准参数
 * */
public class SendCommandsDto implements Serializable {

    @ApiModelProperty("作业批次号")
    private String batchNumber;

    private List<SysDataDict> commands;

    public List<SysDataDict> getCommands() {
        return commands;
    }

    public void setCommands(List<SysDataDict> commands) {
        this.commands = commands;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }
}
