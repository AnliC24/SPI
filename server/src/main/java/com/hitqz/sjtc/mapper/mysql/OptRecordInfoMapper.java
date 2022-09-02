package com.hitqz.sjtc.mapper.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hitqz.sjtc.dto.optRecord.OptRecordInfoDto;
import com.hitqz.sjtc.dto.optRecord.OptRecordPageCondition;
import com.hitqz.sjtc.entity.OptRecordInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OptRecordInfoMapper extends BaseMapper<OptRecordInfo> {

    int addOptRecord(@Param("opt") OptRecordInfo opt);

    Page<OptRecordInfoDto> selectOptRecordPage(@Param("page") Page<OptRecordInfoDto> page, @Param("param") OptRecordPageCondition param);

    int batchDeleteOptRecord(List<Integer> ids);
}
