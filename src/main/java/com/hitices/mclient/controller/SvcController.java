package com.hitices.mclient.controller;

import com.hitices.common.bean.MResponse;
import com.hitices.mclient.annotation.MFuncProcess;
import com.hitices.mclient.annotation.MInterfaceFunc;
import com.hitices.mclient.annotation.MNextService;
import com.hitices.mclient.base.Action;
import com.hitices.mclient.base.MControllerNode;
import com.hitices.mclient.base.MObject;
import com.hitices.mclient.client.DemoAClient;
import com.hitices.mclient.core.MServiceSkeleton;
import com.hitices.mclient.service.ServiceA;
import com.hitices.mclient.service.ServiceB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
public class SvcController extends MObject {

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Autowired
    ServiceA serviceA;

    @Autowired
    ServiceB serviceB;

    @Autowired
    private DemoAClient demoAClient;
//"ServiceA.aTestB","ServiceA.aTestC"
    @MFuncProcess(value = {"ServiceA.aTestA"})
    @PostMapping(value = "/test1")
    public MResponse test1(Action action,int a, boolean b){
        serviceA.aTestA(a,b);
//        System.out.println(demoAClient.test());
        System.out.println("After demoa");
        return MResponse.successResponse();
//        return MServiceSkeleton.getInstance().runProcess(action,a,b);
    }

    @PostMapping(value = "/testRemove")
    public MResponse testRemove(){
        RequestMappingInfo requestMappingInfo = RequestMappingInfo.paths("/test1").methods(RequestMethod.POST).build();
        System.out.println("testRemove");
        requestMappingHandlerMapping.unregisterMapping(requestMappingInfo);
        return MResponse.successResponse();
//        return MServiceSkeleton.getInstance().runProcess(action,a,b);
    }

    @RequestMapping("/testAdd")
    @ResponseBody
    public String testAdd() throws Exception {
        //构建 RequestMappingInfo对象
        RequestMappingInfo requestMappingInfo = RequestMappingInfo
                .paths("/test1")
                .methods(RequestMethod.POST)
                .build();
        // 获取目标处理类的
        Class<?> myController = SvcController.class.getClassLoader().loadClass("com.hitices.mclient.controller.SvcController");

        //最关键的一步，注册mapping对象
        requestMappingHandlerMapping.registerMapping(requestMappingInfo, this, myController.getDeclaredMethod("test1", Action.class,int.class,boolean.class));


        return "success";
    }

    @PostMapping(value = "/interface")
    public Set<MControllerNode> interfaceInfo(){
        return MServiceSkeleton.getInstance().getMInterface();
    }
}
