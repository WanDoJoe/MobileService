package com.sinosoft.mobileservice.controller.impl.backmanagemengtsystem;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sinosoft.mobileservice.Utils.IntegerUtils;
import com.sinosoft.mobileservice.bean.BackUser;
import com.sinosoft.mobileservice.bean.MobileStatus;
import com.sinosoft.mobileservice.controller.BaseController;
import com.sinosoft.mobileservice.controller.impl.BaseControllerImpl;
import com.sinosoft.mobileservice.login.WebSecurityConfig;
import com.sinosoft.mobileservice.services.BackSysService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@Path("/backsys")
public class UserManageControllerImpl extends BaseControllerImpl {
    @Autowired
    BackSysService backSysService;


    @RequestMapping(value = "/index")
    public  Object my(){
        Map<String ,Object> map=new HashMap<>();
        BackUser adm = backSysService.adminfind("1");
        if(adm!=null) {
            map.put("bUser", adm);
            map.put("message","成功");
        }else{
            map.put("bUser", "");
            map.put("message","失败");

        }

        return map;
    }

//    @GET
//    @Path("/l/{name}/{pwd}")
//    @Produces({MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/l/{name}/{pwd}")
    public Map<String ,Object > loginweb(@PathVariable(value = "name") String name, @PathVariable(value = "pwd") String pwd){
        Map<String, Object> map = new HashMap<>();
        try {
            System.out.println("name==" + name + "=pwd==" + pwd);
            BackUser backUser = null;
            backUser = backSysService.login(name, pwd);
            if (backUser != null) {
                map.put("data", backUser);
                map.put("msg", "请求成功");
                map.put("resultCode", "1");
//            return returnMapMsg("data",backUser);
            } else {
//            return returnMapMsg("data",null);
                map.put("data", "");
                map.put("msg", "请求失败");
                map.put("resultCode", "0");
            }
        }catch(Exception e){
            map.put("data", "");
            map.put("msg", "请求失败:"+e.getLocalizedMessage());
            map.put("resultCode", "0");
        }
        return map;
    }

//    @GET
//    @Produces({MediaType.APPLICATION_JSON})
//    @Path("/mobilestatus/{id}/{pageNum}")
//    @RequestMapping(value = "/mobilestatus/{id}/{pageNum}")
        @RequestMapping(value = "/mobilestatus/{id}/{pageNum}")
    public Map<String,Object> mobilestatusAll(@PathVariable(value = "id") String id,@PathVariable(value = "pageNum") String pageNum){
//        public Map<String,Object> mobilestatusAll(){
//        String id="1";
//        String pageNum="2";
        System.out.println("id="+id+"--pageNum:"+pageNum);
        Map<String ,Object> map=new HashMap<>();
        if(IntegerUtils.isInteger(pageNum)) {
            PageHelper.startPage(Integer.valueOf(pageNum), 2);//pageNum当前页
        }else{
            map.put("data","");
            map.put("msg", "请求失败:请输入正确的页码");
            map.put("resultCode", "1");
            return map;
        }

        try{
            if(backSysService.adminfind(id)!=null||(id.equals("")||id.equals(null))) {
                List<MobileStatus> list = backSysService.listMobileStatus();
                PageInfo<MobileStatus> pageInfo=new PageInfo<>(list);
//                if (list != null) {
                    map.put("data", pageInfo);
                    map.put("msg", "请求成功");
                    map.put("resultCode", "1");
//                } else {
//                    map.put("data", pageInfo);
//                    map.put("msg", "请求失败");
//                    map.put("resultCode", "1");
//                }
            }else{
                map.put("data", "");
                map.put("msg", "请求失败:错误信息！请重新登录！");
                map.put("resultCode", "1");
            }
        }catch(Exception e){
            map.put("data","");
            map.put("msg", "请求失败:"+e.getLocalizedMessage());
            map.put("resultCode", "1");
        }
        return map;
    }

//    @POST
//    @Path("/updata")
//    @Produces({MediaType.APPLICATION_JSON})
//    @Consumes("application/x-www-form-urlencoded")  //接受的参数类型为表单信息
    public Map<String,Object> update( String params){
        System.out.println(params);
        Map<String,Object> map=new HashMap<>();
        JSONObject json=new JSONObject(params);
        MobileStatus mobileStatus=new MobileStatus();
        mobileStatus.setId(json.getInt("id"));
        mobileStatus.setUserid(json.getString("userid"));
        mobileStatus.setHardwareid(json.getString("hardwareid"));
        mobileStatus.setSoftwareid(json.getString("softwareid"));
        mobileStatus.setStatus(json.getInt("status"));
        mobileStatus.setLogintype(json.getInt("logintype"));
        mobileStatus.setStopordisable(json.getInt("stopordisable"));

        backSysService.updataMobileStatus(mobileStatus);

        map.put("data","");
        map.put("msg", "请求失败:");
        map.put("resultCode", "1");

        return map;
    }

    //权限管理


    /**
     * 用户查询.
     * @return
     */
//    @GET
//    @Path("/userList")
    @RequestMapping(value = "/userList")
    @RequiresPermissions("userInfo:view")//权限管理;
    public String userInfo(){
        return "userInfo";
    }

    /**
     * 用户添加;
     * @return
     */
//    @POST
//    @Path("/userAdd")
    @RequestMapping(value = "/userAdd")
    @RequiresPermissions("userInfo:add")//权限管理;
//    @RequiresPermissions("user:add")//权限管理;
    public String userInfoAdd(){
        return "userInfoAdd";
    }

    /**
     * 用户删除;
     * @return
     */
//    @DELETE
//    @Path("/userDel")
    @RequestMapping(value = "/userDel")
    @RequiresPermissions("userInfo:del")//权限管理;
//    @RequiresPermissions("user:del")//权限管理;
    public String userDel(){
        return "userInfoDel";
    }



    @RequestMapping(value = "/userPull")
//    @RequiresPermissions("userInfo:del")//权限管理;
    @RequiresPermissions("userInfo:pull")//权限管理;
    public String userPull() throws Exception{
        return "userPull";
    }

}
