package com.hitices.mclient.controller;

import com.hitices.mclient.annotation.MFuncProcess;
import com.hitices.mclient.annotation.MInterfaceFunc;
import com.hitices.mclient.annotation.MNextService;
import com.hitices.mclient.base.MControllerNode;
import com.hitices.mclient.base.MObject;
import com.hitices.mclient.core.MServiceSkeleton;
import com.hitices.mclient.service.ServiceA;
import com.hitices.mclient.service.ServiceB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
public class SvcController extends MObject {

    @Autowired
    ServiceA serviceA;

    @Autowired
    ServiceB serviceB;

    @MFuncProcess(value = {"ServiceA.aTestB","ServiceA.aTestC"})
    @PostMapping(value = "/test1")
    public String test1(){
//        serviceB.bTestA();
//        serviceB.bTestB();
//        serviceB.bTestC();
        MServiceSkeleton.getInstance().runProcess("test1");
        return "test";
    }

    @MInterfaceFunc
    @PostMapping(value = "/test2")
    public String test2(){
        serviceA.aTestA();
        for (int i = 0; i<4;i++){
            serviceA.aTestB();
        }
        serviceA.aTestC();
        return "test";
    }

    @MInterfaceFunc
    @PostMapping(value = "/test3")
    public String test3(int i){
        serviceA.aTestA();
        if (i>1){
            serviceB.bTestB();
        }else {
            serviceA.aTestB();
        }
        return "test";
    }

    @PostMapping(value = "/interface")
    public Set<MControllerNode> interfaceInfo(){
        return MServiceSkeleton.getInstance().getMInterface();
    }
}
