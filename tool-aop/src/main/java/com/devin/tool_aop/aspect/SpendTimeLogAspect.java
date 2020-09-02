package com.devin.tool_aop.aspect;

import com.devin.tool_aop.AopUtils;
import com.devin.tool_aop.LogUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.concurrent.TimeUnit;

/**
 * @author Devin on 17/3/23.
 * <p>
 * 自动打印方法的耗时
 */
@Aspect
public class SpendTimeLogAspect {

    public static final String TAG = "Aspect";

    @Pointcut("execution(@com.devin.tool_aop.annotation.SpendTimeLog * *(..))") // 方法切入点
    public void methodAnnotated() {
    }

    @Pointcut("execution(@com.devin.tool_aop.annotation.SpendTimeLog *.new(..))") // 构造器切入点
    public void constructorAnnotated() {
    }

    @Around("methodAnnotated() || constructorAnnotated()") // 在连接点进行方法替换
    public Object spendTime(ProceedingJoinPoint joinPoint) throws Throwable {
        if (!AopUtils.DEBUG) {
            // 执行原方法
            return joinPoint.proceed();
        }
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        long startTime = System.nanoTime();
        // 执行原方法
        Object result = joinPoint.proceed();
        StringBuilder keyBuilder = new StringBuilder();
        keyBuilder.append(methodName + ":");
        for (Object obj : joinPoint.getArgs()) {
            if (obj instanceof String) {
                keyBuilder.append((String) obj);
            } else if (obj instanceof Class) {
                keyBuilder.append(((Class) obj).getSimpleName());
            }
        }
        String key = keyBuilder.toString();
        // 打印时间差
        LogUtils.d(TAG, (className + "." + key + " >>>>>:" + "[" + (TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime)) + "ms]"));
        return result;
    }
}