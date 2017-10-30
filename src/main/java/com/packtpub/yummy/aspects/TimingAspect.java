package com.packtpub.yummy.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Slf4j
@Aspect
public class TimingAspect {

    @Pointcut("execution(public * *(..))")
    public void publicMethod() {
    }

    @Pointcut("within(com.packtpub..*Controller)")
    public void controllerMethods() {}

    @Around("publicMethod() && controllerMethods()")
    public Object measure2(ProceedingJoinPoint joinPoint) throws Throwable {
        return measure(joinPoint);
    }


    @Around("@annotation(LogExecution)")
    public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
        String taskName = joinPoint.getSignature().toString();
        StopWatch watch = new StopWatch();
        watch.start(taskName);
        try {
            return joinPoint.proceed();
        } finally {
            watch.stop();
            log.info("Call to {} ENDED\n{}", taskName, watch.prettyPrint());
        }
    }
}
