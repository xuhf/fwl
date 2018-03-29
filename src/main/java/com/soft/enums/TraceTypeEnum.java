package com.soft.enums;

/**
 * Created by xuhf on 2018/3/28.
 */
public enum TraceTypeEnum {

    ADDRESS("地址"),

    MOBILE("电话");

    private String desc;

    private TraceTypeEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return this.desc;
    }
}
