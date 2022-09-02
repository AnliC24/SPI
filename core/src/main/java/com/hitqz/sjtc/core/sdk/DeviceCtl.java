package com.hitqz.sjtc.core.sdk;

import com.hitqz.sjtc.core.dict.SysDataDict;
import java.util.List;


/**
 * @author windc
 * 设备操作集
 * */
public interface DeviceCtl {

    //deviceId 设备编号 ctlOption 操作指令
    boolean doCtl(String deviceId, List<SysDataDict> ctlOption);
}
