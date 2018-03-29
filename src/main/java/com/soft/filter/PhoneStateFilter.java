package com.soft.filter;

import com.soft.enums.TraceTypeEnum;
import com.soft.model.Trace;
import com.soft.utils.PhoneStateUtil;
import org.apache.commons.lang.StringUtils;

/**
 * Created by xuhf on 2018/3/29.
 */
public class PhoneStateFilter implements Filter {

    public void filter(Trace trace) {
        String type = trace.getType();
        if (StringUtils.equals(TraceTypeEnum.MOBILE.name(), type)) {
            return;
        }
        String phone = trace.getMobile();
        String state = PhoneStateUtil.getState(phone);
        trace.setStatus(state);
    }
}
