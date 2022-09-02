package com.hitqz.sjtc.mapper.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hitqz.sjtc.entity.WorkRecordInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WorkRecordInfoMapper extends BaseMapper<WorkRecordInfo> {

    boolean saveWorkRecordBatch(@Param("lists") List<WorkRecordInfo> workRecordInfos);
}
