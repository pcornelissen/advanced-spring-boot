package com.packtpub.yummy.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Slf4j
@Aspect
public class TimingAspect {

    @Autowired
    CounterService counterService;
    @Autowired
    GaugeService gaugeService;

    @Pointcut("execution(public * *(..))")
    public void publicMethod() {
    }

    @Pointcut("within(com.packtpub..*Controller)")
    public void controllerMethods() {}

    @Around("publicMethod() && controllerMethods()")
    public Object measure2(ProceedingJoinPoint joinPoint) throws Throwable {
        return measure(joinPoint);
    }


    //@Around("@annotation(LogExecution)")
    public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
        String taskName = joinPoint.getSignature().toString();
        String name = joinPoint.getSignature().getName();
        StopWatch watch = new StopWatch();
        watch.start(taskName);
        try {
            Thread.sleep((long)(Math.random()*300));
            return joinPoint.proceed();
        } finally {
            watch.stop();
            counterService.increment(name+".callcount");
            gaugeService.submit("gauge."+name,watch.getTotalTimeMillis());
            gaugeService.submit("timer."+name,watch.getTotalTimeMillis());
            gaugeService.submit("histogram."+name,watch.getTotalTimeMillis());
            log.info("Call to {} ENDED\n{}", taskName, watch.prettyPrint());
        }
    }
}
