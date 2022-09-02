package com.hitqz.sjtc.core.model;

import java.io.Serializable;

/**
 * 起源作业表
 * */
public class BaseWorkRecord implements Serializable {

    //批次号
    private String batchOperationId;

    //产生作业结果的设备编号
    private String deviceId;

    //结果类型，区分未 摄像结果 或 嵌入式消息结果
    private String resType;

    //结果值
    private String resValue;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
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

    public String getBatchOperationId() {
        return batchOperationId;
    }

    public void setBatchOperationId(String batchOperationId) {
        this.batchOperationId = batchOperationId;
    }
}
