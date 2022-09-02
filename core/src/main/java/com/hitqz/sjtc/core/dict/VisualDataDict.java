package com.hitqz.sjtc.core.dict;

/**
 * 视觉服务字典
 * */
public final class VisualDataDict {


    public static SysDataDict visualRequest
            = new SysDataDict("01","http://192.168.124.57:48060/v1.0/hb_robot/autorec","visual_request","视觉检测-请求路径");

    public static SysDataDict detectWheelNumber
            = new SysDataDict("1","轮子编号识别","visual_detect_type","视觉检测类型");


    public static SysDataDict detectWheelTurn
            = new SysDataDict("2","轮子转动异常检测","visual_detect_type","视觉检测类型");
}
