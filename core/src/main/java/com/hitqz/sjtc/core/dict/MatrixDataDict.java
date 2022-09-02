package com.hitqz.sjtc.core.dict;

public final class MatrixDataDict {

    public static SysDataDict handleStatusByDetected
            = new SysDataDict("01","已检测","handle_status","作业检测状态");

    public static SysDataDict handleStatusByNotDetected
            = new SysDataDict("02","未检测","handle_status","作业检测状态");

    public static SysDataDict stdWorkNumber
            = new SysDataDict("2","单侧标准作业数","std_work_number","标准作业数");

    public static SysDataDict stdMatrix
            = new SysDataDict("01","标准矩阵","std_matrix","作业集标准数");

    public static SysDataDict unStdMatrix
            = new SysDataDict("02","非标准矩阵","std_matrix","作业集标准数");
}
