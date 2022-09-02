package com.hitqz.sjtc.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @author windc
 * */
@TableName("work_record_info")
public class WorkRecordInfo implements Serializable {

    @ApiModelProperty(value = "主键编号")
    private String id;

    private String batchOperationId;

    private String deviceId;

    private int optProcess;

    private String resType;

    private String resValue;

    private String handleStatus;

    private Date createTime;

    private Date modifyTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBatchOperationId() {
        return batchOperationId;
    }

    public void setBatchOperationId(String batchOperationId) {
        this.batchOperationId = batchOperationId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public int getOptProcess() {
        return optProcess;
    }

    public void setOptProcess(int optProcess) {
        this.optProcess = optProcess;
    }

    public String getResType() {
        return resType;
    }

    public void setResType(String resType) {
        this.resType = resType;
    }

    public String getResValue() {
        return resValue;
    }

    public void setResValue(String resValue) {
        this.resValue = resValue;
    }

    public String getHandleStatus() {
        return handleStatus;
    }

    public void setHandleStatus(String handleStatus) {
        this.handleStatus = handleStatus;
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
}
