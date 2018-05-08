package com.sinosoft.mobileservice.services;

import com.sinosoft.mobileservice.bean.BackUser;
import com.sinosoft.mobileservice.bean.MobileStatus;

import java.util.List;

public interface BackSysService extends BaseServices{
    public BackUser login(String name, String paswd);
    public BackUser adminfind(String id);

    public List<MobileStatus> listMobileStatus();

    public void updataMobileStatus(MobileStatus m);
}
