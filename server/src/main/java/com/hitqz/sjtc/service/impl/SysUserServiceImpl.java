package com.hitqz.sjtc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hitqz.sjtc.core.dict.UserDataDict;
import com.hitqz.sjtc.dto.user.QueryUserParam;
import com.hitqz.sjtc.dto.user.SysUserDto;
import com.hitqz.sjtc.dto.user.UserPhoneByUserIds;
import com.hitqz.sjtc.entity.SysUser;
import com.hitqz.sjtc.entity.UserRelateMap;
import com.hitqz.sjtc.mapper.mysql.SysUserMapper;
import com.hitqz.sjtc.service.SysUserService;
import com.hitqz.sjtc.service.UserRelateMapService;
import com.hitqz.sjtc.util.ShiroUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    private static final Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Resource
    private UserRelateMapService userRelateMapService;

    @Override
    public SysUser findByUserName(String userName) {
        return baseMapper.findByUserName(userName);
    }

    public IPage<SysUser> searchByPage(int currentPage, int pageSize, QueryWrapper<SysUser> queryWrapper) {
        Page<SysUser> page = new Page<>(currentPage, pageSize);
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public String getSuperiorUserIdByUserId(String userId) {
        return baseMapper.getSuperiorUserIdByUserId(userId);
    }

    @Override
    public Page<SysUserDto> selectUserPage(Page<SysUserDto> page, QueryUserParam param) {
        return baseMapper.selectUserPage(page,param);
    }

    @Override
    public SysUserDto getSysUserById(int id) {
        return baseMapper.getSysUserById(id);
    }

    @Override
    public List<UserPhoneByUserIds> getUserPhonesByUserIds(List<String> userIds, String roleType) {
        return baseMapper.getUserPhonesByUserIds(userIds,roleType);
    }

    @Override
    public List<SysUser> getUserInfoByUserId(List<String> userIds) {
        return baseMapper.getUserInfoByUserId(userIds);
    }

    @Override
    public String getUserNameByUserId(String userId) {
        return baseMapper.getUserNameByUserId(userId);
    }

    /**
     * 新增一个用户的同时，需要同时补入该用户的上级信息
     * */
    @Transactional
    @Override
    public int addUser(SysUser user) {

        String currentRoleType = ShiroUtils.getUserEntity().getRoleType();
        String currentUserId = ShiroUtils.getUserId();
        String currentUserName = ShiroUtils.getUserEntity().getUserName();

        String userId = user.getUserId();

        UserRelateMap userRelateMap = new UserRelateMap();

        userRelateMap.setUserId(userId);
        userRelateMap.setUserName(user.getUserName());

        userRelateMap.setSuperRelateId(currentUserId);
        userRelateMap.setSuperRelateName(currentUserName);

        //如果当前是超级管理员
        if(UserDataDict.superAdmin.getDictCode().equals(currentRoleType)){
            user.setRoleType(UserDataDict.operationAdmin.getDictCode());
        }

        //创建时默认为正常使用状态
        user.setUserStatus(UserDataDict.userStatusByNormal.getDictCode());
        user.setUserId(userId);

        int res = baseMapper.addUser(user);
        int relateRes = userRelateMapService.addUserRelateMap(userRelateMap);

        return res==1&&relateRes==1?1:0;
    }

    @Override
    public int updateUser(SysUser user) {

        String email = user.getUserEmail();
        String phone = user.getUserPhone();
        if(!StringUtils.hasLength(email)){
            log.warn("不允许邮箱为空");
            return 0;
        }
        if(!StringUtils.hasLength(phone)){
            log.warn("不允许联系电话为空");
            return 0;
        }

        String password = user.getUserPassword();
        if(!StringUtils.hasLength(password)){
            log.warn("不允许密码为空");
            return 0;
        }
        user.setModifyTime(new Date());

        return baseMapper.updateUser(user);
    }

    @Override
    public List<String> getRoleTypeByIds(List<Integer> ids) {
        return baseMapper.getRoleTypeByIds(ids);
    }

    @Override
    public int updateUserStatus(List<Integer> ids, String userStatus, Date modifyTime) {
        return baseMapper.updateUserStatus(ids,userStatus,modifyTime);
    }

}
