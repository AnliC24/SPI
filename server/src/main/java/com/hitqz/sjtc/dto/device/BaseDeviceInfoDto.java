package com.hitqz.sjtc.dto.device;

import com.hitqz.sjtc.core.sdk.BaseDevice;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


public class BaseDeviceInfoDto extends BaseDevice implements Serializable {

    @ApiModelProperty(value = "主键编号")
    private Integer id;

    @ApiModelProperty(value = "网络连接状态")
    private String networkConnect;

    @ApiModelProperty(value = "操作工序")
    private Integer optProcess;

    @ApiModelProperty(value = "工序描述")
    private String optProcessDesc;

    @ApiModelProperty(value = "设备类型描述")
    private String deviceTypeCn;

    @ApiModelProperty("ip地址")
    private String ipAddress;

    @ApiModelProperty("port")
    private String port;

    @ApiModelProperty("user_name")
    private String userName;

    @ApiModelProperty("password")
    private String password;

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

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

    public String getOptProcessDesc() {
        return optProcessDesc;
    }

    public void setOptProcessDesc(String optProcessDesc) {
        this.optProcessDesc = optProcessDesc;
    }

    public String getDeviceTypeCn() {
        return deviceTypeCn;
    }

    public void setDeviceTypeCn(String deviceTypeCn) {
        this.deviceTypeCn = deviceTypeCn;
    }
}
