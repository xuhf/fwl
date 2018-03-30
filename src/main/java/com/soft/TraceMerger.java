package com.soft;

import com.google.common.collect.Lists;
import com.soft.enums.TraceTypeEnum;
import com.soft.model.Member;
import com.soft.model.Trace;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * Created by xuhf on 2018/3/29.
 */
public class TraceMerger {

    public static void merge(List<Member> members) {
        // 过滤掉无用的数据
        for (Member m : members) {
            List<Trace> removes = Lists.newArrayList();
            List<Trace> traces = m.getTraces();
            if (traces.size() == 0) {
                continue;
            }
            for (Trace t : traces) {
                if (t.getType().equalsIgnoreCase(TraceTypeEnum.MOBILE.name()) && StringUtils.equals("0", t.getStatus())) {
                    removes.add(t);
                }
            }
            traces.removeAll(removes);
        }

        // 合并手机号，姓名，地址信息完全一致的数据
        for (Member m : members) {
            List<Trace> traces = m.getTraces();
            List<Trace> traces1 = filter(traces);
            m.setTraces(traces1);
        }
    }

    //
    private static List<Trace> filter(List<Trace> traces) {
        List<Trace> results = Lists.newArrayList();
        List<Trace> tempAddressResults = Lists.newArrayList(); //临时信息，将号码状态正常的放在最前面
        List<Trace> mobileResults = Lists.newArrayList();
        List<Trace> addressStatusRightResults = Lists.newArrayList();
        List<Trace> addressStatusErrorResults = Lists.newArrayList();
        // 先排一下序
        for (Trace t : traces) {
            if (t.getType().equals(TraceTypeEnum.MOBILE.name()) && t.getStatus().equals("1")) {
                mobileResults.add(t);
            }
            if (t.getType().equals(TraceTypeEnum.ADDRESS.name())) {
                if (t.getStatus().equals("1")) {
                    addressStatusRightResults.add(t);
                } else {
                    addressStatusErrorResults.add(t);
                }
            }
        }

        tempAddressResults.addAll(addressStatusRightResults);
        tempAddressResults.addAll(addressStatusErrorResults);

        for (Trace t : tempAddressResults) {
            String name = trim(t.getName());
            String mobile = trim(t.getMobile());
            String address = trim(t.getNormalAddress());
            boolean flag = true;
            for (Trace r : results) {
                String rn = trim(r.getName());
                String rm = trim(r.getMobile());
                String ra = trim(r.getNormalAddress());
                if (StringUtils.equals(name, rn)) {
                    if (StringUtils.equals(mobile, rm) && StringUtils.equals(address, ra)) {
                        flag = false;
                    } else if (t.getStatus().equals(r.getStatus()) && t.getStatus().equals("0") && StringUtils.equals(address, ra)) {
                        flag = false;
                    } else if (t.getStatus().equals("0") && StringUtils.equals(address, ra)) {
                        flag = false;
                    }
                }
            }
            if (flag) {
                results.add(t);
            }
        }
        results.addAll(mobileResults);
        return results;
    }

    private static String trim(String str) {
        if (StringUtils.isBlank(str)) {
            return "";
        }
        str = str.trim();
        return str;
    }
}
