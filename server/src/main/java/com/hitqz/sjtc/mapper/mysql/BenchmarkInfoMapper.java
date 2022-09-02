package com.hitqz.sjtc.mapper.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hitqz.sjtc.dto.benchmark.BenchmarkInfoDto;
import com.hitqz.sjtc.dto.benchmark.BenchmarkPageCondition;
import com.hitqz.sjtc.entity.BenchmarkInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BenchmarkInfoMapper extends BaseMapper<BenchmarkInfo> {

    Page<BenchmarkInfoDto> selectBenchmarkPage(@Param("page") Page<BenchmarkInfoDto> page, @Param("param") BenchmarkPageCondition param);
}
