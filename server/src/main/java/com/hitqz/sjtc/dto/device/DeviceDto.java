package com.hitqz.sjtc.dto.device;

import java.io.Serializable;

/**
 * @author windc
 * */
public class DeviceDto implements Serializable {

    private String deviceId;

    private String deviceType;

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
}
