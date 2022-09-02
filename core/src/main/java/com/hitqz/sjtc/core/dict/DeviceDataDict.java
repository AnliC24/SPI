package com.hitqz.sjtc.core.dict;


/**
 * @author windc
 * 设备模块 系统字典 系统字典是不可改变的
 * */
public final class DeviceDataDict {

    public static SysDataDict deviceLoginStatusByOnline
            = new SysDataDict("01","上线","login_status","登录状态");

    public static SysDataDict deviceLoginStatusByUnderline
            = new SysDataDict("02","离线","login_status","登录状态");

    public static SysDataDict deviceLoginStatusByFault
            = new SysDataDict("03","故障","login_status","登录状态");

    public static SysDataDict groupAddressByLeft
            = new SysDataDict("01","左侧", "group_address","组地址-台车左侧");

    public static SysDataDict groupAddressByRight
            = new SysDataDict("02","右侧","group_address","组地址-台车右侧");

    public static SysDataDict hkTakePhotoOpt
            = new SysDataDict("01","海康摄像抓图","opt_command","设备操作指令");
			
	public static SysDataDict hkTakePhotoPlayOpt
            = new SysDataDict("02","海康摄像预览抓图","opt_command","设备操作指令");

    public static SysDataDict pingOpt
            = new SysDataDict("03","ping指令","opt_command","设备操作指令");

    public static SysDataDict cameraWorkResType
            = new SysDataDict("01","摄像设备作业","res_type","作业类型");

    public static SysDataDict embeddedWorkResType
            = new SysDataDict("02","嵌入式设备作业","res_type","作业类型");

}


