package com.hitices.mclient.aop;


import com.hitices.mclient.base.MControllerNode;
import com.hitices.mclient.core.MServiceSkeleton;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class MSvcFuncAop {
    @Pointcut("@annotation(com.hitices.mclient.annotation.MSvcFunc)")
    public void svcFunction() {
    }

    @Before("svcFunction()")
    public void svcFunctionCall(JoinPoint joinPoint) {
    }

    @After("svcFunction()")
    public void svcFunctionCallEnd(JoinPoint joinPoint) {
        MControllerNode mControllerNode = MServiceSkeleton.threadData.get(Thread.currentThread());
        if (mControllerNode.getCurrentSvc() == null){
            mControllerNode.setCurrentSvc(joinPoint.getSignature().getName());
            mControllerNode.putSvc(joinPoint.getSignature().getName());
        }else {
            mControllerNode.addSvc(joinPoint.getSignature().getName());
            mControllerNode.setCurrentSvc(joinPoint.getSignature().getName());
            mControllerNode.putSvc(joinPoint.getSignature().getName());
        }
        System.out.println(mControllerNode.toString());
    }

}
