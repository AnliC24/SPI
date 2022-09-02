package com.hitqz.sjtc.config.shiro;

import com.hitqz.sjtc.core.dict.UserDataDict;
import com.hitqz.sjtc.entity.SysUser;
import com.hitqz.sjtc.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * @author windc
 * @descrition shiro 框架内的领域组件，两个重载方法
 * doGetAuthorizationInfo 为当前角色配置权限，比如配置一些可以无需权限的所有路径，或进行按钮控制
 * doGetAuthenticationInfo 登录用户名和密码和角色进行与数据源信息的比对
 * shiro 中要求一个数据源配一个 realm
 */
@Component
public class MysqlRealm extends AuthorizingRealm {

    private static final Logger log = LoggerFactory.getLogger(MysqlRealm.class);

    @Autowired
    private SysUserService sysUserService;

    public MysqlRealm() {}

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        List<String> permissions = new ArrayList<>();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissions);
        return info;
    }

    /**
     * 验证是否符合正确角色
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        SysUser user = sysUserService.findByUserName(usernamePasswordToken.getUsername());

        if (user == null) {
            throw new UnknownAccountException("该账号不存在");
        }
        if (user.getUserStatus().equals(UserDataDict.userStatusByBlock.getDictCode())) {
            throw new LockedAccountException("该账号已经被冻结");
        }
        return new SimpleAuthenticationInfo(user, user.getUserPassword(), this.getClass().getName());
    }
}
