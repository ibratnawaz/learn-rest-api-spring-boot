package com.learn.rest_crud.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @AfterThrowing("execution(* com.learn.rest_crud..*(..))")
    public void logMethodCrashed(JoinPoint joinPoint) {
        log.warn("method: " + joinPoint.getSignature().getDeclaringTypeName() + "::" + joinPoint.getSignature().getName() + " has thrown some error!");
    }
}
