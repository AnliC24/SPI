package com.hitqz.sjtc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hitqz.sjtc.entity.UserRelateMap;
import com.hitqz.sjtc.mapper.mysql.UserRelateMapMapper;

import com.hitqz.sjtc.service.UserRelateMapService;
import org.springframework.stereotype.Service;

@Service
public class UserRelateMapServiceImpl extends ServiceImpl<UserRelateMapMapper, UserRelateMap> implements UserRelateMapService {

    @Override
    public int addUserRelateMap(UserRelateMap map) {
        return baseMapper.addUserRelateMap(map);
    }
}
