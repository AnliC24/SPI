package com.hitqz.sjtc.controller;

import com.google.common.collect.Maps;
import com.hitqz.sjtc.annotation.OptRecord;
import com.hitqz.sjtc.core.model.BaseController;
import com.hitqz.sjtc.core.model.ResultObj;
import com.hitqz.sjtc.core.model.UserLogin;
import com.hitqz.sjtc.entity.SysUser;
import com.hitqz.sjtc.service.TestSystemPhoenixService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@RestController
@RequestMapping(value = "/security/manage")
@Api(tags = "登录控制器")
public class LoginController extends BaseController {

    @Resource
    private TestSystemPhoenixService testSystemPhoenixService;

    /**
     * 这里的重复性登录 是否需要进行逻辑处理
     */
    @OptRecord
    @PostMapping(value = "/login")
    @ApiOperation("登录请求")
    public ResultObj login(@RequestBody UserLogin dto, HttpServletRequest request) {
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(dto.getUserName(), dto.getUserPassword().trim());
        Subject subject = SecurityUtils.getSubject();
        SysUser userLogin;
        try {
            if (subject.isAuthenticated()) {
                subject.logout();
            }
            subject.login(usernamePasswordToken);
            userLogin = (SysUser) subject.getPrincipal();
        } catch (UnknownAccountException | LockedAccountException e) {
            return ResultObj.fail(e.getMessage());
        } catch (Exception e) {
            return ResultObj.fail("密码错误,请重试");
        }
        Map<String, Object> ret = Maps.newHashMap();
        ret.put("uid", request.getSession().getId());
        ret.put("token", request.getSession().getId());
        ret.put("user", userLogin);

        return ResultObj.success("用户: "+userLogin.getUserName()+" 登录成功").withData(ret);
    }


    @OptRecord
    @GetMapping(value = "/logout")
    @ApiOperation("登出请求")
    public ResultObj logout() {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return ResultObj.success("用户成功登出");
    }

    @GetMapping(value = "/test")
    @ApiOperation("phoenix连接测试")
    public ResultObj test() {
        String userName = testSystemPhoenixService.test();
        return ResultObj.success().withData(userName);
    }



}
