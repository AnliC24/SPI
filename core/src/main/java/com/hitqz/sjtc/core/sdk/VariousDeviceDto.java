package com.hitqz.sjtc.core.sdk;


import java.io.Serializable;

public class VariousDeviceDto extends BaseDevice implements Serializable {

    private String ipAddress;

    private String port;

    private String userName;

    private String password;

    private Integer optProcess;

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getOptProcess() {
        return optProcess;
    }

    public void setOptProcess(Integer optProcess) {
        this.optProcess = optProcess;
    }
}
