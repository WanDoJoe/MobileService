package com.sinosoft.mobileservice.services.impl;

import com.sinosoft.mobileservice.Utils.IntegerUtils;
import com.sinosoft.mobileservice.bean.BackUser;
import com.sinosoft.mobileservice.bean.MobileStatus;
import com.sinosoft.mobileservice.dao.BackSysDao;
import com.sinosoft.mobileservice.services.BackSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BackSysServiceImpl implements BackSysService {
    @Autowired
    BackSysDao sysDao;

    @Override
    public BackUser login(String name, String paswd) {

        return sysDao.login(name,paswd);
    }

    @Override
    public BackUser adminfind(String id) {
        if(IntegerUtils.isInteger(id)){
            return sysDao.adminfind(Integer.valueOf(id));
        }else{
            return null;
        }

    }

    @Override
    public List<MobileStatus> listMobileStatus() {
        return sysDao.listMobileStatus();
    }

    @Override
    public void updataMobileStatus(MobileStatus m) {
            sysDao.updataMobileStatus(m);
    }
}
