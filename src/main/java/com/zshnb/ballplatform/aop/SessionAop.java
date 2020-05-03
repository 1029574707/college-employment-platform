package com.zshnb.ballplatform.aop;

import com.zshnb.ballplatform.common.Response;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * CreateDate：2020/5/3 <br/>
 * Author：WangHao <br/>
 * Description: 对请求进行session检测
 **/
@Aspect
@Component
public class SessionAop {

    @Around("@within(org.springframework.web.bind.annotation.RestController) && !@annotation(com.zshnb.ballplatform.anno.IgnoreSession)")
    public Object verifyToken(ProceedingJoinPoint joinPoint) throws Throwable {

        //get request information
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        HttpSession session = request.getSession();
        if (session.getAttribute("userId") == null || session.getAttribute("useType") == null) {
            return Response.error("用户信息获取失败，请重新登录");
        }

        return joinPoint.proceed();
    }
}
