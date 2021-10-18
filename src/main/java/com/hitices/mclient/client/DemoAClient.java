package com.hitices.mclient.client;


import com.hitices.mclient.base.MSvcObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 16382
 */
@FeignClient(name = "DemoA")
public interface DemoAClient{
    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public String test();
}
