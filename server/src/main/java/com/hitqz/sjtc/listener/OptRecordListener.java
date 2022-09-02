package com.hitqz.sjtc.listener;

import com.hitqz.sjtc.core.model.BaseOptRecord;
import com.hitqz.sjtc.entity.OptRecordInfo;
import com.hitqz.sjtc.service.OptRecordInfoService;
import com.hitqz.sjtc.util.OptRecordUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.Date;

/**
 * 各子模块如果产生异常,需要记录操作信息，则发布 ctx.publishEvent(record)
 * BaseOptRecord record
 * */
@Component
public class OptRecordListener {

    private static final Logger log = LoggerFactory.getLogger(OptRecordListener.class);

    @Resource
    private OptRecordInfoService optRecordInfoService;

    @Async
    @EventListener
    public void saveOptRecord(BaseOptRecord record){
        log.info("开始记录操作信息");
        int code = record.getCode();

        if(code == 200){
            OptRecordInfo optRecordInfo = OptRecordUtil.createOptRecordInfo();

            //异常结果
            String message = record.getMessage();
            optRecordInfo.setOptResult(message);
            optRecordInfo.setSuccess(true);

            optRecordInfo.setCreateTime(new Date());

            optRecordInfoService.addOptRecord(optRecordInfo);
            return;
        }

        if(code == 500){
            OptRecordInfo optRecordInfo = OptRecordUtil.createOptRecordInfo();

            //异常结果
            String message = record.getMessage();
            optRecordInfo.setOptResult(message);
            optRecordInfo.setSuccess(false);

            optRecordInfo.setCreateTime(new Date());

            optRecordInfoService.addOptRecord(optRecordInfo);
            return;
        }

        log.error("不支持除 200/500 以外的内部编码状态");
    }
}
