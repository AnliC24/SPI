package com.hitqz.device.starter;

/**
 * @author windc
 * @description 设备未注册的业务异常
 * */
public class DeviceNotRegisterException extends RuntimeException{

    public DeviceNotRegisterException(String message) {
        super(message);
    }
}
