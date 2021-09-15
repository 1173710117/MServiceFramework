package com.hitices.mclient.base;

import com.hitices.mclient.annotation.MNextService;
import com.hitices.mclient.core.MServiceSkeleton;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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

    public Object call(String methodName, Object[] args){
        try {
            Class[] classes = new Class[args.length];
            for (int i = 0; i<args.length; i++){
                classes[i] = args[i].getClass();
            }
            Method method = this.getClass().getMethod(methodName,classes);
            return method.invoke(this,args);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
