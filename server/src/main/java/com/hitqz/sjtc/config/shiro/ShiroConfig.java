package com.hitqz.sjtc.config.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;


@Configuration
public class ShiroConfig {

    @Bean
    MysqlRealm mysqlRealm() {
        MysqlRealm realm = new MysqlRealm();
        realm.setCredentialsMatcher(new CredentialsMatcher());
        return realm;
    }

    @Bean
    DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(mysqlRealm());
        return manager;
    }

    @Lazy
    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(manager);

        // 过滤器链定义映射
        Map<String, String> filterChain = new LinkedHashMap<>();

        filterChain.put("/login", "anon");

        filterChain.put("/doc.html","anon");
        filterChain.put("/**/swagger-ui/**", "anon");

        filterChain.put("/swagger-resources/**", "anon");
        filterChain.put("/swagger-ui.html", "anon");
        filterChain.put("/v2/api-docs", "anon");
        filterChain.put("/webjars/**", "anon");

        /**
         * 对所有请求都进行 验证拦截
         * */
        filterChain.put("/**", "authc");

        filterChain.put("/logout", "logout");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChain);

        /**
         * 验证拦截器 由自定义拦截器实现
         * */
        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        /**
         * shiro 默认验证拦截器实现是 FormAuthenticationFilter.class
         * 如果这里不是设置自定义拦截器，前端会出现求情302 重定向异常，此处因是shiro bug
         * bug 在 saveRequestAndRedirectToLogin 这个方法上，会无限重定向到login方法
         * */
        filters.put("authc", new MyFormAuthenticationFilter());
        shiroFilterFactoryBean.setFilters(filters);

        return shiroFilterFactoryBean;
    }

}
