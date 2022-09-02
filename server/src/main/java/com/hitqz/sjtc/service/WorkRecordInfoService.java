package com.hitqz.sjtc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hitqz.sjtc.entity.WorkRecordInfo;

import java.util.List;

public interface WorkRecordInfoService extends IService<WorkRecordInfo> {

    boolean saveWorkRecordBatch(List<WorkRecordInfo> workRecordInfos);
}
