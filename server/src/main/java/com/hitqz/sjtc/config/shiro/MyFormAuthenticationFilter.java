package com.hitqz.sjtc.config.shiro;

import cn.hutool.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author windC
 * 指定url 需要进行验证登录
 */
public class MyFormAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    public void setLoginUrl(String loginUrl) {
        super.setLoginUrl("/security/manage/login");
    }

    /**
     * FormAuthenticationFilter.loginUrl = "login.jsp"
     * shiro 框架使用了自定义的 Path match 来对url进行匹配拦截
     * 这里也有个bug，AntPathMatch使用了 fullMatch = true 的方式 对路径进行匹配，匹配的是 loginUrl 和 requestUrl
     * 这就导致了 只要loginUrl 和 requestUrl 没设置成一样的，就无法判断它为登录请求，无限退出
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            return true;
        } else {
            HttpServletRequest httpRequest = WebUtils.toHttp(request);
            HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
            httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpServletResponse.setContentType("application/json; charset=utf-8");
            httpServletResponse.setCharacterEncoding("UTF-8");
            PrintWriter out = httpServletResponse.getWriter();
            JSONObject result = new JSONObject();
            result.put("msg", "请先进行登陆");
            result.put("data", new JSONObject());
            result.put("code", HttpServletResponse.SC_UNAUTHORIZED);
            out.println(result);
            out.flush();
            out.close();
            return false;
        }
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        WebUtils.getAndClearSavedRequest(request);
        // 登录成功之后 设置session时间为365天 单位为毫秒
        SecurityUtils.getSubject().getSession().setTimeout(365 * 24 * 60 * 60 * 1000L);
        System.out.println("设置过期时间：");
        return false;
    }

}
