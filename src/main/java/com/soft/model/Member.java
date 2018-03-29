package com.soft.model;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by xuhf on 2018/3/28.
 */
public class Member {

    private String name;

    private String id;

    private String phone;

    private String address;

    // 追踪地址信息
    private List<Trace> traces = Lists.newArrayList();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Trace> getTraces() {
        return traces;
    }

    public void setTraces(List<Trace> traces) {
        this.traces = traces;
    }
}
