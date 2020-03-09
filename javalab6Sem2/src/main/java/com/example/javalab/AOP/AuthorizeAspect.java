package com.example.javalab.AOP;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.dom4j.rule.Mode;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
@Component
@Slf4j
public class AuthorizeAspect {
    @Pointcut("@annotation(AuthorizeAnnotation)")
    public void callAuthorizeAtViewController()
    {

    }
    private static HttpSession httpSession() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true);
    }
    @Around("callAuthorizeAtViewController()")
    public ModelAndView AroundCallMethod(ProceedingJoinPoint pjp) {
        ModelAndView modelAndView=new ModelAndView("index");
        try {
            HttpSession session=httpSession();
            if(session.getAttribute("role").equals("admin")) {
                modelAndView = (ModelAndView) pjp.proceed();
            }
            return modelAndView;
        }
        catch (Throwable e)
        {
            log.info(e.getMessage());
            return modelAndView;
        }
    }
}
