package com.hitqz.device;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "hitqz.device.hkcamera")
@Primary
public class HkCameraProperties {

    //还看HCNNetSDK.dll 地址 可用绝对路径和相对路径
    private String HcNetSdkPath;

    /**
     * sdk产生的日志地址
     * */
    private String sdkLogPath;

    /**
     * 日志级别
     * */
    private int nLogLevel;

    /**
     * 是否自动删除日志
     * */
    private boolean bAutoDel;

    /**
     * 调用海康设备照片存储路径
     * */
    private String savePhotoPath;

    public String getSavePhotoPath() {
        return savePhotoPath;
    }

    public void setSavePhotoPath(String savePhotoPath) {
        this.savePhotoPath = savePhotoPath;
    }

    public String getHcNetSdkPath() {
        return HcNetSdkPath;
    }

    public void setHcNetSdkPath(String hcNetSdkPath) {
        HcNetSdkPath = hcNetSdkPath;
    }

    public String getSdkLogPath() {
        return sdkLogPath;
    }

    public void setSdkLogPath(String sdkLogPath) {
        this.sdkLogPath = sdkLogPath;
    }

    public int getnLogLevel() {
        return nLogLevel;
    }

    public void setnLogLevel(int nLogLevel) {
        this.nLogLevel = nLogLevel;
    }

    public boolean isbAutoDel() {
        return bAutoDel;
    }

    public void setbAutoDel(boolean bAutoDel) {
        this.bAutoDel = bAutoDel;
    }
}
