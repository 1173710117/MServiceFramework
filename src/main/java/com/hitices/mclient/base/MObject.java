package com.hitices.mclient.base;


import com.hitices.mclient.annotation.MFuncProcess;
import com.hitices.mclient.annotation.MNextService;
import com.hitices.mclient.core.MServiceSkeleton;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

@Slf4j
public abstract class MObject {

    protected String id = null;

    protected MObject() {
        this.id = this.getClass().getSimpleName();
        if (this.getId()!=null){
            for (Method method : this.getClass().getDeclaredMethods()){
                if(method.getAnnotation(MFuncProcess.class) != null){
                    MServiceSkeleton.getInstance().registerApiProcess(method.getName(),
                            method.getAnnotation(MFuncProcess.class).value());
                    log.info(String.valueOf(method.getAnnotation(MFuncProcess.class).value()[0]));
                }
            }
        }
    }

    public String getId() {
        return id;
    }

    protected void setId(String id) {
        this.id = id;
    }

}
