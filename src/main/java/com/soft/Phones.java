package com.soft;

import com.google.common.collect.Sets;
import com.soft.enums.TraceTypeEnum;
import com.soft.model.Member;
import com.soft.model.Trace;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.List;
import java.util.Set;

/**
 * Created by xuhf on 2018/3/29.
 */
public class Phones {

    public static void main(String[] args) throws Exception {
        List<Member> members = Merger.getMembers();
        Set<String> phones = Sets.newHashSet();

        for (Member m : members) {
            for (Trace trace : m.getTraces()) {
                if (trace.getType().equalsIgnoreCase(TraceTypeEnum.ADDRESS.name())) {
                    phones.add(trace.getMobile());
                }
            }
        }
        FileUtils.writeLines(new File("C:\\Users\\xuhf\\Desktop\\fwl\\data\\result\\phones.txt"), "utf-8", phones, false);
    }
}
