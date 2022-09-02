package com.hitqz.device.starter.annotation;

import com.hitqz.device.starter.DeviceRouter;
import com.hitqz.device.starter.DeviceService;
import com.hitqz.device.utils.AopUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Map;

@EnableAspectJAutoProxy(proxyTargetClass = true)
@Aspect
public class OptRouterAspect {

    private static final Logger log = LoggerFactory.getLogger(OptRouterAspect.class);

    private static final String deviceId = "deviceId";

    private static final String deviceType = "deviceType";

    @Resource
    private DeviceRouter deviceRouter;

    @Pointcut("@annotation(com.hitqz.device.starter.annotation.OptRouter)")
    public void optRouterPointCut(){}

    @Around(value = "optRouterPointCut()")
    public Object deviceRouter(ProceedingJoinPoint joinPoint){
        log.info("开始进行设备多类型路由");

        //获取方法名
        String methodName = joinPoint.getSignature().getName();
        //获取参数值
        Map<String, Object> params = AopUtil.getNameAndValue(joinPoint);

        String deviceId = null;
        String deviceType = null;

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            log.info("name: {} , value: {} ",entry.getKey() ,entry.getValue());
//            if(entry.getKey().equals(OptRouterAspect.deviceId)){
//                deviceId = (String)entry.getValue();
//            }
            if(entry.getKey().equals(OptRouterAspect.deviceType)){
                deviceType = (String)entry.getValue();
            }
        }

        if(!StringUtils.hasLength(deviceType)){
            log.error("不允许调用设备路由插件传入空类型,deviceType : {}",deviceType);
            throw new NullPointerException("不允许调用设备路由插件传入空类型");
        }

//        if(!StringUtils.hasLength(deviceId)){
//            log.error("不允许调用设备路由插件传入空类型,deviceId : {}",deviceId);
//            throw new NullPointerException("不允许调用设备路由插件传入空类型");
//        }

        Object result;

        try {
            //前置通知
            DeviceService.deviceStatement = deviceRouter.getDeviceStatement(deviceType);
            result = joinPoint.proceed();
            //返回通知
            log.info("The method  {}  ends with  : {}",methodName,result);
        } catch (Throwable e) {
            // 异常通知
            log.error("The method {} throws exception : {}",methodName,e);
            throw new RuntimeException(e);
        }
        return result;
    }

}
