package com.hitices.mclient.base;

import com.github.andrewoma.dexx.collection.Set;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.HashSet;


@Getter
@Setter
public class MSvcNode {
    private String id;
    private Set<String> next;

    private MSvcNode(String id){
        this.id = id;
    }
}
