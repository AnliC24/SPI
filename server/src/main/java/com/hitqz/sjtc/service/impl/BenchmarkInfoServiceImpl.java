package com.hitqz.sjtc.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hitqz.sjtc.dto.benchmark.BenchmarkInfoDto;
import com.hitqz.sjtc.dto.benchmark.BenchmarkPageCondition;
import com.hitqz.sjtc.entity.BenchmarkInfo;
import com.hitqz.sjtc.mapper.mysql.BenchmarkInfoMapper;
import com.hitqz.sjtc.service.BenchmarkInfoService;
import org.springframework.stereotype.Service;

@Service
public class BenchmarkInfoServiceImpl extends ServiceImpl<BenchmarkInfoMapper, BenchmarkInfo> implements BenchmarkInfoService {
    @Override
    public Page<BenchmarkInfoDto> selectBenchmarkPage(Page<BenchmarkInfoDto> page, BenchmarkPageCondition param) {
        return baseMapper.selectBenchmarkPage(page,param);
    }
}
