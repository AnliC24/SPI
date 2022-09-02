package com.hitqz.sjtc.core.util;

import org.springframework.beans.BeanUtils;

/**
 * @author windc
 * object -> Bean 工具
 * Map -> Bean
 * List -> Bean
 * <p>
 * 深拷贝还是浅拷贝的问题，还需注意
 */
public final class MyBeanUtils {

    public static <T> T getBeanByCopyProperties(Object source, T target) {
        BeanUtils.copyProperties(source, target);
        return target;
    }
}
