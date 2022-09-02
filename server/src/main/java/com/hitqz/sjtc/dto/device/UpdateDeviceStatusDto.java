package com.hitqz.sjtc.dto.device;

import io.swagger.annotations.ApiParam;

import java.io.Serializable;

public class UpdateDeviceStatusDto implements Serializable {

    @ApiParam(value = "设备编号")
    private String deviceId;

    @ApiParam(value = "登录状态")
    private String loginStatus;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
    }
}
