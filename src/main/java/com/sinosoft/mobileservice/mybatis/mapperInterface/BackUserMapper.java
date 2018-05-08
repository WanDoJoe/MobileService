package com.sinosoft.mobileservice.mybatis.mapperInterface;

import com.sinosoft.mobileservice.bean.BackUser;
import com.sinosoft.mobileservice.bean.MobileStatus;

import java.util.List;

public interface BackUserMapper {
    public BackUser login(String loginname,String pswd);
    public BackUser adminfind(int id);

    public List<MobileStatus> listMobileStatus();

    public void updataMobileStatus(MobileStatus m);
}
