package com.sinosoft.mobileservice.services;

import com.sinosoft.mobileservice.bean.shiro.UserInfo;

public interface UserInfoService {

    /**通过username查找用户信息;*/
    public UserInfo findByUsernameAndPwd(String username,String password);


    public UserInfo findByUsername(String username);
}
