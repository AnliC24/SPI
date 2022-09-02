package com.hitqz.sjtc.start;

import com.hitqz.sjtc.service.OriginWorkRecordInfoService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

/**
 * @author windc
 * 程序启动时,处理一些初始化操作
 * 1.将原始作业记表数据清空
 * 为了保证批次号连续，可用。每次程序宕机，重启后，都需要将之前的记录信息都删除
 * */
@Component
public class StartUpRunner implements ApplicationRunner {

    @Resource
    private OriginWorkRecordInfoService originWorkRecordInfoService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //TRUNCATE origin_work_record_info
        originWorkRecordInfoService.deleteOriginRecordByUnDetect();
    }
}
