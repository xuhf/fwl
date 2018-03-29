package com.soft;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.soft.enums.TraceTypeEnum;
import com.soft.model.Member;
import com.soft.model.Trace;
import com.soft.utils.ExportExcel;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.util.List;

/**
 * Created by xuhf on 2018/3/29.
 */
public class Writer {

    public static void write(List<Member> members) {
        try {
            writeExcel(members, "\t");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void writeExcel(List<Member> members, String limiter) throws Exception {
        List<String> results = Lists.newArrayList();
        String[] headers = new String[]{"身份证", "电话", "姓名", "地址",
                "找回途径", "找回电话", "找回姓名", "找回地址",
                "找回地址（过滤后）", "找回邮编"};
        for (Member m : members) {
            List<String> baseData = Lists.newArrayList();
            baseData.add(m.getId());
            baseData.add(m.getPhone());
            baseData.add(m.getName());
            baseData.add(m.getAddress());
            baseData.add("");
            baseData.add("");
            baseData.add("");
            baseData.add("");
            baseData.add("");
            baseData.add("");
            results.add(Joiner.on(limiter).join(baseData));
            for (Trace t : m.getTraces()) {
                List<String> data = Lists.newArrayList();
                data.add("");
                data.add("");
                data.add("");
                data.add("");

                if (StringUtils.equals(TraceTypeEnum.MOBILE.name(), t.getType())) {
                    if (StringUtils.isBlank(t.getStatus()) || StringUtils.equals("0", t.getStatus())) {
                        // 号码找回，如果异常就不要了
                        continue;
                    }
                    data.add("号码找回");
                    data.add(t.getMobile());
                    data.add("");
                    data.add("");
                    data.add("");
                    data.add("");
                } else {
                    data.add("地址找回");
                    if (StringUtils.isBlank(t.getStatus()) || StringUtils.equals("0", t.getStatus())) {
                        // 电话号码就不填写
                        data.add("");
                    } else {
                        data.add(t.getMobile());
                    }

                    data.add(t.getName() == null ? "" : t.getName());
                    data.add(t.getAddr() == null ? "" : t.getAddr());
                    data.add(t.getNormalAddress() == null ? "" : t.getNormalAddress());
                    data.add(t.getPostCode() == null ? "" : t.getPostCode());
                }
                results.add(Joiner.on(limiter).join(data));
            }
            results.add(getSpace(limiter));
        }

        List<String[]> excelData = Lists.newArrayList();
        for (String line : results) {
            excelData.add(line.split(limiter));
        }

        File exportFile = new File("C:\\Users\\xuhf\\Desktop\\fwl\\data\\result\\找回信息.xls");
        ExportExcel.export(exportFile, headers, excelData);
    }

    private static void writeCSV(List<Member> members, String limiter, String name) throws Exception {
        List<String> results = Lists.newArrayList();
        for (Member m : members) {
            List<String> baseData = Lists.newArrayList();
            baseData.add(m.getId());
            baseData.add(m.getPhone());
            baseData.add(m.getName());
            baseData.add(m.getAddress());
            baseData.add("");
            baseData.add("");
            baseData.add("");
            baseData.add("");
            baseData.add("");
            baseData.add("");
            results.add(Joiner.on(limiter).join(baseData));
            for (Trace t : m.getTraces()) {
                List<String> data = Lists.newArrayList();
                data.add("");
                data.add("");
                data.add("");
                data.add("");
                if (StringUtils.equals(TraceTypeEnum.MOBILE.name(), t.getType())) {
                    data.add(t.getType());
                    data.add(t.getMobile());
                    data.add(t.getStatus() == null ? "" : t.getStatus());
                    data.add("");
                    data.add("");
                    data.add("");
                    data.add("");
                } else {
                    data.add(t.getType());
                    data.add(t.getMobile());
                    data.add(t.getStatus() == null ? "" : t.getStatus());
                    data.add(t.getName() == null ? "" : t.getName());
                    data.add(t.getAddr() == null ? "" : t.getAddr());
                    data.add(t.getNormalAddress() == null ? "" : t.getNormalAddress());
                    data.add(t.getPostCode() == null ? "" : t.getPostCode());
                }
                results.add(Joiner.on(limiter).join(data));
            }
            results.add(getSpace(limiter));
        }
        FileUtils.writeLines(new File("C:\\Users\\xuhf\\Desktop\\fwl\\data\\result\\" + name), "utf-8", results, false);
    }

    private static String getSpace(String limiter) {
        List<String> baseData = Lists.newArrayList();
        baseData.add("");
        baseData.add("");
        baseData.add("");
        baseData.add("");
        baseData.add("");
        baseData.add("");
        baseData.add("");
        baseData.add("");
        baseData.add("");
        baseData.add("");
        return Joiner.on(limiter).join(baseData);
    }

}
