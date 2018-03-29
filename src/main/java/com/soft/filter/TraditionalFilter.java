package com.soft.filter;

/**
 * Created by xuhf on 2018/3/28.
 */

import com.soft.enums.TraceTypeEnum;
import com.soft.model.Trace;
import com.soft.utils.ZHConverter;
import org.apache.commons.lang.StringUtils;

/**
 * 简体繁体转换
 */
public class TraditionalFilter implements Filter {

    public void filter(Trace trace) {
        String type = trace.getType();
        if (StringUtils.equals(TraceTypeEnum.ADDRESS.name(), type)) {
            String address = trace.getNormalAddress();
            if (StringUtils.isBlank(address)) {
                address = trace.getAddr();
            }
            ZHConverter converter = ZHConverter.getInstance(ZHConverter.SIMPLIFIED);
            String simplifiedAddress = converter.convert(address);
            trace.setNormalAddress(simplifiedAddress);
        }
    }
}
