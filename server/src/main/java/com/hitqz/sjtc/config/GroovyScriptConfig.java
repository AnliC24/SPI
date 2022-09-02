package com.hitqz.sjtc.config;

import cn.hutool.core.io.resource.NoResourceException;
import groovy.lang.Binding;
import groovy.lang.Script;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import java.io.IOException;


/**
 * @author windc
 * @description groovy 动态脚本配置
 */
@Component
public class GroovyScriptConfig<T> implements ApplicationContextAware {

    private static final Logger log = LoggerFactory.getLogger(GroovyScriptConfig.class);

    /**
     * 开发环境下:
     *      H:/hgdProject/zhxf/code/server/src/main/resources/groovyScript
     * 生产环境下:
     *      ./resources/groovyScript/
     * */
    private String groovyResource;

    public final static String GROOVY_RESOURCE_PATH = "groovyResource";

    public final static String SPRING_PROFILES_ACTIVE = "spring.profiles.active";

    private static GroovyScriptEngine engine = null;

    /**
     * script name
     */
    public static Script createScriptByScriptName(String scriptName) throws ResourceException, ScriptException {
        return engine.createScript(scriptName, new Binding());
    }

    /**
     * 用于初始化groovy引擎
     * */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        try {
            groovyResource = applicationContext.getEnvironment().getProperty(GROOVY_RESOURCE_PATH);
            String profile = applicationContext.getEnvironment().getProperty(SPRING_PROFILES_ACTIVE);
            if(!StringUtils.hasLength(groovyResource)){
                log.error("GroovyResource {} 路径为空,请检查yml配置文件",groovyResource);
            }
            if(!StringUtils.hasLength(profile)){
                log.error("spring.profiles.active 路径为空");
            }
            else if("prod".equalsIgnoreCase(profile)){
                /*
                  生产环境走./resources/groovyScript 路径
                  */
                engine = new GroovyScriptEngine(groovyResource);
            }
            else{
                /*
                 其余环境走全部走 classpath  target 文件
                 1.先检查是否能在classpath路径下找到对应的资源文件
                 2.如果存在把文件下的脚本名称打印出来
                 3.资源路径正确的情况下,将file的绝对路径提供给GroovyScriptEngine加载
                 */
                ClassPathResource file = new ClassPathResource(groovyResource);
                if(!file.getFile().exists()){
                    log.info(file.getFile().getAbsolutePath()+"该路径下无Groovy文件,请检查");
                }
                else{
                    for(String fileName : file.getFile().list()){
                        log.info("加载Groovy文件: {}",fileName);
                    }
                }
                engine = new GroovyScriptEngine(file.getFile().getAbsolutePath());
            }
        }  catch (NoResourceException e){
            log.error("路径："+ groovyResource + " 不存在");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("GroovyScriptEngine 加载异常,异常原因 :"+e.getMessage());
        }
    }
}
