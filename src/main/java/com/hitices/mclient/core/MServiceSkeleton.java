package com.hitices.mclient.core;


import com.hitices.common.bean.MResponse;
import com.hitices.mclient.base.Action;
import com.hitices.mclient.base.MControllerNode;
import com.hitices.mclient.base.MSvcNode;
import com.hitices.mclient.base.MSvcObject;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class MServiceSkeleton {

    private static MServiceSkeleton instance;

    @Getter
    private Set<MControllerNode> mInterface;

    @Getter
    private Map<String, MSvcObject> mSvcObjectMap;

    @Getter
    private Map<String, Map<String,String[]>> mSvcApiMap;

    //controller
    @Getter
    private Map<String, String[]> mApiProcess;

    private MServiceSkeleton(){
        this.mInterface = Collections.synchronizedSet(new HashSet<MControllerNode>());
        this.mSvcObjectMap = new HashMap<>();
        this.mSvcApiMap = new HashMap<>();
        this.mApiProcess = new HashMap<>();
    }

    public static MServiceSkeleton getInstance(){
        if (instance == null){
            synchronized (MServiceSkeleton.class) {
                instance = new MServiceSkeleton();
            }
        }
        return instance;
    }

    //service

    public void registerService(MSvcObject mSvcObject){
        if (this.mSvcObjectMap.containsKey(mSvcObject.getId())){
            log.warn("Service " + mSvcObject.getId() + " has been registered before !!!");
        } else {
            this.mSvcObjectMap.put(mSvcObject.getId(),mSvcObject);
        }
    }

    public void registerApi(MSvcObject mSvcObject,String method, String[] nextMethod){
        if (!this.mSvcApiMap.containsKey(mSvcObject.getId())){
            this.mSvcApiMap.put(mSvcObject.getId(), new HashMap<>());
        } else {
            this.mSvcApiMap.get(mSvcObject.getId()).put(method,nextMethod);
        }
    }

    //controller

//    public static boolean isHaveThread(String ControllerName,String InterfaceName){
//        for (MControllerNode mControllerNode : threadData.values()){
//            if(mControllerNode.getControllerName().equals(ControllerName) &&
//            mControllerNode.getInterfaceName().equals(InterfaceName)){
//                return true;
//            }
//        }
//        return false;
//    }

    public static void updateInterface(MControllerNode mControllerNode){
        if (MServiceSkeleton.getInstance().getMInterface().contains(mControllerNode)){
            for(MControllerNode mControllerNode1 : MServiceSkeleton.getInstance().getMInterface()){
                if (mControllerNode1.equals(mControllerNode)){
                    mControllerNode1.combine(mControllerNode);
                    break;
                }
            }
        }else {
            MServiceSkeleton.getInstance().getMInterface().add(mControllerNode);
        }

    }

    public void registerApiProcess(String method, String[] process){
       this.mApiProcess.put(method, process);
    }

    /**
     * 完成业务逻辑的调用,输入一个指令，自动执行
     */
    public MResponse runProcess(Action action, Object... args){
        MResponse response = new MResponse();
        String[] process = mApiProcess.get(action.getId());
        int size = action.getTarget() <= 0 || action.getTarget() > process.length? process.length : action.getTarget();
        log.info(String.valueOf(size));
        if (process!=null){
            for (int i=0; i<size; i++){
                //todo:参数匹配
                Object temp = mSvcObjectMap.get(process[i].split("\\.")[0]).call(process[i].split("\\.")[1], args);
                args = new Object[]{temp};
            }
        }
        return response;
    }



}
