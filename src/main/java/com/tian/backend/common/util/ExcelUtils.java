package com.tian.backend.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        //Excel名字
        HSSFSheet sheet;
        sheet = "".equals(sheetName) ? wb.createSheet("sheet1") : wb.createSheet(sheetName);
        //靠左放置
        HSSFCellStyle headerStyle = wb.createCellStyle();
        headerStyle.setAlignment(HorizontalAlignment.LEFT);
        //设置字体
        HSSFFont font = wb.createFont();
        //是否加粗
        font.setBold(true);
        //字体大小
        font.setFontHeightInPoints((short) 12);
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
        List<String> keyList = Arrays.asList(headerKey);
        //插入数值
        int n = 0;
        for (Object a : dataList) {
            List<Field> fields = Arrays.asList(a.getClass().getDeclaredFields());
            //转化成对应的map
            Map<String, Field> map = new HashMap<>();
            fields.forEach(field -> {
                if (keyList.contains(field.getName())) {
                    map.put(headerKey[keyList.indexOf(field.getName())], field);
                }
            });
            HSSFRow dataRow = sheet.createRow(n += 1);
            //按照headerKey的顺序插值,后面根据key插入data中对应的值
            for (int j = 0; j < headerKey.length; j++) {
                HSSFCell cell = dataRow.createCell(j);
                Field field = map.get(headerKey[j]);
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
