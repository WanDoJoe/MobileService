package com.sinosoft.mobileservice.dao.impl;

import com.sinosoft.mobileservice.bean.shiro.SysPermission;
import com.sinosoft.mobileservice.bean.shiro.SysRole;
import com.sinosoft.mobileservice.bean.shiro.UserInfo;
import com.sinosoft.mobileservice.dao.UserInfoDao;
import com.sinosoft.mobileservice.mybatis.mapperInterface.ShiroUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserInfoDaoImpl implements UserInfoDao {
    @Autowired
    ShiroUserMapper mapper;

    @Override
    public UserInfo findByUsername(String username) {

        UserInfo i = mapper.findByUsername(username);
//        List<SysPermission > permissinosList=new ArrayList<>();
//        SysPermission permission=new SysPermission();
//        SysPermission permission1=new SysPermission();
//        permission.setPermission("userInfo:add");
//        permission1.setPermission("userInfo:del");
//        permissinosList.add(permission);
//        permissinosList.add(permission1);
//
//        byte sa=13 ;
//        List<SysRole> roles=new ArrayList<>();
//        for (int i=0;i<=2;i++){
//                SysRole r=new SysRole();
//                r.setAvailable(true);
//            r.setDescription("sda");
//
//            r.setPermissions(permissinosList);
//            r.setRole("admin");
//            r.setUserInfos(null);
//            roles.add(r);
//        }
//        UserInfo i=new UserInfo();
//        i.setUsername("admin");
//        i.setName("admin");
//        i.setPassword("admin");
//        i.setSalt("s");
//        i.setState(sa);
//        i.setUid(123);
//        i.setRoleList(roles);

        return i;
    }

    @Override
    public <S extends UserInfo> S save(S s) {
        return null;
    }

    @Override
    public <S extends UserInfo> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<UserInfo> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<UserInfo> findAll() {
        return null;
    }

    @Override
    public Iterable<UserInfo> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(UserInfo userInfo) {

    }

    @Override
    public void deleteAll(Iterable<? extends UserInfo> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
