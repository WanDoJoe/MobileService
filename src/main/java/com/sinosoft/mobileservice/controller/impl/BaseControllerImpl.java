package com.sinosoft.mobileservice.controller.impl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class BaseControllerImpl {
//    @RequestMapping(value = "/returnMapMsg", method = RequestMethod.POST)
    public  Map<String ,Object> returnMapMsg(String msg,Object object){
        Map<String ,Object> map=new HashMap<String,Object>();
        map.put(msg,object);
        map.put("code","1");
        map.put("message","请求成功");
        map.put("resultMsg","请求数据成功");

        return map;
    }


}
