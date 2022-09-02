package com.hitqz.sjtc.mapper.phoenix;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hitqz.sjtc.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestSystemPhoenixMapper extends BaseMapper<SysUser> {

    String test();
}
