package com.hitqz.sjtc.mapper.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hitqz.sjtc.entity.OriginWorkRecordInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OriginWorkRecordInfoMapper extends BaseMapper<OriginWorkRecordInfo> {

    List<String> getTenBatchNumberByOld();

    List<OriginWorkRecordInfo> getWorkRecordByBatchNumber(List<String> batchNumbers);

    int updateOriginRecordByBatchNumber(List<String> batchNumbers, String handleStatus);

    int updateOriginRecordById(List<Integer> ids, String handleStatus);

    int deleteOriginRecordByUnDetect();

    int deleteOriginRecordByIds(List<Integer> ids);
}


