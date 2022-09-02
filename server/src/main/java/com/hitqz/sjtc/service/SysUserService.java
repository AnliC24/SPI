package com.hitqz.sjtc.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hitqz.sjtc.dto.user.QueryUserParam;
import com.hitqz.sjtc.dto.user.SysUserDto;
import com.hitqz.sjtc.dto.user.UserPhoneByUserIds;
import com.hitqz.sjtc.entity.SysUser;
import java.util.Date;
import java.util.List;

public interface SysUserService extends IService<SysUser> {

    SysUser findByUserName(String userName);

    String getSuperiorUserIdByUserId(String userId);

    Page<SysUserDto> selectUserPage(Page<SysUserDto> page, QueryUserParam param);

    SysUserDto getSysUserById(int id);

    /**
     * 根据用户编号,批量获取对应的用户手机联系号码
     * */
    List<UserPhoneByUserIds> getUserPhonesByUserIds(List<String> userIds,String roleType);

    List<SysUser> getUserInfoByUserId(List<String> userIds);

    //根据用户编号,获取对应的用户名称
    String getUserNameByUserId(String userId);

    //新增用户信息
    int addUser(SysUser user);

    int updateUser(SysUser user);

    List<String> getRoleTypeByIds(List<Integer> ids);

    int updateUserStatus(List<Integer> ids, String userStatus , Date modifyTime);

}
