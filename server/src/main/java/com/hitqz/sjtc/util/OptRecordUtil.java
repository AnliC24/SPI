package com.hitqz.sjtc.util;

import cn.hutool.extra.servlet.ServletUtil;
import com.hitqz.sjtc.core.dict.UserDataDict;
import com.hitqz.sjtc.entity.OptRecordInfo;
import com.hitqz.sjtc.entity.SysUser;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class OptRecordUtil {

    /**
     *  获取当前请求的HttpRequest
     * */
    public static OptRecordInfo createOptRecordInfo(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) (requestAttributes != null ? requestAttributes
                .resolveReference(RequestAttributes.REFERENCE_REQUEST) : null);

        SysUser sysUser = ShiroUtils.getUserEntity();
        if(!Optional.ofNullable(sysUser).isPresent()){
            sysUser = illegalUser();
        }
        String userId = sysUser.getUserId();
        String userName = sysUser.getUserName();
        String roleType = sysUser.getRoleType();

        //获取用户的真实请求ip
        String url = request == null?"error url":request.getRequestURI();

        OptRecordInfo optRecordInfo = new OptRecordInfo();
        optRecordInfo.setOptIp(request == null?"error ip": ServletUtil.getClientIP(request));
        optRecordInfo.setUserId(userId);
        optRecordInfo.setUserName(userName);
        optRecordInfo.setRoleType(roleType);
        optRecordInfo.setOptUrl(url);

        return optRecordInfo;
    }

    /**
     * 记录非法用户
     * */
    public static SysUser illegalUser(){
        SysUser illegalUser = new SysUser();
        illegalUser.setUserId("110");
        illegalUser.setRoleType(UserDataDict.illegalAdmin.getDictCode());
        illegalUser.setUserName(UserDataDict.illegalAdmin.getDictValue());
        return illegalUser;
    }
}
