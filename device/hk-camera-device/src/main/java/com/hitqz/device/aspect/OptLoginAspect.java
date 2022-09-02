package com.hitqz.device.aspect;

import com.hitqz.device.HkCameraProperties;
import com.hitqz.device.config.HkCameraConfig;
import com.hitqz.device.entity.HkCameraDeviceInfo;
import com.hitqz.device.hknative.HCNetSDK;
import com.hitqz.device.service.HkCameraService;
import com.hitqz.device.utils.AopUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.*;


/**
 * @author windc
 * 调用摄像设备操作前 进行登录
 * 使用SPI 机制，会导致主程序无法扫描当前的子包的切面,需要手动注册
 * */
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Aspect
public class OptLoginAspect {

    private static final Logger log = LoggerFactory.getLogger(OptLoginAspect.class);

    private final HkCameraConfig hkCameraConfig;

    @Autowired
    private HkCameraProperties hkCameraProperties;

    @Autowired
    private HkCameraService hkCameraService;

    @Autowired
    public OptLoginAspect(HkCameraConfig hkCameraConfig) {
        this.hkCameraConfig = hkCameraConfig;
    }

    @Pointcut("@annotation(com.hitqz.device.annotation.OptLogin)")
    public void optLoginPointCut(){}

    /**
     * 用户登陆操作  由于业务存在多个摄像设备,每个设备都是独立的个体,所以每个设备的ip,端口,用户,密码都不一样,
     * 需要通过id去获取设备的详情  在通过设备详情去登录操作
     * */
    @Around(value = "optLoginPointCut()")
    public Object hkLogin(ProceedingJoinPoint joinPoint){
        int lUserID = -1;
        log.info("开始调用海康设备登录操作,当前lUserID :{}",lUserID);


        String sdkLogPath = hkCameraProperties.getSdkLogPath();
        int nLogLevel = hkCameraProperties.getnLogLevel();
        boolean isAutoDel = hkCameraProperties.isbAutoDel();

        HCNetSDK hCNetSDK = HkCameraConfig.INSTANCE;

        //初始化后,才能调用其他dll接口
        boolean initSuc = hCNetSDK.NET_DVR_Init();
        if (!initSuc) {
            log.error("初始化失败");
        }
        // 打印SDK日志 3  ".\\SDKLog\\" false
        hCNetSDK.NET_DVR_SetLogToFile(nLogLevel, sdkLogPath, isAutoDel);

        //获取方法名
        String methodName = joinPoint.getSignature().getName();
        //获取参数值
        Map<String, Object> params = AopUtil.getNameAndValue(joinPoint);

        String deviceId = null;

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            log.info("name: {} , value: {} ",entry.getKey() ,entry.getValue());
            if(entry.getKey().equals("deviceId")){
                deviceId = (String)entry.getValue();
            }
            if(entry.getKey().equals("lUserID")){
                lUserID = (int)entry.getValue();
            }
        }

        Object result;


        try {
            //前置通知
            log.info("The method {} deviceId with  : {}",
                    methodName,
                    deviceId);
            //获取设备详情
            if(!Optional.ofNullable(deviceId).isPresent()){
                log.error("使用OptLogin注解,必须要配在deviceId参数");
                throw new RuntimeException("请配置deviceId入参,OptLogin必须要配置deviceId");
            }
            HkCameraDeviceInfo deviceInfo = hkCameraService.getHkCameraDeviceInfo(deviceId);

            if(!Optional.ofNullable(deviceInfo).isPresent()){
                log.warn("未查询到对应设备编号的海康相机信息,请检查");
                throw new NullPointerException("不允许设备详情为空");
            }

            String ip = deviceInfo.getIpAddress();
            short port = Short.parseShort(deviceInfo.getPort());
            String userName = deviceInfo.getUserName();
            String password = deviceInfo.getPassword();

            //进行设备登录
            lUserID = hkCameraConfig.Login_V40(ip,port,userName,password);
            params.put("lUserID",lUserID);

            Object[] res = AopUtil.setNameAndValue(joinPoint,params);

            result = joinPoint.proceed(res);
            //返回通知
            log.info("The method  {}  ends with  : {}",methodName,result);
        } catch (Throwable e) {
            // 异常通知
            log.error("The method {} throws exception : {}",methodName,e);
            throw new RuntimeException(e);
        }finally {
            log.info("开始调用海康设备登出操作");
            //用户注销，释放SDK
            hkCameraConfig.Logout(lUserID);
        }
        return result;
    }
}
