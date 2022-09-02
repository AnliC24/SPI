package com.hitqz.sjtc.annotation;

import com.hitqz.sjtc.core.model.ResultObj;
import com.hitqz.sjtc.core.util.MyBeanUtils;
import com.hitqz.sjtc.entity.OptRecordInfo;
import com.hitqz.sjtc.service.OptRecordInfoService;
import com.hitqz.sjtc.util.OptRecordUtil;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Date;

/**
 * @author windc
 * 操作记录入库切面
 * */
@Component
@Aspect
public class OptRecordAspect {

    private static final Logger log = LoggerFactory.getLogger(OptRecordAspect.class);

    private final OptRecordInfoService optRecordInfoService;

    @Autowired
    public OptRecordAspect(OptRecordInfoService optRecordInfoService) {
        this.optRecordInfoService = optRecordInfoService;
    }

    @Pointcut("@annotation(com.hitqz.sjtc.annotation.OptRecord)")
    public void optLogPointCut(){

    }

    @Pointcut("execution(* com.hitqz.sjtc.controller..*.*(..))")
    public void optExceptionLogPointCut() {
    }


    /**
     * afterReturning 是在调用完接口后,在进行日志记录
     * 所以一个接口 可能调用成功，也可能内部错误，抛出ThrowException
     * 正常处理  -- saveOptLog
     * 切点 optLogPointCut
     * */

    @AfterReturning(value = "optLogPointCut()", returning = "obj")
    public void saveOptLog(Object obj){

        log.info("开始处理请求日志");

        OptRecordInfo optRecordInfo = OptRecordUtil.createOptRecordInfo();

        ResultObj res = MyBeanUtils.getBeanByCopyProperties(obj,new ResultObj());

        optRecordInfo.setSuccess(res.getCode() == 200);

        optRecordInfo.setCreateTime(new Date());

        optRecordInfo.setOptResult(res.getMsg());


        optRecordInfoService.addOptRecord(optRecordInfo);

    }

    /**
     * 处理controller 层抛出来的所有异常记录
     * 异常处理
     * */
    @AfterThrowing(pointcut = "optExceptionLogPointCut()", throwing = "e")
    public void saveExceptionLog(Throwable e){
        log.info("开始处理接口异常抛出的操作记录");

        OptRecordInfo optRecordInfo = OptRecordUtil.createOptRecordInfo();

        //异常结果
        String exceptionMessage = stackTraceToString(e.getClass().getName(), e.getMessage(), e.getStackTrace());
        optRecordInfo.setOptResult(exceptionMessage);
        optRecordInfo.setSuccess(false);

        optRecordInfo.setCreateTime(new Date());

        optRecordInfoService.addOptRecord(optRecordInfo);

    }

    /**
     * 异常处理成字符串
     * 这边可能会异常,截取出来的异常栈可能过大，导致存储进表字段的时候，会出现溢出的问题，暂时没有出现。
     * */
    public String stackTraceToString(String exceptionName, String exceptionMessage, StackTraceElement[] elements) {
         StringBuilder buff = new StringBuilder();
         for (StackTraceElement stet : elements) {
             buff.append(stet).append("\n");
         }
        return exceptionName + ":" + exceptionMessage + "\n\t" + buff.toString();
    }

}
