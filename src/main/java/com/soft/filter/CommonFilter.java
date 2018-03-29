package com.soft.filter;

import com.soft.enums.TraceTypeEnum;
import com.soft.model.Trace;
import org.apache.commons.lang.StringUtils;

/**
 * Created by xuhf on 2018/3/29.
 */
public class CommonFilter implements Filter {

    public void filter(Trace trace) {
        String type = trace.getType();
        if (StringUtils.equals(TraceTypeEnum.MOBILE.name(), type)) {
            return;
        }
        String address = trace.getNormalAddress();
        if (StringUtils.isBlank(address)) {
            address = trace.getAddr();
        }
        address = address.replaceAll("（", "(").replaceAll("）", ")");
        address = address.replaceAll("。", ".").replaceAll("，", ",");
        address = address.replaceAll("？", "?");
        address = replaceLastPoint(address);
        trace.setNormalAddress(address);
    }

    private String replaceLastPoint(String address) {
        if (StringUtils.isBlank(address)) {
            return address;
        }
        if (address.endsWith(".")) {
            return replaceLastPoint(address.substring(0, address.length() - 1));
        } else {
            return address;
        }
    }
}
