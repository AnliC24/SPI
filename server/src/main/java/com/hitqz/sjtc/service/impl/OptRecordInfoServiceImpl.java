package com.hitqz.sjtc.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hitqz.sjtc.dto.optRecord.OptRecordInfoDto;
import com.hitqz.sjtc.dto.optRecord.OptRecordPageCondition;
import com.hitqz.sjtc.entity.OptRecordInfo;
import com.hitqz.sjtc.mapper.mysql.OptRecordInfoMapper;
import com.hitqz.sjtc.service.OptRecordInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptRecordInfoServiceImpl extends ServiceImpl<OptRecordInfoMapper, OptRecordInfo> implements OptRecordInfoService {

    @Override
    public int addOptRecord(OptRecordInfo opts) {
        return baseMapper.addOptRecord(opts);
    }

    @Override
    public Page<OptRecordInfoDto> selectOptRecordPage(Page<OptRecordInfoDto> page, OptRecordPageCondition param) {
        return baseMapper.selectOptRecordPage(page,param);
    }

    @Override
    public int batchDeleteOptRecord(List<Integer> ids) {
        return baseMapper.batchDeleteOptRecord(ids);
    }
}
