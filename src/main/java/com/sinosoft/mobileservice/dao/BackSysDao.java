package com.sinosoft.mobileservice.dao;

import com.sinosoft.mobileservice.bean.BackUser;
import com.sinosoft.mobileservice.bean.MobileStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BackSysDao extends BaseDao{

    public BackUser login(String name,String paswd);
    public BackUser adminfind(int id);

    public List<MobileStatus>   listMobileStatus();

    public void updataMobileStatus(MobileStatus m);
}
