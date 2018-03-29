package com.soft;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.soft.enums.TraceTypeEnum;
import com.soft.model.Member;
import com.soft.model.Trace;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by xuhf on 2018/3/29.
 */
public class Merger {

    public static List<Member> getMembers() throws Exception {
        Map<String, Member> members = Maps.newHashMap();
        buildHdsSuccess(members);
        buildHdsFail(members);
        buildZy(members);
        List<Member> memberList = Lists.newArrayList();
        for (String k : members.keySet()) {
            memberList.add(members.get(k));
        }
        return memberList;
    }

    private static void buildHdsSuccess(Map<String, Member> members) throws Exception {
        File haoduoshuSuccessFile = new File("C:\\Users\\xuhf\\Desktop\\fwl\\data\\success.txt");
        List<String> lines = FileUtils.readLines(haoduoshuSuccessFile, "utf-8");
        for (String line : lines) {
            String[] data = line.split("\t");
            String id = data[0];
            String mobile = data[1];
            String name = data[2];
            String address = data[3];
            String tracePhone = data[4];
            String traceName = data[5];
            String traceAddress = data[6];
            String traceFirstTime = data[7];
            String traceLastTime = data[8];

            Member m = members.get(id + "_" + mobile);
            if (m == null) {
                m = new Member();
                m.setId(id);
                m.setName(name);
                m.setAddress(address);
                m.setPhone(mobile);
                members.put(id + "_" + mobile, m);
            }
            Trace trace = new Trace();
            trace.setMobile(tracePhone);
            trace.setStatus("");
            trace.setType(TraceTypeEnum.ADDRESS.name());
            trace.setName(traceName);
            trace.setAddr(traceAddress);
            trace.setFirstTime(traceFirstTime);
            trace.setLastTime(traceLastTime);
            m.getTraces().add(trace);
        }
    }

    private static void buildHdsFail(Map<String, Member> members) throws Exception {
        File haoduoshuFailFile = new File("C:\\Users\\xuhf\\Desktop\\fwl\\data\\fail.txt");
        List<String> lines = FileUtils.readLines(haoduoshuFailFile, "utf-8");
        for (String line : lines) {
            String[] data = line.split("\t");
            String id = data[0];
            String mobile = data[1];
            String name = data[2];
            String address = data[3];

            Member m = members.get(id + "_" + mobile);
            if (m == null) {
                m = new Member();
                m.setId(id);
                m.setName(name);
                m.setAddress(address);
                m.setPhone(mobile);
                members.put(id + "_" + mobile, m);
            }
        }
    }

    private static void buildZy(Map<String, Member> members) throws Exception {
        File zyFile = new File("C:\\Users\\xuhf\\Desktop\\fwl\\data\\zy.txt");
        List<String> zyLines = FileUtils.readLines(zyFile, "utf-8");
        for (String line : zyLines) {
            String[] data = line.split("\t");
            String id = data[0];
            String mobile = data[1];
            String name = data[2];
            String address = data[3];
            String tracePhone = data[4];
            String tracePhoneStatus = data[5];
            Member m = members.get(id + "_" + mobile);
            if (m == null) {
                System.out.println(id + "_" + mobile);
                m = new Member();
                m.setId(id);
                m.setName(name);
                m.setAddress(address);
                m.setPhone(mobile);
                members.put(id + "_" + mobile, m);
            }
            Trace trace = new Trace();
            trace.setMobile(tracePhone);
            trace.setStatus(tracePhoneStatus);
            trace.setType(TraceTypeEnum.MOBILE.name());
            m.getTraces().add(trace);
        }
    }

}
