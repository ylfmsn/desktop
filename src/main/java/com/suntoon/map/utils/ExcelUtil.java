package com.suntoon.map.utils;

import org.apache.poi.hssf.usermodel.*;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.List;

/**
 * @ProjectionName desktop
 * @ClassName ExcelUtil
 * @Description TODO
 * @Author YueLifeng
 * @Date 2019/5/17 0017下午 5:00
 * @Version 1.0
 */
public class ExcelUtil {

    public static HSSFWorkbook getHSSFWorkbook(String sheetName, Object[] title, List<Object[]> values) {

        HSSFWorkbook wb = new HSSFWorkbook();

        HSSFSheet sheet = wb.createSheet(sheetName);

        HSSFRow row = sheet.createRow(0);

        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        HSSFCell cell = null;

        for(int i = 0; i < title.length; i++){
            cell = row.createCell(i);
            cell.setCellValue(title[i].toString());
            cell.setCellStyle(style);
        }

        for(int i = 0; i < values.size(); i++){
            row = sheet.createRow(i + 1);
            for(int j = 0; j < values.get(i).length; j++){
                //将内容按顺序赋给对应的列对象
                row.createCell(j).setCellValue(values.get(i)[j].toString());
            }
        }

        return wb;
    }

    public static void main(String[] args) {

        JFileChooser jfc = new JFileChooser();
        //设置当前路径为桌面路径,否则将我的文档作为默认路径
        FileSystemView fsv = FileSystemView .getFileSystemView();
        jfc.setCurrentDirectory(fsv.getHomeDirectory());
        //JFileChooser.FILES_AND_DIRECTORIES 选择路径和文件
        //jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //用户选择的路径或文件
        if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = jfc.getSelectedFile();








        }





    }


}
