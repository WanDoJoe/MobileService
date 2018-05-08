package com.sinosoft.mobileservice.dao;

import com.sinosoft.mobileservice.bean.shiro.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserInfoDao extends CrudRepository<UserInfo,Long> {
    /**通过username查找用户信息;*/
    public UserInfo findByUsername(String username);
}