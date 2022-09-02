package com.hitqz.device.annotation;

import java.lang.annotation.*;

/**
 * @author windc
 * 调用摄像机前,需要进行登录操作
 * 操作结束后,需要登出操作
 * */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OptLogin {
}
