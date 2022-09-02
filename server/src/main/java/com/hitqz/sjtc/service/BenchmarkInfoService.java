package com.hitqz.sjtc.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hitqz.sjtc.dto.benchmark.BenchmarkInfoDto;
import com.hitqz.sjtc.dto.benchmark.BenchmarkPageCondition;
import com.hitqz.sjtc.entity.BenchmarkInfo;
import org.apache.ibatis.annotations.Param;

public interface BenchmarkInfoService extends IService<BenchmarkInfo> {

    Page<BenchmarkInfoDto> selectBenchmarkPage(@Param("page") Page<BenchmarkInfoDto> page, @Param("param") BenchmarkPageCondition param);
}
