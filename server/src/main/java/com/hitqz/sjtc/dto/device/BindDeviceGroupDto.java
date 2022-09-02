package com.hitqz.sjtc.dto.device;

import java.io.Serializable;

public class BindDeviceGroupDto implements Serializable {

    private String deviceId;
    private String deviceGroupId;

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
