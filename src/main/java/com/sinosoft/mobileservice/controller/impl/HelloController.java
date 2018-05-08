package com.sinosoft.mobileservice.controller.impl;


import com.sinosoft.mobileservice.services.UserInfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;

//@Path("/")
@RestController
public class HelloController{
    @Autowired
    UserInfoService service;
//    @RequestParam //获取url中的变量的值
//    @GET
//    @Path("/login")
//    @Produces(MediaType.APPLICATION_JSON)
    @GetMapping(value = "/login")// method = RequestMethod.GET)
//    @ResponseBody
    public Map<String ,Object> login(@RequestParam(value = "username",required = false)String username,@RequestParam(value = "password",required = false)String password){
//    public Map<String ,Object> login(){
            System.out.println("username="+username+";password="+password);
        Map<String ,Object> map=new HashMap<>();

        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token=new UsernamePasswordToken(username,password);
            subject.login(token);
            map.put("data",service.findByUsername(username));
            map.put("msg","success");
//            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg",e.getMessage());

        }

        return map;
    }
//    @GET
//    @Path("/say/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(value="/say/{id}")
    public String say(@PathVariable(value="id")String id){

        return"my Spring Boot!! "+id;
    }

//    @GET
//    @Path(value = "/unauth")
//    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(value="/unauth")
    public Object unauth() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", "1000000");
        map.put("msg", "未授权");
        return map;
    }
    @RequestMapping(value="/403")
    public Map<String ,String > unauthorizedRole(){
        System.out.println("------没有权限-------");
        Map<String ,String >map=new HashMap<>();
        map.put("key","403");
        return map;
    }

//    @RequestMapping(value="/error")
//    public Map<String ,String > error(){
//        System.out.println("------没有权限-------");
//        Map<String ,String >map=new HashMap<>();
//        map.put("key","error");
//        return map;
//    }

}
