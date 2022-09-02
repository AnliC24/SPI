package com.hitqz.sjtc.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hitqz.sjtc.dto.optRecord.OptRecordInfoDto;
import com.hitqz.sjtc.dto.optRecord.OptRecordPageCondition;
import com.hitqz.sjtc.entity.OptRecordInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OptRecordInfoService extends IService<OptRecordInfo> {

    int addOptRecord(OptRecordInfo opt);

    Page<OptRecordInfoDto> selectOptRecordPage(@Param("page") Page<OptRecordInfoDto> page, @Param("param") OptRecordPageCondition param);

    int batchDeleteOptRecord(List<Integer> ids);
}
