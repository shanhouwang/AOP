package com.devin.tool_aop.aspect;

import com.devin.tool_aop.LogUtils;
import com.devin.tool_aop.annotation.SingleClick;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Devin on 17/6/26.
 * <p>
 * 防止被连续点击，时间间隔 1秒钟
 */
@Aspect
public class SingleClickAspect {

    public static final String TAG = "Aspect";
    static Map<String, Long> map = new HashMap<>();

    // 方法切入点
    @Pointcut("execution(@com.devin.tool_aop.annotation.SingleClick * *(..)) && @annotation(singleClick)")
    public void singleClick(SingleClick singleClick) {
    }

    // 在连接点进行方法替换
    @Around("singleClick(singleClick)")
    public Object around(ProceedingJoinPoint joinPoint, SingleClick singleClick) throws Throwable {
        String key = joinPoint.toString();
        LogUtils.d(TAG, ">>>>>singleClick, key: " + key);
        Long lastClickTime = map.get(key);
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (lastClickTime == null || lastClickTime == 0) {
            map.put(key, currentTime);
            // 执行原方法
            return joinPoint.proceed();
        } else {
            // 过滤掉singleClick毫秒内的连续点击
            if (currentTime - lastClickTime > singleClick.value()) {
                map.put(key, currentTime);
                // 执行原方法
                return joinPoint.proceed();
            }
        }
        return null;
    }
}
