package com.hitices.mclient.aop;

import com.hitices.mclient.base.Action;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Aspect
@Component
public class MFuncProcessAop {
    @Pointcut("@annotation(com.hitices.mclient.annotation.MFuncProcess)")
    public void interfaceFunction() {
    }

    @Around("interfaceFunction()")
    public Object interfaceFunctionCall(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] obj = joinPoint.getArgs();
        Action action = (Action) obj[0];
        action.setId(joinPoint.getSignature().getName());
        // 查看请求头部信息
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if (request != null) {
            if (request.getIntHeader("target") > 0){
                action.setTarget(request.getIntHeader("target"));
            }
        }
        return joinPoint.proceed(obj);
    }
}
