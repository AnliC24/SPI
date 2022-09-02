package com.hitqz.visual.starter.annotation;

import com.hitqz.sjtc.core.model.BaseOptRecord;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.annotation.Resource;

@EnableAspectJAutoProxy(proxyTargetClass = true)
@Aspect
public class DetectExceptionAspect {

    private static final Logger log = LoggerFactory.getLogger(DetectExceptionAspect.class);

    @Resource
    private ApplicationContext ctx;

    @Pointcut("execution(* com.hitqz.visual.starter..*.*(..))")
    public void optExceptionLogPointCut() {
    }

    @AfterThrowing(pointcut = "optExceptionLogPointCut()", throwing = "e")
    public void saveExceptionLog(Throwable e){
        log.info("视觉服务调用发生异常");
        //发布调用异常事件
        String message = e.getMessage();
        BaseOptRecord record = new BaseOptRecord(500,message);
        ctx.publishEvent(record);
    }
}
