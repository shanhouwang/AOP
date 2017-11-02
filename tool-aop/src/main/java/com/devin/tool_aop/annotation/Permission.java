package com.devin.tool_aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Devin on 17/3/8.
 * <p>
 * 申请系统权限注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Permission {

    String value() default "";

    boolean must() default false; // 是否可以不授权能继续下一步
}