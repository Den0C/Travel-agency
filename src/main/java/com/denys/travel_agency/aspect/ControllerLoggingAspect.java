package com.denys.travel_agency.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerLoggingAspect {
    private static final Logger logger = LogManager.getLogger(ControllerLoggingAspect.class);

   @Before("execution(* com.denys.travel_agency.controller..*(..))")
    public void logBefore(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName(); // Отримуємо ім'я класу (контролера)
        String methodName = joinPoint.getSignature().getName(); // Отримуємо ім'я методу
        Object[] args = joinPoint.getArgs();
        logger.info("Controller: {}, Method: {} called with arguments: {}", className, methodName, args);
    }

    @AfterReturning("execution(* com.denys.travel_agency.controller..*(..))")
    public void logAfter(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        logger.info("Controller: {}, Method: {} executed successfully", className, methodName);
    }

    @AfterThrowing(pointcut = "execution(* com.denys.travel_agency.controller..*(..))", throwing = "exception")
    public void logException(JoinPoint joinPoint, Throwable exception) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        logger.error("Controller: {}, Method: {} threw an exception: {}", className, methodName, exception.getMessage());
    }
}
