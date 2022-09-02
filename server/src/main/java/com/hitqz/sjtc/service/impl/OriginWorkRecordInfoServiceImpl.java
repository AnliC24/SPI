package com.hitqz.sjtc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hitqz.sjtc.entity.OriginWorkRecordInfo;
import com.hitqz.sjtc.mapper.mysql.OriginWorkRecordInfoMapper;
import com.hitqz.sjtc.service.OriginWorkRecordInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OriginWorkRecordInfoServiceImpl extends ServiceImpl<OriginWorkRecordInfoMapper, OriginWorkRecordInfo>
    implements OriginWorkRecordInfoService {


    @Override
    public List<String> getTenBatchNumberByOld() {
        return baseMapper.getTenBatchNumberByOld();
    }

    @Override
    public List<OriginWorkRecordInfo> getWorkRecordByBatchNumber(List<String> batchNumbers) {
        return baseMapper.getWorkRecordByBatchNumber(batchNumbers);
    }

    @Override
    public int updateOriginRecordByBatchNumber(List<String> batchNumbers, String handleStatus) {
        return baseMapper.updateOriginRecordByBatchNumber(batchNumbers,handleStatus);
    }

    @Override
    public int updateOriginRecordById(List<Integer> ids, String handleStatus) {
        return baseMapper.updateOriginRecordById(ids,handleStatus);
    }

    @Override
    public int deleteOriginRecordByUnDetect() {
        return baseMapper.deleteOriginRecordByUnDetect();
    }

    @Override
    public int deleteOriginRecordByIds(List<Integer> ids) {
        return baseMapper.deleteOriginRecordByIds(ids);
    }
}
