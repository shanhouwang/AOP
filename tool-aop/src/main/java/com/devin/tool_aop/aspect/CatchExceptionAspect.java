package com.devin.tool_aop.aspect;

import com.devin.tool_aop.LogUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by Devin on 17/3/8.
 */
@Aspect
public class CatchExceptionAspect {

    public static final String TAG = "Aspect";

    @Pointcut("execution(@com.devin.tool_aop.annotation.CatchException * *(..))")//方法切入点
    public void catchException() {
    }

    @Around("catchException()")// 在连接点进行方法替换
    public void catchException(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            joinPoint.proceed();
        } catch (Throwable e) {
            LogUtils.e(TAG, e.getMessage());
        }
    }
}
