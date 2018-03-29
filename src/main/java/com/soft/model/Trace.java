package com.soft.model;

import com.soft.enums.TraceTypeEnum;

/**
 * Created by xuhf on 2018/3/28.
 */
public class Trace {

    private String mobile;

    private String status;

    private String type;

    private String name;

    // 通过技术手段找回的地址
    private String addr;

    private String firstTime;

    private String lastTime;

    private String postCode;

    private String normalAddress;

    // 这条数据是否有效
    private boolean valid = false;

    // 备注
    private String remark;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(String firstTime) {
        this.firstTime = firstTime;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getNormalAddress() {
        return normalAddress;
    }

    public void setNormalAddress(String normalAddress) {
        this.normalAddress = normalAddress;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
