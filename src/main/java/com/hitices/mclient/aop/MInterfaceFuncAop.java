package com.hitices.mclient.aop;

import com.hitices.mclient.base.MControllerNode;
import com.hitices.mclient.core.MServiceSkeleton;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class MInterfaceFuncAop {
//    @Pointcut("@annotation(com.hitices.mclient.annotation.MInterfaceFunc)")
//    public void interfaceFunction() {
//    }
//
//    @Before("interfaceFunction()")
//    public void interfaceFunctionCall(JoinPoint joinPoint) {
//        if(!MServiceSkeleton.isHaveThread(joinPoint.getTarget().getClass().getName(),
//                joinPoint.getSignature().getName())){
//            MServiceSkeleton.threadData.put(Thread.currentThread(),new MControllerNode(
//                    joinPoint.getTarget().getClass().getName(),
//                    joinPoint.getSignature().getName()));
//        }
//
//    }
//
//    @After("interfaceFunction()")
//    public void interfaceFunctionCallEnd(JoinPoint joinPoint) {
//        if(MServiceSkeleton.threadData.containsKey(Thread.currentThread())){
//            MServiceSkeleton.updateInterface(MServiceSkeleton.threadData.get(Thread.currentThread()));
//            MServiceSkeleton.threadData.remove(Thread.currentThread());
//        }
//    }
}
