package com.devin.tool_aop.aspect;

import android.view.View;

import com.devin.tool_aop.LogUtils;
import com.devin.tool_aop.R;
import com.devin.tool_aop.annotation.SingleClick;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Calendar;

/**
 * Created by Devin on 17/6/26.
 *
 * 防止被连续点击，时间间隔 1秒钟
 */
@Aspect
public class SingleClickAspect {

    public static final String TAG = "Aspect";
    static int TIME_TAG = R.id.click_time;

    @Pointcut("execution(@com.devin.tool_aop.annotation.SingleClick * *(..)) && @annotation(singleClick)")// 方法切入点
    public void singleClick(SingleClick singleClick) {
    }

    @Around("singleClick(singleClick)")// 在连接点进行方法替换
    public void around(ProceedingJoinPoint joinPoint, SingleClick singleClick) throws Throwable {
        LogUtils.d(TAG, ">>>>>singleClick");
        View view = null;
        for (Object arg : joinPoint.getArgs()) {
            if (arg instanceof View) {
                view = (View) arg;
            }
        }
        if (view != null) {
            Object tag = view.getTag(TIME_TAG);
            long lastClickTime = ((tag != null) ? (long) tag : 0);
            long currentTime = Calendar.getInstance().getTimeInMillis();
            if (currentTime - lastClickTime > singleClick.value()) {// 过滤掉singleClick毫秒内的连续点击
                view.setTag(TIME_TAG, currentTime);
                joinPoint.proceed();//执行原方法
            }
        }
    }
}
