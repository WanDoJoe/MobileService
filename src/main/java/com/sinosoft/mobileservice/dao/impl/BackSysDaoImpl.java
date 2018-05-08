package com.sinosoft.mobileservice.dao.impl;

import com.sinosoft.mobileservice.bean.BackUser;
import com.sinosoft.mobileservice.bean.MobileStatus;
import com.sinosoft.mobileservice.dao.BackSysDao;
import com.sinosoft.mobileservice.mybatis.mapperInterface.BackUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BackSysDaoImpl implements BackSysDao {
    @Autowired
    BackUserMapper backUserMapper;

    @Override
    public BackUser login(String name, String paswd) {
        return backUserMapper.login(name,paswd);
    }

    @Override
    public BackUser adminfind(int id) {
        return backUserMapper.adminfind(id);
    }

    @Override
    public List<MobileStatus> listMobileStatus() {
        return backUserMapper.listMobileStatus();
    }

    @Override
    public void updataMobileStatus(MobileStatus m) {
        backUserMapper.updataMobileStatus(m);
    }


}
