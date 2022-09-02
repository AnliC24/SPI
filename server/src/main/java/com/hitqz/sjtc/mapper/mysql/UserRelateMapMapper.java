package com.hitqz.sjtc.mapper.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hitqz.sjtc.entity.UserRelateMap;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRelateMapMapper extends BaseMapper<UserRelateMap> {

    int addUserRelateMap(UserRelateMap map);
}
