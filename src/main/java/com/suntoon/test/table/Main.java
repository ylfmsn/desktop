package com.suntoon.test.table;

import com.suntoon.test.ReadDbf;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception {

        /**
         * 表头（列名）
         */
        Object[] columnNames = {"姓名", "语文", "数学", "英语", "总分"};

        /**
         * 表格所有行数据
         */
        Object[][] rowData = {
                {"张三", 80, 80, 80, 240},
                {"John", 70, 80, 90, 240},
                {"Sue", 70, 70, 70, 210},
                {"Jane", 80, 70, 60, 210},
                {"Joe", 80, 70, 60, 210}
        };

        ReadDbf readDbf = new ReadDbf();
        Map map = readDbf.dbaseReader("E:\\ArcGISdata\\bou4_4m\\BOUNT_poly.dbf");

        JFrame jf = new JFrame("测试窗口");
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // 创建内容面板，使用边界布局
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(500, 400));

        // 使用表格模型创建一个表格
        MyTableModel myTableModel = new MyTableModel();
        myTableModel.setColumnNames((Object[]) map.get("header"));
        myTableModel.setRowData((List<Object[]>) map.get("data"));
        System.out.println(myTableModel.getColumnCount());

        JTable table = new JTable(myTableModel);

        RowSorter<MyTableModel> rowSorter = new TableRowSorter<MyTableModel>(myTableModel);
        table.setRowSorter(rowSorter);

        DefaultTableCellRenderer renderC = new DefaultTableCellRenderer();
        renderC.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

        for (int i = 0; i < myTableModel.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setWidth(55);
            table.getColumnModel().getColumn(i).setPreferredWidth(55);
            //table.getColumnModel().getColumn(i).setMinWidth(55);
            //table.getColumnModel().getColumn(i).setMaxWidth(85);
            table.getColumnModel().getColumn(i).sizeWidthToFit();
            table.getColumnModel().getColumn(i).setResizable(true);
            table.getColumnModel().getColumn(i).setCellRenderer(renderC);
        }

        // 设置表格内容颜色
        table.setForeground(Color.BLACK);  // 字体颜色
        table.setFont(new Font(null, Font.PLAIN, 16));  // 字体样式
        table.setSelectionForeground(Color.DARK_GRAY);  // 选中后字体颜色
        table.setSelectionBackground(Color.LIGHT_GRAY);  // 选中后字体背景
        table.setGridColor(Color.GRAY); // 网格颜色

        // 设置表头
        table.getTableHeader().setFont(new Font(null, Font.BOLD, 18)); // 设置表头名称字体样式
        table.getTableHeader().setForeground(Color.pink); // 设置表头名称字体颜色
        table.getTableHeader().setResizingAllowed(true); // 设置不允许手动改变列宽
        table.getTableHeader().setReorderingAllowed(false); // 设置不允许拖动重新排序各列

        table.setRowHeight(30);

        // 把 表头 添加到容器顶部（使用普通的中间容器添加表格时，表头 和 内容 需要分开添加）
        //panel.add(table.getTableHeader(), BorderLayout.NORTH);
        // 把 表格内容 添加到容器中心
        //panel.add(table, BorderLayout.CENTER);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // 加个工具条
        // 以下是测试表格显示风格的操作
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setMargin(new Insets(2, 15, 2, 15));
        JButton btn = new JButton("导出");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        toolBar.add(btn);
        panel.add(toolBar, BorderLayout.NORTH);

        jf.setContentPane(panel);
        jf.pack();
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }

    /**
     * 表格模型实现，表格显示数据时将调用模型中的相应方法获取数据进行表格内容的显示
     */
    public static class MyTableModel extends AbstractTableModel {
        /**
         * 表头（列名）
         */
        private Object[] columnNames;

        /**
         * 表格所有行数据
         */
        private List<Object[]> rowData;

        public MyTableModel() {

        }

        public MyTableModel(Object[] columnNames, List<Object[]> rowData) {
            this.columnNames = columnNames;
            this.rowData = rowData;
        }

        public Object[] getColumnNames() {
            return this.columnNames;
        }

        public void setColumnNames(Object[] columnNames) {
            this.columnNames = columnNames;
        }

        public List<Object[]> getRowData() {
            return rowData;
        }

        public void setRowData(List<Object[]> rowData) {
            this.rowData = rowData;
        }

        /**
         * 返回总行数
         */
        @Override
        public int getRowCount() {
            return rowData.size();
        }

        /**
         * 返回总列数
         */
        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        /**
         * 返回列名称（表头名称），AbstractTableModel 中对该方法的实现默认是以
         * 大写字母 A 开始作为列名显示，所以这里需要重写该方法返回我们需要的列名。
         */
        @Override
        public String getColumnName(int column) {
            return columnNames[column].toString();
        }

        /**
         * 返回指定单元格的显示的值
         */
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return rowData.get(rowIndex)[columnIndex];
        }
    }

}
