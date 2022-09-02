package com.hitqz.sjtc.core.service.user;

import java.util.List;

/**
 * @author windc
 * 系统用户模型对外提供统一标准
 * */
public interface UserStatement {

    //根据用户编号，获取其模型所属所有上级节点
    List<BaseUser> getAllSuperNodeByUserId(String userId);

    //根据用户编号，获取其模型所属所有下级节点
    List<BaseUser> getAllLowerNodeByUserId(String userId);

    //根据用户编号，获取其模型所属用信息
    BaseUser getDetailByUserId(String userId);
}
