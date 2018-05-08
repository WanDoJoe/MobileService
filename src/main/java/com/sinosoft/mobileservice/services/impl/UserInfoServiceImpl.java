package com.sinosoft.mobileservice.services.impl;

import com.sinosoft.mobileservice.bean.shiro.UserInfo;
import com.sinosoft.mobileservice.dao.UserInfoDao;
import com.sinosoft.mobileservice.services.UserInfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserInfoDao userInfoDao;
    @Override
    public UserInfo findByUsernameAndPwd(String username,String Pwd)  {
        System.out.println("UserInfoServiceImpl.findByUsername()");
//        String md5Password = Md5Utils.md5(Md5Utils.md5((String) map.get("password")) + user1.getSalt());
        UsernamePasswordToken token = new UsernamePasswordToken(username, Pwd); // 获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        return userInfoDao.findByUsername(username);
    }

    @Override
    public UserInfo findByUsername(String username)  {
        System.out.println("UserInfoServiceImpl.findByUsername()");

        return userInfoDao.findByUsername(username);
    }
}