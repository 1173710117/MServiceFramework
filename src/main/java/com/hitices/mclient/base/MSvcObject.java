package com.hitices.mclient.base;

import com.hitices.mclient.annotation.MNextService;
import com.hitices.mclient.core.MServiceSkeleton;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public abstract class MSvcObject {

    protected String id = null;

    protected MSvcObject(){
        this.id = this.getClass().getSimpleName();
        if (this.getId()!=null){
            for (Method method : this.getClass().getDeclaredMethods()){
                if(method.getAnnotation(MNextService.class) != null){
                    MServiceSkeleton.getInstance().registerApi(this,method.getName(),
                            method.getAnnotation(MNextService.class).value());
                    log.info(String.valueOf(method.getAnnotation(MNextService.class).value()[0]));
                }else {
                    MServiceSkeleton.getInstance().registerApi(this,method.getName(),new String[]{});
                }
            }
            MServiceSkeleton.getInstance().registerService(this);
            log.info(this.getId() + " created");
        }

    }

    public String getId() {
        return id;
    }

    public void call(String methodName,Object[] args){
        try {
            Method method = this.getClass().getMethod(methodName);
            method.invoke(this,new Object[]{});
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
