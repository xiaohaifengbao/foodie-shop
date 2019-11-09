package com.imooc.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @Classname LoggerAspectConfig
 * @Description TODO
 * @Date 2019/11/9 18:23
 * @Created by 于明灏
 */
@Component
@Aspect
@Slf4j
public class LoggerAspectConfig {


    @Around(value = "execution(* com.imooc.controller..*.*(..))")
    public Object AspectLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // 打印起始的类名和方法名
        log.info("======== 开始执行{}.{} ========", joinPoint.getTarget().getClass()
                                                    , joinPoint.getSignature().getName());

        // 当前起始时间
        long startTime = System.currentTimeMillis();
        // 执行完毕
        Object proceed = joinPoint.proceed();
        // 获取时间差(毫秒)
        long resultTime = System.currentTimeMillis() - startTime;

        // 根据时间差打印日志级别
        if(resultTime > 3000) {
            log.error("======== 执行完毕 执行时间：{} 毫秒 ========", resultTime);
        }else if (resultTime > 2000) {
            log.warn("======== 执行完毕 执行时间：{} 毫秒 ========", resultTime);
        }else {
            log.info("======== 执行完毕 执行时间：{} 毫秒 ========", resultTime);
        }

        return proceed;
    }


}
