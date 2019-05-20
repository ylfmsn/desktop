package com.suntoon.map.table;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * @ProjectionName desktop
 * @ClassName MapTable
 * @Description TODO
 * @Author YueLifeng
 * @Date 2019/5/17 0017下午 2:54
 * @Version 1.0
 */
public class MapTable extends JTable {

    private Map<String, Object> map;    //填充的表格头以及数据

    private Object[] header;      //表头

    private List<Object[]> data;     //表格数据

    private MapTableModel mapTableModel;

    public MapTable() {
        super();
    }

    public MapTable(MapTableModel mapTableModel) {
        super(mapTableModel);
        this.mapTableModel = mapTableModel;
        this.header = mapTableModel.getColumnNames();
        this.data = mapTableModel.getRowData();
        init();
    }

    public void init() {
        // 列排序
        RowSorter<MapTableModel> rowSorter = new TableRowSorter<>(mapTableModel);
        this.setRowSorter(rowSorter);

        // 单元格居中显示
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

        TableColumnModel tableColumnModel = getColumnModel();
        // 设置每一列的宽和显示居中
        for (int i = 0; i < mapTableModel.getColumnCount(); i++) {
            TableColumn tableColumn = tableColumnModel.getColumn(i);
            tableColumn.setWidth(55);
            tableColumn.setPreferredWidth(55);
            tableColumn.sizeWidthToFit();
            tableColumn.setResizable(true);
            tableColumn.setCellRenderer(renderer);
        }

        // 设置表格内容颜色
        this.setForeground(Color.BLACK);  // 字体颜色
        this.setFont(new Font(null, Font.PLAIN, 16));  // 字体样式
        this.setSelectionForeground(Color.DARK_GRAY);  // 选中后字体颜色
        this.setSelectionBackground(Color.LIGHT_GRAY);  // 选中后字体背景
        this.setGridColor(Color.GRAY); // 网格颜色

        // 设置表头
        this.getTableHeader().setFont(new Font(null, Font.BOLD, 18)); // 设置表头名称字体样式
        this.getTableHeader().setForeground(Color.pink); // 设置表头名称字体颜色
        this.getTableHeader().setResizingAllowed(true); // 设置不允许手动改变列宽
        this.getTableHeader().setReorderingAllowed(false); // 设置不允许拖动重新排序各列

        this.setRowHeight(30);     // 行高
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public Object[] getHeader() {
        return header;
    }

    public void setHeader(Object[] header) {
        this.header = header;
    }

    public List<Object[]> getData() {
        return data;
    }

    public void setData(List<Object[]> data) {
        this.data = data;
    }

    public MapTableModel getMapTableModel() {
        return mapTableModel;
    }

    public void setMapTableModel(MapTableModel mapTableModel) {
        this.mapTableModel = mapTableModel;
    }
}
