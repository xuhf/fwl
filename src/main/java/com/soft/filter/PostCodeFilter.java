package com.soft.filter;

/**
 * Created by xuhf on 2018/3/28.
 */

import com.soft.enums.TraceTypeEnum;
import com.soft.model.Trace;
import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 邮政编码提取
 */
public class PostCodeFilter implements Filter {

    private static Pattern pattern = Pattern.compile("(\\(([0-9]{6})\\))");

    public void filter(Trace trace) {
        String type = trace.getType();
        if (StringUtils.equals(TraceTypeEnum.MOBILE.name(), type)) {
            return;
        }
        String address = trace.getNormalAddress();
        if (StringUtils.isBlank(address)) {
            address = trace.getAddr();
        }
        Matcher matcher = pattern.matcher(address);
        if (matcher.find()) {
            String str = matcher.group(1);
            String postCode = matcher.group(2);
            address = address.replace(str, "");
            trace.setNormalAddress(address);
            trace.setPostCode(postCode);
        }
    }
}
