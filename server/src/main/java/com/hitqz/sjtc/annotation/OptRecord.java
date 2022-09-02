package com.hitqz.sjtc.annotation;

import java.lang.annotation.*;

/**
 * @author windc
 * 重要接口调用日志记录
 * */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OptRecord {


}
