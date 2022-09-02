package com.hitqz.sjtc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hitqz.sjtc.entity.OriginWorkRecordInfo;

import java.util.List;

public interface OriginWorkRecordInfoService extends IService<OriginWorkRecordInfo> {

    //获取创建时间最早的10条批次号
    List<String> getTenBatchNumberByOld();

    //根据批次号获取其对应的作业记录
    List<OriginWorkRecordInfo> getWorkRecordByBatchNumber(List<String> batchNumbers);

    //根据批次号,更新其所有状态，将其置为已处理
    int updateOriginRecordByBatchNumber(List<String> batchNumbers,String handleStatus);

    //根据id去更新handle_status
    int updateOriginRecordById(List<Integer> ids,String handleStatus);

    //删除所有未聚合检测的作业记录信息
    int deleteOriginRecordByUnDetect();

    //根据id删除所有记录信息
    int deleteOriginRecordByIds(List<Integer> ids);
}
