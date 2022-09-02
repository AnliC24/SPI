package com.hitqz.sjtc.core.sdk;

/**
 * 2022-08-16
 * 考虑是否要做接口隔离
 * */
public interface HkDeviceStatement extends DeviceStatement{

    //拍照
    boolean takePhotograph(String deviceId,String batchNumber);
}
