package com.hitqz.device.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.CodeSignature;

import java.util.HashMap;
import java.util.Map;

public final class AopUtil {

    //取出对应目标方法的参数
    public static Map<String, Object> getNameAndValue(ProceedingJoinPoint joinPoint) {
        Map<String, Object> param = new HashMap<>();
        Object[] paramValues = joinPoint.getArgs();
        String[] paramNames = ((CodeSignature)joinPoint.getSignature()).getParameterNames();
        for (int i = 0; i < paramNames.length; i++) {
            param.put(paramNames[i], paramValues[i]);
        }
        return param;
    }

    //注入对应目标的参数
    public static Object[] setNameAndValue(ProceedingJoinPoint joinPoint,Map<String, Object> param) {
        Object[] paramValues = joinPoint.getArgs();
        String[] paramNames = ((CodeSignature)joinPoint.getSignature()).getParameterNames();
        for (int i = 0; i < paramNames.length; i++) {
            Object value = param.get(paramNames[i]);
            paramValues[i] = value;
        }
        return paramValues;
    }


}
