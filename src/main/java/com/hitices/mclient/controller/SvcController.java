package com.hitices.mclient.controller;

import com.hitices.common.bean.MResponse;
import com.hitices.mclient.annotation.MFuncProcess;
import com.hitices.mclient.annotation.MInterfaceFunc;
import com.hitices.mclient.annotation.MNextService;
import com.hitices.mclient.base.Action;
import com.hitices.mclient.base.MControllerNode;
import com.hitices.mclient.base.MObject;
import com.hitices.mclient.core.MServiceSkeleton;
import com.hitices.mclient.service.ServiceA;
import com.hitices.mclient.service.ServiceB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
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
//"ServiceA.aTestB","ServiceA.aTestC"
    @MFuncProcess(value = {"ServiceA.aTestA"})
    @PostMapping(value = "/test1")
    public MResponse test1(Action action,int a, boolean b){
        System.out.println(a);
        return MServiceSkeleton.getInstance().runProcess(action,a,b);
    }

    @PostMapping(value = "/interface")
    public Set<MControllerNode> interfaceInfo(){
        return MServiceSkeleton.getInstance().getMInterface();
    }
}
