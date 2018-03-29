package com.soft;

import com.google.common.collect.Lists;
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
public class ReadXlxs2 {

    @Test
    public void showExcel() throws Exception {
        List<String> results = Lists.newArrayList();
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File("C:\\Users\\xuhf\\Desktop\\fwl\\data\\zyresult2.xls")));
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
                    String findPhone = "";
                    String findPhoneStatus = "";
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
                        } else if (k == 4) {
                            findPhone = cell.toString();
                        } else if (k == 5) {
                            findPhoneStatus = cell.toString();
                        }
                    }
                    String status = "0";
                    if (findPhoneStatus.equalsIgnoreCase("正常")) {
                        status = "1";
                    }

                    List<String> d = Lists.newArrayList();
                    d.add(id);
                    d.add(phone);
                    d.add(name);
                    d.add(address);
                    d.add(findPhone);
                    d.add(status);
                    results.add(StringUtils.join(d, "\t"));
                }
            }
            FileUtils.writeLines(new File("C:\\Users\\xuhf\\Desktop\\fwl\\data\\zy.txt"), "utf-8", results, false);
        }
    }

}
