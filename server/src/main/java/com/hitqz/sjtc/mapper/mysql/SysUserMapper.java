package com.hitqz.sjtc.mapper.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hitqz.sjtc.dto.user.QueryUserParam;
import com.hitqz.sjtc.dto.user.SysUserDto;
import com.hitqz.sjtc.dto.user.UserPhoneByUserIds;
import com.hitqz.sjtc.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    SysUser findByUserName(@Param("userName") String userName);

    String getSuperiorUserIdByUserId(@Param("userId") String userId);

    /**
     * 2022-04-21 根据当前登录用户查询其所有子层用户信息
     * */
    Page<SysUserDto> selectUserPage(@Param("page") Page<SysUserDto> page, @Param("param") QueryUserParam param);

    SysUserDto getSysUserById(int id);

    List<UserPhoneByUserIds> getUserPhonesByUserIds(@Param("userIds") List<String> userIds,@Param("roleType") String roleType);

    List<SysUser> getUserInfoByUserId(@Param("userIds") List<String> userIds);

    String getUserNameByUserId(String userId);

    int addUser(SysUser user);

    int updateUser(SysUser user);

    //获取ids 对应的各个角色类型
    List<String> getRoleTypeByIds(List<Integer> ids);

    //批量变更用户状态
    int updateUserStatus(@Param("ids") List<Integer> ids,@Param("userStatus") String userStatus , @Param("modifyTime") Date modifyTime);
}