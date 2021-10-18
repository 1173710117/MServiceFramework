package com.hitices.mclient.service;


import com.hitices.mclient.annotation.MNextService;
import com.hitices.mclient.annotation.MSvcFunc;
import com.hitices.mclient.base.MSvcObject;
import com.hitices.mclient.client.DemoAClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ServiceA extends MSvcObject {

    @Autowired
    DemoAClient demoAClient;

    @MSvcFunc
    public String aTestA(Integer a,Boolean b){
        log.info("this is ServiceA-testA {} ",a);
        log.info(demoAClient.test());
        return "this a result from a";
    }

    @MSvcFunc
    public String aTestB(String a){
        log.info("this is ServiceA-testB ,{}",a);
        return "this a result from b";
    }

    @MSvcFunc
    public void aTestC(String a){
        log.info("this is ServiceA-testC ,{}",a);
    }


}
