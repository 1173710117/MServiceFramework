package com.hitices.mclient.base;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Array;
import java.util.*;

@Getter
@Setter
public class MControllerNode {
    private String ControllerName;
    private String InterfaceName;
    private String CurrentSvc;
    private Map<String, HashSet<String>> SvcMap;

    public MControllerNode(String ControllerName, String InterfaceName){
        this.ControllerName = ControllerName;
        this.InterfaceName = InterfaceName;
        this.SvcMap = new HashMap<>();
    }

    public void putSvc(String svc){
        if (!this.SvcMap.containsKey(svc)){
            this.SvcMap.put(svc,new HashSet<>());
        }
    }

    public void addSvc(String svc){
        if(!this.getSvcMap().get(CurrentSvc).contains(svc)){
            HashSet<String> temp = this.getSvcMap().get(CurrentSvc);
            temp.add(svc);
            this.getSvcMap().put(CurrentSvc,temp);
        }
    }

    public MControllerNode combine(MControllerNode node){
        if (this.equals(node)){
            for (Map.Entry<String, HashSet<String>> entry : node.getSvcMap().entrySet()){
                if (this.SvcMap.containsKey(entry.getKey())){
                    entry.getValue().addAll(this.getSvcMap().get(entry.getKey()));
                    this.getSvcMap().put(entry.getKey(),entry.getValue());
                }else {
                    this.SvcMap.put(entry.getKey(),entry.getValue());
                }
            }
        }
        return this;
    }

    @Override
    public String toString() {
        return "MControllerNode{" +
                "ControllerName='" + ControllerName + '\'' +
                ", InterfaceName='" + InterfaceName + '\'' +
                ", CurrentSvc='" + CurrentSvc + '\'' +
                ", SvcMap=" + SvcMap +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MControllerNode that = (MControllerNode) o;
        return Objects.equals(ControllerName, that.ControllerName) && Objects.equals(InterfaceName, that.InterfaceName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ControllerName, InterfaceName);
    }
}
