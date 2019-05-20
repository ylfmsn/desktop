package com.suntoon.map.table;

import com.suntoon.map.utils.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * @ProjectionName desktop
 * @ClassName TableToolBar
 * @Description TODO
 * @Author YueLifeng
 * @Date 2019/5/17 0017下午 4:13
 * @Version 1.0
 */
public class TableToolBar extends JToolBar {

    public TableToolBar(MapTable table) {
        this.setFloatable(false);
        this.setMargin(new Insets(2, 15, 2, 15));
        initCompenent(table);
    }

    private void initCompenent(MapTable table) {

        // 导出
        JButton export = new JButton("导出");
        export.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Object[] header = table.getHeader();
                List<Object[]> data = table.getData();

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

                    HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook("属性表", header, data);

                    try {
                        OutputStream os = new FileOutputStream(file.getAbsoluteFile() + "\\" + "属性表.xls");
                        wb.write(os);
                        os.flush();
                        os.close();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        this.add(export);
    }
}
