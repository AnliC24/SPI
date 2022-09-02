package com.hitqz.sjtc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hitqz.sjtc.entity.SysUser;
import com.hitqz.sjtc.mapper.phoenix.TestSystemPhoenixMapper;
import com.hitqz.sjtc.service.TestSystemPhoenixService;
import org.springframework.stereotype.Service;


@Service
public class TestSystemPhoenixServiceImpl extends ServiceImpl<TestSystemPhoenixMapper, SysUser> implements TestSystemPhoenixService {

    @Override
    public String test() {
        return baseMapper.test();
    }
}
