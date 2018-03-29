package com.soft.filter;

/**
 * Created by xuhf on 2018/3/28.
 */

import com.google.common.collect.Lists;
import com.soft.enums.TraceTypeEnum;
import com.soft.model.Trace;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * 关键词过滤
 */
public class KeywordFilter implements Filter {

    private static List<String> keywords = Lists.newArrayList();

    static {
        keywords.add("\\((.*)\\)");
        keywords.add("\\（(.*)\\）");
        keywords.add("。");
        keywords.add("\\*");
        keywords.add("\\d{11}"); // 电话
        keywords.add("\\d{1,}点");
        keywords.add("\\d{6}");
        keywords.add("\\\\r");
        keywords.add("\\\\n");
        keywords.add("\\\\t");
        keywords.add("未央区凤城一路西段C1区邮政物流集散中心天音仓库");
        keywords.add("农村淘宝仓库");
        keywords.add("淘宝仓库");
        keywords.add("快递仓库");
        keywords.add("固定电话");
        keywords.add("下午一点");
        keywords.add("手机号");
        keywords.add("快递员");
        keywords.add("代收点");
        keywords.add("电商圆");
        keywords.add("电商园");
        keywords.add("门卫");
        keywords.add("代收");
        keywords.add("农村");
        keywords.add("淘宝");
        keywords.add("圆通");
        keywords.add("中通");
        keywords.add("申通");
        keywords.add("韵达");
        keywords.add("汇通");
        keywords.add("快递");
        keywords.add("顺丰");
        keywords.add("本人");
        keywords.add("签收");
        keywords.add("网点");
        keywords.add("请先");
        keywords.add("电话");
        keywords.add("联系");
        keywords.add("麻烦");
        keywords.add("早上");
        keywords.add("中午");
        keywords.add("晚上");
        keywords.add("以后");
        keywords.add("送货");
        keywords.add("周一");
        keywords.add("周二");
        keywords.add("周三");
        keywords.add("周四");
        keywords.add("周五");
        keywords.add("周六");
        keywords.add("周日");
        keywords.add("或者");
        keywords.add("其他");
        keywords.add("时候");
        keywords.add("即可");
        keywords.add("只能");
        keywords.add("没人");
        keywords.add("在家");
        keywords.add("请");
        keywords.add("送");
        keywords.add("收");
        keywords.add("再");
    }

    public void filter(Trace trace) {
        String type = trace.getType();
        if (StringUtils.equals(TraceTypeEnum.MOBILE.name(), type)) {
            return;
        }
        String address = trace.getNormalAddress();
        if (StringUtils.isBlank(address)) {
            address = trace.getAddr();
        }
        for (String keyword : keywords) {
            address = address.replaceAll(keyword, "");
        }
        trace.setNormalAddress(address);
    }
}
