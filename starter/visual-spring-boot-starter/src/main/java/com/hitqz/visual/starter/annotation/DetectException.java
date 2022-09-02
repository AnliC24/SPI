package com.hitqz.visual.starter.annotation;

import java.lang.annotation.*;

/**
 * 检测异常时处理
 * */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DetectException {
}
