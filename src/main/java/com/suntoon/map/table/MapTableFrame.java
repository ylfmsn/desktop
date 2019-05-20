package com.suntoon.map.table;

import org.geotools.data.shapefile.dbf.DbaseFileHeader;
import org.geotools.data.shapefile.dbf.DbaseFileReader;
import org.geotools.data.shapefile.files.ShpFiles;

import javax.swing.*;
import java.awt.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectionName desktop
 * @ClassName MapTableFrame
 * @Description TODO
 * @Author YueLifeng
 * @Date 2019/5/17 0017下午 2:50
 * @Version 1.0
 */
public class MapTableFrame extends JFrame {

    public MapTableFrame(String dbfFilePath) {

        setTitle("属性表");
        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // 创建内容面板，使用边界布局
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(1400, 700));

        Map map = dbaseReader(dbfFilePath);
        MapTableModel mapTableModel = new MapTableModel((Object[]) map.get("header"), (List<Object[]>) map.get("data"));
        MapTable mapTable = new MapTable(mapTableModel);

        JScrollPane scrollPane = new JScrollPane(mapTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(new TableToolBar(mapTable), BorderLayout.NORTH);

        this.setContentPane(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * @Author YueLifeng
     * @Description //读取dbf
     * @Date 下午 2:58 2019/5/17 0017
     * @param path
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    private Map<String, Object> dbaseReader(String path) {

        Map<String, Object> map = new HashMap<>();
        Object[] columnNames = null;
        List list = new ArrayList();
        DbaseFileReader reader = null;

        try {
            //第三个参数使用一个charset类型的实例，使用的参数是编码类型
            reader = new DbaseFileReader(new ShpFiles(path), false, Charset.forName("GBK"));
            DbaseFileHeader header = reader.getHeader();
            //返回字段的数量
            int numFields = header.getNumFields();

            if (numFields > 0) {
                columnNames = new Object[numFields];

                for (int i = 0; i < numFields; i++) {
                    columnNames[i] = header.getFieldName(i);
                }
                map.put("header", columnNames);

                // 迭代读取数据
                while (reader.hasNext()) {
                    list.add(reader.readEntry());
                }
                map.put("data", list);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                //关闭
                try
                {
                    reader.close();
                } catch (Exception e) {

                }
            }
        }
        return map;
    }

    public static void main(String[] args) {
        MapTableFrame mapTableFrame = new MapTableFrame("E:\\ArcGISdata\\bou4_4m\\BOUNT_poly.dbf");
    }
}
