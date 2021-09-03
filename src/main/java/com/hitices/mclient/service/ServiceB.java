package com.hitices.mclient.service;

import com.hitices.mclient.annotation.MSvcFunc;
import com.hitices.mclient.base.MSvcObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ServiceB extends MSvcObject {
    @MSvcFunc
    public void bTestA(){
        log.info("this is ServiceB-testA");
    }

    @MSvcFunc
    public void bTestB(){
        log.info("this is ServiceB-testB");
    }

    @MSvcFunc
    public void bTestC(){
        log.info("this is ServiceB-testC");
    }
}
