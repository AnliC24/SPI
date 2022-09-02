package com.hitqz.device.starter.annotation;

import java.lang.annotation.*;


/**
 * @author windc
 * 路由操作,用来处理DeviceService 生成指定类型的DeviceStatement
 * */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OptRouter {
}
