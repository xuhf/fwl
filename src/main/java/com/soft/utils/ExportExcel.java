package com.soft.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExportExcel {

	public static void main(String[] args) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		List<String[]> data = new ArrayList<String[]>();
		data.add(new String[] { "张三", "20", format.format(new Date()), "Yes", "180cm" });
		data.add(new String[] { "张三2", "22", format.format(new Date()), "No", "158cm" });
		String exportPath = "e:/export22.xls";
		File f = new File(exportPath);

		String[] headers = new String[] { "姓名", "年龄", "出生年月", "是否学生", "身高" };
		export(f, headers, data);
	}

	@SuppressWarnings("resource")
	public static void export(File file, String[] headers, List<String[]> data) throws Exception {
		OutputStream out = new FileOutputStream(file);
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet("sheet的名称");
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth(15);

		// 设置标题
		HSSFCellStyle titleStyle = workbook.createCellStyle();
		// 居中显示
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 标题字体
		HSSFFont titleFont = workbook.createFont();
		// 字体大小
		titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		titleStyle.setFont(titleFont);

		HSSFCellStyle contentStyle = workbook.createCellStyle();
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont contentFont = workbook.createFont();
		contentFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		contentStyle.setFont(contentFont);

		// 产生表格标题行
		HSSFRow row = sheet.createRow(0);
		for (int i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
			cell.setCellStyle(titleStyle);
		}

		int rowCount = 1;
		for (int i = 0; i < data.size(); i++, rowCount++) {
			HSSFRow dataRow = sheet.createRow(rowCount);
			String[] d = data.get(i);

			for (int j = 0; j < d.length; j++) {
				HSSFCell cell0 = dataRow.createCell(j);
				cell0.setCellValue(d[j]);
				cell0.setCellStyle(contentStyle);
			}
		}
		workbook.write(out);
	}

	public static void export2007(File file, String[] headers, List<String[]> data) throws Exception {
		OutputStream out = new FileOutputStream(file);
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet("sheet的名称");
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth(15);

		// 设置标题
		HSSFCellStyle titleStyle = workbook.createCellStyle();
		// 居中显示
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 标题字体
		HSSFFont titleFont = workbook.createFont();
		// 字体大小
		titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		titleStyle.setFont(titleFont);

		HSSFCellStyle contentStyle = workbook.createCellStyle();
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont contentFont = workbook.createFont();
		contentFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		contentStyle.setFont(contentFont);

		// 产生表格标题行
		HSSFRow row = sheet.createRow(0);
		for (int i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
			cell.setCellStyle(titleStyle);
		}

		int rowCount = 1;
		for (int i = 0; i < data.size(); i++, rowCount++) {
			HSSFRow dataRow = sheet.createRow(rowCount);
			String[] d = data.get(i);

			for (int j = 0; j < d.length; j++) {
				HSSFCell cell0 = dataRow.createCell(j);
				cell0.setCellValue(d[j]);
				cell0.setCellStyle(contentStyle);
			}
		}
		workbook.write(out);
	}
}
