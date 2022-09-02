package com.hitqz.sjtc.core.sdk;

import java.util.Optional;

public enum DeviceType {

    HK_CAMERA("08","camera","海康摄像机"),
    OIL_FILL("09","oil-fill","注油装置"),
    WHEEL_CORE_CLEAN("10","wheel-core-clean","轮芯清理");

    private String type;

    private String name;

    private String desc;

    DeviceType(String deviceType,String name,String desc){
        this.type = deviceType;
        this.name = name;
        this.desc = desc;
    }

    public static DeviceType getDeviceType(String type) {
        for( DeviceType deviceType : DeviceType.class.getEnumConstants()) {
            if(type.equals(deviceType.type))
                return deviceType;
        }
        throw new IllegalArgumentException("Type:" + type + " is not a valid "
                + "Types.java value.");
    }

    public static DeviceType getByDeviceName(String name) {
        for( DeviceType deviceType : DeviceType.class.getEnumConstants()) {
            if(name.equals(deviceType.name))
                return deviceType;
        }
        throw new IllegalArgumentException("Name:" + name + "智能监控平台暂不支持此类型设备");
    }


    public static boolean isAllowDeviceType(String type){
        return Optional.ofNullable(getDeviceType(type)).isPresent()?true:false;
    }

    public static boolean isAllowDeviceName(String name){
        return Optional.ofNullable(getByDeviceName(name)).isPresent()?true:false;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

}
