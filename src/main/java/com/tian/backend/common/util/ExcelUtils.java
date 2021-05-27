package com.tian.backend.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * EXCEL工具类
 * @author muyang.tian
 * @date 2021/5/24 15:41
 */
@Slf4j
public class ExcelUtils {

    /**
     * 导出
     * @param sheetName 表格名
     * @param headerName 列名
     * @param headerKey 列对应的对象的属性名
     * @param dataList 需要导出的数据
     * @return HSSFWorkbook
     */
    public static HSSFWorkbook exportExcel(String sheetName, String[] headerName, String[] headerKey, List<?> dataList) {
        HSSFWorkbook wb = new HSSFWorkbook();
        assert headerKey != null;
        if (dataList.isEmpty()){
            throw new RuntimeException("无数据导出!");
        }
        //Excel名字
        HSSFSheet sheet = "".equals(sheetName) ? wb.createSheet("sheet1") : wb.createSheet(sheetName);
        //靠左放置
        HSSFCellStyle headerStyle = wb.createCellStyle();
        headerStyle.setAlignment(HorizontalAlignment.LEFT);
        //设置字体
        HSSFFont font = wb.createFont();
        //是否加粗
        font.setBold(true);
        //字体大小
        font.setFontHeightInPoints((short) 14);
        headerStyle.setFont(font);
        //填充列名
        HSSFRow headerRow = sheet.createRow(0);
        for (int i = 0; i < headerName.length; i++) {
            HSSFCell headerCell = headerRow.createCell(i);
            headerCell.setCellValue(headerName[i]);
            headerCell.setCellStyle(headerStyle);
        }
        //小数格式
        HSSFCellStyle decimalStyle = wb.createCellStyle();
        decimalStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00_);(#,##0.00)"));
        //文本格式
        HSSFCellStyle contextStyle = wb.createCellStyle();
        contextStyle.setDataFormat(wb.createDataFormat().getFormat("text"));
        List<Field> fields = Arrays.asList(dataList.get(0).getClass().getDeclaredFields());
        //把headerKey和变量对应一下
        Map<String, Field> fieldMap = fields.stream().filter(field -> Arrays.asList(headerKey).contains(field.getName()))
                .collect(Collectors.toMap(Field::getName, field -> field));
        for (Object a : dataList) {
            HSSFRow dataRow = sheet.createRow(dataList.indexOf(a)+1);
            //按照headerKey的顺序插值,后面根据key插入data中对应的值
            for (int j = 0; j < headerKey.length; j++) {
                HSSFCell cell = dataRow.createCell(j);
                Field field = fieldMap.get(headerKey[j]);
                field.setAccessible(true);
                try {
                    if (field.get(a) == null || "".equals(field.get(a))) {
                        cell.setCellValue("");
                    } else if (field.get(a) instanceof BigDecimal) {
                        cell.setCellType(CellType.NUMERIC);
                        cell.setCellStyle(decimalStyle);
                        cell.setCellValue(Double.parseDouble(field.get(a).toString()));
                    } else {
                        cell.setCellValue(field.get(a).toString());
                        cell.setCellStyle(contextStyle);
                    }
                    //log.info("{}列填充cell:{}", n, cell.getStringCellValue());
                } catch (IllegalAccessException e) {
                    log.error("创建失败😭");
                    e.printStackTrace();
                }
            }
        }
        for (int i = 0;i<headerKey.length;i++){
            sheet.autoSizeColumn(i, true);
        }
        return wb;
    }

}
