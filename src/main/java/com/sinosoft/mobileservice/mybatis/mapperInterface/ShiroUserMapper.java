package com.sinosoft.mobileservice.mybatis.mapperInterface;

import com.sinosoft.mobileservice.bean.shiro.UserInfo;

public interface ShiroUserMapper {
    /**通过username查找用户信息;*/
    public UserInfo findByUsername(String username);
}
