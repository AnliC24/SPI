package com.hitqz.sjtc.controller;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hitqz.sjtc.core.dict.UserDataDict;
import com.hitqz.sjtc.core.model.BaseController;
import com.hitqz.sjtc.core.model.PageCondition;
import com.hitqz.sjtc.core.model.PageResultEntity;
import com.hitqz.sjtc.core.model.ResultObj;
import com.hitqz.sjtc.core.util.MyBeanUtils;
import com.hitqz.sjtc.dto.user.QueryUserParam;
import com.hitqz.sjtc.dto.user.SysUserDto;
import com.hitqz.sjtc.dto.user.SysUserPageCondition;
import com.hitqz.sjtc.entity.SysUser;
import com.hitqz.sjtc.service.SysUserService;
import com.hitqz.sjtc.util.ShiroUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.util.*;

/**
 * @author windc
 * 烧结台车-用户管理
 * 超级管理员 + 作业人员 两个角色
 * */
@Api(tags = "系统用户管理")
@RestController
@RequestMapping(value = "/user/manage")
public class SysUserController extends BaseController implements PageTemplate {

    private static final Logger log = LoggerFactory.getLogger(SysUserController.class);

    private final SysUserService sysUserService;

    @Autowired
    public SysUserController(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    @GetMapping("/getUserPage")
    @ApiOperation(value = "分页获取系统用户列表")
    public ResultObj getUserPage(SysUserPageCondition param){
        return pageTemplate(param);
    }

    @Override
    public ResultObj executePage(PageCondition param) {
        QueryUserParam queryUserParam = new QueryUserParam();

        IPage<SysUserDto> pageData = sysUserService.selectUserPage(new Page<>(param.getCurrentPage(),param.getPageSize()),
                queryUserParam);

        PageResultEntity<SysUserDto> retData = this.convertEntityPageList(pageData);
        return ResultObj.success().withData(retData);
    }

    /**
     * 新增用户
     * */
    @ApiOperation("新增用户信息")
    @PostMapping("/addUser")
    public ResultObj addUser(@RequestBody SysUserDto sysUserDto){
        SysUser user = MyBeanUtils.getBeanByCopyProperties(sysUserDto, new SysUser());

        String userId = IdUtil.simpleUUID();
        user.setUserId(userId);

        int res = sysUserService.addUser(user);

        if (res == 0) {
            return ResultObj.fail("用户添加失败");
        }
        return ResultObj.success("用户添加成功");
    }

    /**
     * 超级管理员才有权限变更用户角色类型
     * 作业操作人员只允许修改自身的 email / phone / password
     * */
    @ApiOperation("更新用户信息")
    @PutMapping("/editUser")
    public ResultObj editUser(@RequestBody SysUserDto model){
        if(!Optional.ofNullable(model).isPresent()){
            return ResultObj.fail("不允许传入参数为空");
        }

        if (!StringUtils.hasLength(String.valueOf(model.getId()))) {
            return ResultObj.fail("参数错误，ID不能为空");
        }

        String needEditSysUserRoleType = model.getRoleType();

        if(StringUtils.hasLength(needEditSysUserRoleType)){
            log.warn("不支持角色类型的变更");
        }

        SysUser user = MyBeanUtils.getBeanByCopyProperties(model, new SysUser());

        int res = sysUserService.updateUser(user);

        if (res == 0) {
            return ResultObj.fail("更新失败");
        }
        return ResultObj.success("更新成功");
    }

    /**
     * 逻辑删除
     * */
    @DeleteMapping("/deleteUser")
    @ApiOperation(value = "根据ID停用系统用户信息")
    public ResultObj deleteById(Integer id) {

        if (!Optional.ofNullable(id).isPresent()) {
            return ResultObj.fail("id不能为空");
        }

        SysUser user = sysUserService.getById(id);

        if (user.getRoleType().equalsIgnoreCase(UserDataDict.superAdmin.getDictCode())) {
            return ResultObj.fail("无权停用超级管理账户");
        }
        //将用户的状态变更为停用
        user.setUserStatus(UserDataDict.userStatusByBlock.getDictCode());

        if (!sysUserService.updateById(user)) {
            return ResultObj.fail("停用失败");
        }

        return ResultObj.success("停用成功");
    }

    /**
     * 需要对批量删除的id进行验证,
     * 不允许
     * */
    @DeleteMapping("/batchDeleteUser")
    @ApiOperation(value = "根据ID批量停用系统用户信息")
    public ResultObj deleteByIds(@ApiParam(name = "ids",value="用户批量的id eg 1,2,3") String ids) {

        if (!StringUtils.hasLength(ids)) {
            return ResultObj.fail("id不能为空");
        }

        String loginRoleType = ShiroUtils.getUserEntity().getRoleType();

        if(!loginRoleType.equals(UserDataDict.superAdmin.getDictCode())){
            return ResultObj.fail("不允许非超级管理员进行批量停用");
        }
        String[] idStr = ids.split(",");
        List<Integer> delIds = new ArrayList<>();

        for(String id : idStr){
            Integer idI = Integer.valueOf(id);
            delIds.add(idI);
        }

        List<String> roleTypes = sysUserService.getRoleTypeByIds(delIds);

        if(roleTypes.contains(UserDataDict.superAdmin.getDictCode())){
            return ResultObj.fail("批量停用的系统用户信息内,包含超级管理员,请排除");
        }

        int row = sysUserService.updateUserStatus(delIds,UserDataDict.userStatusByBlock.getDictCode(),new Date());

        if(row == 0){
            return ResultObj.fail("批量停用失败");
        }

        return ResultObj.success("批量停用成功");
    }

    /**
     * 获取用户详情
     * */
    @GetMapping("/getUserById")
    @ApiOperation(value = "根据ID获取用户信息")
    public ResultObj getById(@RequestParam("id") Integer id) {

        if (!Optional.ofNullable(id).isPresent()) {
            return ResultObj.fail("id不能为空");
        }

        SysUserDto user = sysUserService.getSysUserById(id);

        if (!Optional.ofNullable(user).isPresent()) {
            return ResultObj.fail("信息不存在");
        }

        return ResultObj.success().withData(user);
    }


}
