package com.hitqz.sjtc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hitqz.sjtc.entity.UserRelateMap;

public interface UserRelateMapService extends IService<UserRelateMap> {

    int addUserRelateMap(UserRelateMap map);
}
