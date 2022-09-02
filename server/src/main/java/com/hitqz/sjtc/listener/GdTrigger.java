package com.hitqz.sjtc.listener;

import com.hitqz.device.starter.DeviceService;
import com.hitqz.device.starter.batch.BatchNumber;
import com.hitqz.device.starter.dto.SendCommandsDto;
import com.hitqz.sjtc.core.dict.DeviceDataDict;
import com.hitqz.sjtc.core.dict.SysDataDict;
import com.hitqz.sjtc.core.model.BaseGdTrigger;
import com.hitqz.sjtc.core.sdk.DeviceType;
import com.hitqz.sjtc.entity.BaseDeviceInfo;
import com.hitqz.sjtc.service.DeviceGroupMapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 当车轮触发光电感应，嵌入式上报指令后，将消息以事件的形式传入
 * */
@Component
public class GdTrigger {

    private static final Logger log = LoggerFactory.getLogger(GdTrigger.class);

    @Resource(name = "leftTimeWheel")
    private BatchNumber leftTimeWheel;

    @Resource(name = "rightTimeWheel")
    private BatchNumber rightTimeWheel;

    @Resource
    private DeviceService deviceService;

    @Resource
    private DeviceGroupMapService deviceGroupMapService;

    private final static String LEFT = "left";

    private final static String RIGHT = "right";
    /**
     * 光电触发后，发布事件，并在此处生成批次号，并下发指令给各个设备，调用设备功能
     *
     * 批次号生成后,根据是哪一侧设备组上报的光电，先获取该设备组绑定的所有设备，然后下发指令调用该设备组的所有设备
     * */
    @EventListener
    public void GdTriggerResponse(BaseGdTrigger trigger){

        String flag = trigger.getFlag();

        //左组
        String leftGroupAddress = DeviceDataDict.groupAddressByLeft.getDictCode();

        //右组
        String rightGroupAddress = DeviceDataDict.groupAddressByRight.getDictCode();

        //生成作业批次号
        String batchNumber = null;
        if(flag.equals(leftGroupAddress)){
            batchNumber = leftTimeWheel.createBatchNumber(LEFT);
        }else if(flag.equals(rightGroupAddress)){
            batchNumber = rightTimeWheel.createBatchNumber(RIGHT);
        }else{
            log.error("无法匹配设备组地址,请检查");
            throw new NullPointerException("无法匹配设备组地址,请检查");
        }

        log.info("生成作业批次号: {}",batchNumber);


        //根据flag 获取该设备组对应的所有设备信息
        List<BaseDeviceInfo> deviceInfos = deviceGroupMapService.getDeviceIdByGroupAddress(flag);
        if(deviceInfos.size() == 0){
            log.warn("{},该设备组下未发现任何设备,请检查",deviceInfos);
            return;
        }

        //配置批次号
        SendCommandsDto sendCommandsDto = new SendCommandsDto();
        sendCommandsDto.setBatchNumber(batchNumber);

        //分成多类型设备，摄像设备和嵌入式设备，由于不知道如何调用嵌入式设备，所以暂时先处理摄像设备
        for(BaseDeviceInfo deviceInfo : deviceInfos){
            String deviceType = deviceInfo.getDeviceType();
            String deviceId = deviceInfo.getDeviceId();
            //海康摄像机 配置指令
            if(deviceType.equals(DeviceType.HK_CAMERA.getType())){
                //可以配置多条指令
                List<SysDataDict> commands = new ArrayList<>();
                commands.add(DeviceDataDict.hkTakePhotoOpt);
                sendCommandsDto.setCommands(commands);
                deviceService.sendCommands(deviceType,deviceId,sendCommandsDto);
            }

            //嵌入式设备配置对应指令

        }
    }
}
