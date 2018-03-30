package com.soft;

import com.google.common.collect.Lists;
import com.soft.enums.TraceTypeEnum;
import com.soft.model.Member;
import com.soft.model.Trace;
import org.apache.commons.lang.StringUtils;

import java.util.Collections;
import java.util.List;

/**
 * Created by xuhf on 2018/3/30.
 */
public class Sorter {

    public static void sort(List<Member> members) {
        for (Member m : members) {
            List<Trace> traces = m.getTraces();
            if (traces.size() == 0) {
                continue;
            }
            List<Trace> ms = Lists.newArrayList(); // 手机号码找回
            List<Trace> benrenAddressFinds = Lists.newArrayList(); // 本人地址找回
            List<Trace> benrenAddressFindNotimes = Lists.newArrayList(); // 本人地址找回,没有时间
            List<Trace> otherAddressFinds = Lists.newArrayList(); // 其他人地址找回
            List<Trace> otherAddressFindNotimes = Lists.newArrayList(); // 其他人地址找回,没有时间
            for (Trace t : traces) {
                if (t.getType().equals(TraceTypeEnum.MOBILE.name())) {
                    ms.add(t);
                } else {
                    if (m.getName().equals(t.getName())) {
                        if (StringUtils.isNotBlank(t.getLastTime())) {
                            benrenAddressFinds.add(t);
                        } else {
                            benrenAddressFindNotimes.add(t);
                        }
                    } else {
                        if (StringUtils.isNotBlank(t.getLastTime())) {
                            otherAddressFinds.add(t);
                        } else {
                            otherAddressFindNotimes.add(t);
                        }
                    }

                }
            }

            if (benrenAddressFinds.size() > 0) {
                Collections.sort(benrenAddressFinds);
            }

            if (otherAddressFinds.size() > 0) {
                Collections.sort(otherAddressFinds);
            }

            List<Trace> news = Lists.newArrayList();

            news.addAll(benrenAddressFinds);
            news.addAll(benrenAddressFindNotimes);
            news.addAll(otherAddressFinds);
            news.addAll(otherAddressFindNotimes);
            news.addAll(ms);
            m.setTraces(news);
        }
    }

}
