package com.hitqz.sjtc.listener;

import com.hitqz.sjtc.core.dict.MatrixDataDict;
import com.hitqz.sjtc.core.model.BaseWorkRecord;
import com.hitqz.sjtc.core.util.MyBeanUtils;
import com.hitqz.sjtc.entity.BaseDeviceInfo;
import com.hitqz.sjtc.entity.OriginWorkRecordInfo;
import com.hitqz.sjtc.service.BaseDeviceInfoService;
import com.hitqz.sjtc.service.OriginWorkRecordInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author windc
 * 用于处理原始作业记录信息
 * */
@Component
public class OriginWorkRecord {

    private static final Logger log = LoggerFactory.getLogger(OriginWorkRecord.class);

    @Resource
    private OriginWorkRecordInfoService originWorkRecordInfoService;

    @Resource
    private BaseDeviceInfoService baseDeviceInfoService;

    //将作业记录保存起来,无需同步记录
    @Async
    @EventListener
    public void saveWorkRecord(BaseWorkRecord workRecord){
        String deviceId = workRecord.getDeviceId();
        log.info("{} 开始记录作业结果信息",deviceId);

        if(!StringUtils.hasLength(deviceId)){
            log.error("设备编号为空,作业记录保存异常");
            return;
        }

        //默认未检测
        String handleStatus = MatrixDataDict.handleStatusByNotDetected.getDictCode();

        //获取该设备对应的工序号
        BaseDeviceInfo deviceInfo = baseDeviceInfoService.getDeviceInfo(deviceId);

        Integer optProcess = deviceInfo.getOptProcess();

        OriginWorkRecordInfo record = MyBeanUtils.getBeanByCopyProperties(workRecord,new OriginWorkRecordInfo());
        record.setCreateTime(new Date());
        record.setModifyTime(new Date());
        record.setOptProcess(optProcess);
        record.setHandleStatus(handleStatus);

        originWorkRecordInfoService.save(record);
    }
}
