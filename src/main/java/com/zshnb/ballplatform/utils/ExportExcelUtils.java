package com.zshnb.ballplatform.utils;

import com.zshnb.ballplatform.vo.ClassStatisticsVo;
import com.zshnb.ballplatform.vo.CollegeStatisticsVo;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

/**
 * CreateDate：2020/5/16 <br/>
 * Author：WangHao <br/>
 * Description:
 **/
public class ExportExcelUtils {

    public static XSSFWorkbook exportCollege(List<CollegeStatisticsVo> statisticsVos) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        String[] headers = new String[]{"学院", "班级数", "学生数", "就业人数", "就业率", "实习人数", "实习率"};
        String[] exportFields = new String[]{"collegeName", "classCount", "studentCount", "jobStudentCount", "jobPercent", "practiceStudentCount", "practicePercent"};

        // 创建HSSFWorkbook对象
        XSSFWorkbook wb = new XSSFWorkbook();
        // 创建HSSFSheet对象
        XSSFSheet sheet = wb.createSheet("sheet0");
        // 设置表格默认列宽度为20个字节
        sheet.setDefaultColumnWidth((short) 20);
        // 生成一个样式
        XSSFCellStyle style = wb.createCellStyle();
        //设置样式
        setStyle(style);

        // 创建HSSFRow对象(第一行，表头)
        XSSFRow row = sheet.createRow(0);
        //循环写表头
        for (short i = 0; i < headers.length; i++) {
            // 把字体应用到当前的样式
            style.setFont(getBoldFont(wb));
            // 创建HSSFCell对象
            XSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            cell.setCellValue(headers[i]);
        }

        //循环设值
        for (int j = 0; j < statisticsVos.size(); j++) {
            //从第二行开始写
            row = sheet.createRow(j + 1);
            //导出偏移量，用于过滤数据
            for (int k = 0; k < exportFields.length; k++) {
                String fieldName = exportFields[k];
                XSSFCell cell = row.createCell(k);
                cell.setCellStyle(style);
                //反射获取get方法和值
                String getMethodName = "get"
                        + fieldName.substring(0, 1).toUpperCase()
                        + fieldName.substring(1);
                Method getMethod = statisticsVos.get(j).getClass().getMethod(getMethodName);
                Object value = getMethod.invoke(statisticsVos.get(j));

                // 把字体应用到当前的样式
                style.setFont(getBlackFont(wb));
                cell.setCellStyle(style);
                //类型转化
                convertValue(cell, value);
            }
        }
        return wb;
    }

    public static XSSFWorkbook exportClass(List<ClassStatisticsVo> statisticsVos) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        String[] headers = new String[]{"班级", "学生数", "就业人数", "就业率", "实习人数", "实习率"};
        String[] exportFields = new String[]{"className", "studentCount", "jobStudentCount", "jobPercent", "practiceStudentCount", "practicePercent"};

        // 创建HSSFWorkbook对象
        XSSFWorkbook wb = new XSSFWorkbook();
        // 创建HSSFSheet对象
        XSSFSheet sheet = wb.createSheet("sheet0");
        // 设置表格默认列宽度为20个字节
        sheet.setDefaultColumnWidth((short) 20);
        // 生成一个样式
        XSSFCellStyle style = wb.createCellStyle();
        //设置样式
        setStyle(style);

        // 创建HSSFRow对象(第一行，表头)
        XSSFRow row = sheet.createRow(0);
        //循环写表头
        for (short i = 0; i < headers.length; i++) {
            // 把字体应用到当前的样式
            style.setFont(getBoldFont(wb));
            // 创建HSSFCell对象
            XSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            cell.setCellValue(headers[i]);
        }

        //循环设值
        for (int j = 0; j < statisticsVos.size(); j++) {
            //从第二行开始写
            row = sheet.createRow(j + 1);
            //导出偏移量，用于过滤数据
            for (int k = 0; k < exportFields.length; k++) {
                String fieldName = exportFields[k];
                XSSFCell cell = row.createCell(k);
                cell.setCellStyle(style);
                //反射获取get方法和值
                String getMethodName = "get"
                        + fieldName.substring(0, 1).toUpperCase()
                        + fieldName.substring(1);
                Method getMethod = statisticsVos.get(j).getClass().getMethod(getMethodName);
                Object value = getMethod.invoke(statisticsVos.get(j));

                // 把字体应用到当前的样式
                style.setFont(getBlackFont(wb));
                cell.setCellStyle(style);
                //类型转化
                convertValue(cell, value);
            }
        }
        return wb;
    }

    public static XSSFWorkbook exportExcel(String excelName, String[] headers, String[] exportFields, List<?> datas) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 创建HSSFWorkbook对象
        XSSFWorkbook wb = new XSSFWorkbook();
        // 创建HSSFSheet对象
        XSSFSheet sheet = wb.createSheet("sheet0");
        // 设置表格默认列宽度为20个字节
        sheet.setDefaultColumnWidth((short) 20);
        // 生成一个样式
        XSSFCellStyle style = wb.createCellStyle();
        //设置样式
        setStyle(style);

        // 创建HSSFRow对象(第一行，表头)
        XSSFRow row = sheet.createRow(0);
        //循环写表头
        for (short i = 0; i < headers.length; i++) {
            // 把字体应用到当前的样式
            style.setFont(getBoldFont(wb));
            // 创建HSSFCell对象
            XSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            cell.setCellValue(headers[i]);
        }


        //循环设值
        for (int j = 0; j < datas.size(); j++) {
            //从第二行开始写
            row = sheet.createRow(j + 1);
            //导出偏移量，用于过滤数据
            for (int k = 0; k < exportFields.length; k++) {
                String fieldName = exportFields[k];
                XSSFCell cell = row.createCell(k);
                cell.setCellStyle(style);
                //反射获取get方法和值
                String getMethodName = "get"
                        + fieldName.substring(0, 1).toUpperCase()
                        + fieldName.substring(1);
                Method getMethod = datas.get(j).getClass().getMethod(getMethodName, new Class[]{});
                Object value = getMethod.invoke(datas.get(j), new Object[]{});

                // 把字体应用到当前的样式
                style.setFont(getBlackFont(wb));
                cell.setCellStyle(style);
                //类型转化
                convertValue(cell, value);

            }
        }
        return wb;
    }

    /**
     * 样式设置
     *
     * @param style
     */
    private static void setStyle(XSSFCellStyle style) {
        // 设置样式
        // 前景色
        style.setFillForegroundColor(HSSFColor.WHITE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        // 边框
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 指定当单元格内容显示不下时自动换行
        style.setWrapText(true);
    }

    /**
     * 生成黑体字
     *
     * @param wb
     * @return
     */
    private static XSSFFont getBlackFont(XSSFWorkbook wb) {
        // 生成一个字体
        XSSFFont font = wb.createFont();
        font.setColor(IndexedColors.BLACK.index);
        font.setFontHeightInPoints((short) 12);
        return font;
    }

    /**
     * 生成加粗黑体字
     *
     * @param wb
     * @return
     */
    private static XSSFFont getBoldFont(XSSFWorkbook wb) {
        // 生成一个字体
        XSSFFont font = wb.createFont();
        font.setColor(IndexedColors.BLACK.index);
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        font.setFontHeightInPoints((short) 12);
        return font;
    }

    /**
     * 类型转化
     *
     * @param cell  HSSFCell对象
     * @param value 属性值
     * @return HSSFCell对象
     */
    private static void convertValue(XSSFCell cell, Object value) {
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof String) {
            cell.setCellValue((String) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Date) {
            cell.setCellValue((Date) value);
        } else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        } else if (value instanceof Short) {
            cell.setCellValue((Short) value);
        } else if (value instanceof Byte) {
            cell.setCellValue((Byte) value);
        }
    }

}

