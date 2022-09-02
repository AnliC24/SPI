package com.hitqz.device;

import com.hitqz.device.service.HkCameraService;
import com.hitqz.sjtc.core.dict.SysDataDict;
import com.hitqz.sjtc.core.model.PingEntity;
import com.hitqz.sjtc.core.model.ResultObj;
import com.hitqz.sjtc.core.sdk.*;
import com.hitqz.sjtc.core.util.MyBeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class HkCameraDeviceStatement implements HkDeviceStatement {

    private static final Logger log = LoggerFactory.getLogger(HkCameraDeviceStatement.class);

    private final HkCameraService hkCameraService;

    @Autowired
    public HkCameraDeviceStatement(HkCameraService hkCameraService) {
        this.hkCameraService = hkCameraService;
    }

    @Override
    public String getDeviceType() {
        return DeviceType.HK_CAMERA.getType();
    }


    @Override
    public BaseDevice getDeviceInfoByDeviceId(String deviceId){
         return hkCameraService.getDeviceInfoByDeviceId(deviceId);
    }

    @Override
    public boolean deleteDeviceInfoByDeviceId(String deviceId) {
        int row =hkCameraService.deleteHkCameraDevice(deviceId);
        return row>0;
    }

    @Override
    public DeviceCount getDeviceCount() {
        return null;
    }

    @Override
    public boolean editDeviceInfo(BaseDevice baseDevice) {
        int row =hkCameraService.editDeviceInfo(baseDevice);
        return row>0;
    }

    @Override
    public boolean registerDevice(BaseDevice device) {
        if(!(device instanceof VariousDeviceDto)){
            return false;
        }
        VariousDeviceDto variousDeviceDto = MyBeanUtils.getBeanByCopyProperties(device,new VariousDeviceDto());
        int row = hkCameraService.addHkCameraDeviceInfo(variousDeviceDto);
        return row > 0;
    }

    @Override
    public boolean batchRegisterDevice(List<BaseDevice> devices) {
        return false;
    }

    @Override
    public ResultObj sendCommands(String deviceId, List<SysDataDict> commands, String batchNumber) {
        for(SysDataDict command : commands){
            String code = command.getDictCode();
            switch(code){
                //01 - 抓图
                case "01":
                    boolean res = hkCameraService.getPicture(deviceId,-1,batchNumber);
                    return ResultObj.success("调用成功").withData(res);
                //02 - 预览抓图
                case "02":
                    boolean res1 = hkCameraService.getPicturePlay(deviceId,-1,batchNumber);
                    return ResultObj.success("调用成功").withData(res1);
               //03 - 设备网络状态
				case "03":
                    PingEntity res2 = hkCameraService.ping(deviceId);
                    return ResultObj.success("调用成功").withData(res2);
                default:
                    log.warn("暂不支持其他操作指令");
                    return ResultObj.fail("暂不支持其他操作指令");
            }
        }
        return ResultObj.success("调用成功");
    }

    @Override
    public String deCodeDeviceProtocol() {
        return "海康摄像设备";
    }

    @Override
    public String enCodeDeviceProtocol() {
        return "海康摄像设备";
    }


    @Override
    public boolean takePhotograph(String deviceId,String batchNumber) {
        return hkCameraService.getPicture(deviceId,-1,batchNumber);
    }
}
