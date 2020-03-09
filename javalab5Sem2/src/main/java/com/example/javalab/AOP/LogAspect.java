package com.example.javalab.AOP;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
@Component
@Slf4j
public class LogAspect {
    @Pointcut("@annotation(LogAnnotation)")
    public void callAtViewController()
    {

    }
    @Before("callAtViewController()")
    public void beforeCallMethod(JoinPoint jp) {
        String args = Arrays.stream(jp.getArgs())
                .map(Object::toString)
                .collect(Collectors.joining(","));
        log.info("before " + jp.toString() + ", args=[" + args + "]");
    }
    @After("callAtViewController()")
    public void afterCallAt(JoinPoint jp) {
        log.info("after " + jp.toString());
    }
    @AfterThrowing("callAtViewController()")
    public void afterThrowingCallAt(JoinPoint jp) {
        log.info("afterThrowing " + jp.toString());
    }
}
