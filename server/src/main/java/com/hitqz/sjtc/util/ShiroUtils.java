package com.hitqz.sjtc.util;

import com.hitqz.sjtc.entity.SysUser;
import groovy.util.logging.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
/**
 * @author windC
 * 获取shiro框架的登录用户信息
 */
@Slf4j
public class ShiroUtils {

    /**
     * 这个方法有问题
     */
    public static boolean isLogin() {
        return SecurityUtils.getSubject().getPrincipal() != null;
    }

    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public static SysUser getUserEntity() {
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }

    public static Integer getId() {
        return getUserEntity().getId();
    }

    public static String getUserId() {
        return getUserEntity().getUserId();
    }


}
