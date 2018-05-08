package com.sinosoft.mobileservice.bean;

public class MobileStatus {
    private int id;
    private String userid  ;
    private String hardwareid   ;
    private String softwareid   ;
    private int status       ;
    private int logintype     ;
    private int stopordisable  ;
    private int uninstall      ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getHardwareid() {
        return hardwareid;
    }

    public void setHardwareid(String hardwareid) {
        this.hardwareid = hardwareid;
    }

    public String getSoftwareid() {
        return softwareid;
    }

    public void setSoftwareid(String softwareid) {
        this.softwareid = softwareid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getLogintype() {
        return logintype;
    }

    public void setLogintype(int logintype) {
        this.logintype = logintype;
    }

    public int getStopordisable() {
        return stopordisable;
    }

    public void setStopordisable(int stopordisable) {
        this.stopordisable = stopordisable;
    }

    public int getUninstall() {
        return uninstall;
    }

    public void setUninstall(int uninstall) {
        this.uninstall = uninstall;
    }
}
