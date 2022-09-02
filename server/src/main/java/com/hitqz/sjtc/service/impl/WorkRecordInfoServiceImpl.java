package com.hitqz.sjtc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hitqz.sjtc.entity.WorkRecordInfo;
import com.hitqz.sjtc.mapper.mysql.WorkRecordInfoMapper;
import com.hitqz.sjtc.service.WorkRecordInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkRecordInfoServiceImpl extends ServiceImpl<WorkRecordInfoMapper, WorkRecordInfo>
        implements WorkRecordInfoService {

    @Override
    public boolean saveWorkRecordBatch(List<WorkRecordInfo> workRecordInfos) {
        return baseMapper.saveWorkRecordBatch(workRecordInfos);
    }
}
