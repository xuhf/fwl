package com.soft;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by xuhf on 2018/3/28.
 */
public class ReadXlxs {

    @Test
    public void showExcel() throws Exception {
        List<String> results = Lists.newArrayList();
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File("C:\\Users\\xuhf\\Desktop\\fwl\\data\\haoduoshu.xls")));
        HSSFSheet sheet = null;
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {// 获取每个Sheet表
            sheet = workbook.getSheetAt(i);
            for (int j = 1; j < sheet.getLastRowNum() + 1; j++) {// getLastRowNum，获取最后一行的行标
                HSSFRow row = sheet.getRow(j);
                if (row != null) {
                    // getLastCellNum，是获取最后一个不为空的列是第几个
                    String name = "";
                    String id = "";
                    String phone = "";
                    String address = "";
                    List<Map<String, String>> traces = Lists.newArrayList();
                    StringBuffer subInfo = new StringBuffer();
                    boolean isEnd = false;
                    for (int k = 0; k <= row.getLastCellNum(); k++) {
                        // getCell 获取单元格数据
                        HSSFCell cell = row.getCell(k);
                        if (k == 0) {
                            name = cell.toString();
                        } else if (k == 1) {
                            id = cell.toString();
                        } else if (k == 2) {
                            phone = cell.toString();
                        } else if (k == 3) {
                            address = cell.toString();
                        } else {
                            if (cell != null) {
                                String info = cell.toString();
                                if (StringUtils.isNotBlank(info)) {
                                    String tempData[] = info.split(",");
                                    if (tempData.length != 5) {
                                        if (info.startsWith("[") && !info.endsWith("]")) {
                                            subInfo.append(info);
                                        }
                                        if (!info.startsWith("[") && info.endsWith("]")) {
                                            // 这个字符串结束了
                                            subInfo.append(info);
                                            isEnd = true;
                                        }
                                        if (isEnd) {
                                            info = subInfo.toString();
                                        }
                                    }
                                    if (tempData.length == 5 || isEnd) {
                                        String dataStr = info.replace("[", "").replace("]", "");
                                        String[] data = dataStr.split(",");
                                        Map<String, String> map = Maps.newHashMap();
                                        for (String item : data) {
                                            String[] prop = item.split("=");
                                            if (prop.length != 2) {
                                                map.put(prop[0].trim(), "");
                                            } else {
                                                map.put(prop[0].trim(), prop[1].trim());
                                            }

                                        }
                                        traces.add(map);
                                        subInfo.setLength(0);
                                        isEnd = false;
                                    }
                                }
                            }
                        }
                    }

                    if (traces.isEmpty()) {
                        List<String> d = Lists.newArrayList();
                        d.add(id);
                        d.add(phone);
                        d.add(name);
                        d.add(address);
                        results.add(StringUtils.join(d, "\t"));
                    } else {
                        System.out.println(traces.size());
                        for (Map<String, String> map : traces) {
                            List<String> d = Lists.newArrayList();
                            d.add(id);
                            d.add(phone);
                            d.add(name);
                            d.add(address);
                            d.add(map.get("mobile"));
                            d.add(map.get("name"));
                            d.add(map.get("addr"));
                            d.add(map.get("firstTime"));
                            d.add(map.get("lastTime"));
                            results.add(StringUtils.join(d, "\t"));
                        }
                    }
                }
            }
            FileUtils.writeLines(new File("C:\\Users\\xuhf\\Desktop\\fwl\\data\\second.txt"), "utf-8", results, false);
        }
    }

}
