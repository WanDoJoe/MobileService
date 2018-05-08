package com.sinosoft.mobileservice.controller.impl.backmanagemengtsystem;


import com.sinosoft.mobileservice.bean.shiro.UserInfo;
import com.sinosoft.mobileservice.services.UserInfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.shiro.session.Session;


import java.util.HashMap;
import java.util.Map;

@Controller

public class LoginManageControllerImpl {
//    @RequestMapping({"/","/index"})
//    public String index(){
//        return"/index";
//    }
    @Autowired
    UserInfoService userInfoService;

//    @RequestMapping("/login/{shiro}/{username}")


//
//    @GET
//    @Path("/loginshiro/{shiro}/{username}")
//    @Produces(MediaType.APPLICATION_JSON)
    public  Map<String, Object> loginshiro( String shiro,
                             String username) throws Exception{
        Map<String, Object> map =new HashMap<String, Object> ();
        System.out.println("LoginManageControllerImpl.login()");
        // 登录失败从request中获取shiro处理的异常信息。
        // shiroLoginFailure:就是shiro异常类的全类名.
        String exception;// = (String) request.getAttribute("shiroLoginFailure");
        exception=shiro;
        System.out.println("exception=" + exception);
        String msg = "";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                System.out.println("UnknownAccountException -- > 账号不存在：");
                msg = "UnknownAccountException -- > 账号不存在：";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
                msg = "IncorrectCredentialsException -- > 密码不正确：";
            } else if ("kaptchaValidateFailed".equals(exception)) {
                System.out.println("kaptchaValidateFailed -- > 验证码错误");
                msg = "kaptchaValidateFailed -- > 验证码错误";
            } else {
                msg = "else >> "+exception;
                System.out.println("else -- >" + exception);
//                UserInfo usr = userInfoService.findByUsername(username);
//                map.put("user", usr);
            }
        }
        map.put("msg", msg);
        // 此方法不处理登录成功,由shiro进行处理
        return map;
    }

//    @GET
//    @Path("/login/{shiro}/{username}")
//    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> login( String shiro,
                                     String username  ) throws Exception{
        System.out.println("Controller.login()");
        Map<String, Object> map=new HashMap();
        String msg = "";
        // 登录失败从request中获取shiro处理的异常信息。
        // shiroLoginFailure:就是shiro异常类的全类名.
//        Session session = SecurityUtils.getSubject().getSession();
        System.out.println("Controller.login();shiro="+shiro+"---username="+username);

        //            UsernamePasswordToken token = new UsernamePasswordToken(username, shiro);
//            SecurityUtils.getSubject().login(token);
//            if(userInfoService.findByUsername(username)!=null){
//            map.put("status", 200);
//            map.put("message", "登录成功");
//            }else{
//                map.put("status", 500);
//                map.put("message", "登录失败");
//            }
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken();

        try {
            subject.login(token);
            map.put("status", 200);
            map.put("message", "登录成功");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            map.put("status", 500);
            map.put("message", "登录失败");
            map.put("e", e.getMessage());
        }

        map.put("msg", msg);
        // 此方法不处理登录成功,由shiro进行处理
        return map;
    }

//    @Path("/403")
//    @Produces(MediaType.APPLICATION_JSON)
    public Map<String ,String > unauthorizedRole(){
        System.out.println("------没有权限-------");
        Map<String ,String >map=new HashMap<>();
        map.put("key","403");
        return map;
    }

}
