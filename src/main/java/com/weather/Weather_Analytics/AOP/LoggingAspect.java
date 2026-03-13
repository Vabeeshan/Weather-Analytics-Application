package com.weather.Weather_Analytics.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* com.weather.Weather_Analytiics..*(..))")
    public Object logExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        logger.info("Entering into {}.{}",className,methodName);

        Object result;

        try{
            result = joinPoint.proceed();
        }
        catch (Exception e){
            logger.warn("Entering {}.{} : {}",className,methodName,e.getMessage());
            throw e;
        }

        logger.info("Leaving {}.{}",className,methodName);
        return result;
    }
}
