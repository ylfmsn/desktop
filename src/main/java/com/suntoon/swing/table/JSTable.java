package com.suntoon.swing.table;

import com.suntoon.swing.table.header.JSTableHeader;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.HighlighterFactory;
import org.jdesktop.swingx.search.Searchable;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Vector;
/**
 * @ProjectionName desktop
 * @ClassName JSTable
 * @Description 自定义的table对象
 * @Author YueLifeng
 * @Date 2019/4/17 0017下午 3:37
 * @Version 1.0
 */
public class JSTable extends JXTable implements KeyListener {

    private static final long serialVersionUID = 2300711063946251096L;

    //使用一个默认的表模型实例化一个JSTable对象，无数据的
    public JSTable() {
        super();
        initConfig();
    }

    //用指定的表模型实例化一个JXTable对象
    public JSTable(TableModel dm) {
        super(dm);
        initConfig();
    }

    //用指定的表模型、列模型初始化一个JXTable对象
    public JSTable(TableModel dm, TableColumnModel cm) {
        super(dm, cm);
        initConfig();
    }

    //用指定的表格模型 列模型 选中的列表模型实例化一个JXTable对象
    public JSTable(TableModel dm, TableColumnModel cm, ListSelectionModel sm) {
        super(dm, cm, sm);
        initConfig();
    }

    //用给定的行列数实例化JXTable
    public JSTable(int numRows, int numColumns) {
        super(numRows, numColumns);
        initConfig();
    }

    //用带有行数据和列名称的vector实例化
    public JSTable(Vector<?> rowData, Vector<?> columnNames) {
        super(rowData, columnNames);
        initConfig();
    }

    //用表示行数据的二维数据和表示列名称的一维数据实例化JXTable
    public JSTable(Object[][] rowData, Object[] columnNames) {
        super(rowData, columnNames);
        initConfig();
    }

    //设置窗口可编辑状态
    public void readOnly() throws Exception{
        JSTableModel<?> module = (JSTableModel<?>) this.getModel();
        module.setEditable(!module.getEditable());
    }

    /**
     * @Author YueLifeng
     * @Description //初始化设置
     * @Date 下午 3:41 2019/4/17 0017
     * @param
     * @return void
     */
    private void initConfig() {
        this.addKeyListener(this);
        this.setAutoscrolls(true);
        this.setAutoResizeMode(JXTable.AUTO_RESIZE_OFF);
        this.setSortable(true);
        this.setRowHeight(26);
        this.setColumnControlVisible(true);
        this.setShowGrid(true, true);
        this.addHighlighter(HighlighterFactory.createSimpleStriping(Color.LIGHT_GRAY));

        //设置表头对象
        this.setTableHeader(new JSTableHeader(this.getColumnModel()));
        TableCellRenderer headerRenderer = this.getTableHeader().getDefaultRenderer();
        if (headerRenderer instanceof DefaultTableCellRenderer) {
            DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) headerRenderer;
            renderer.setHorizontalAlignment(JLabel.CENTER);
        }

        if (this.getModel() instanceof JSTableModel) {
            JSTableModel<?> module = (JSTableModel<?>) this.getModel();
            if (module != null)
                module.setEditable(false);
        }
    }

    public void moveRow(int srcRow, int tarRow) throws Exception {

        if (srcRow < 0 || srcRow >= this.getRowCount())
            throw new Exception("sourceRow over index");

        if (tarRow < 0 || tarRow >= this.getRowCount())
            throw new Exception("targetRow over index");

        JSTableModel<?> module = (JSTableModel<?>) this.getModel();
        module.moveRow(convertRowIndexToModel(srcRow), convertRowIndexToModel(tarRow));
        this.changeSelection(tarRow, 0, false, false);
    }

    @Override
    public Searchable getSearchable() {
        return super.getSearchable();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //判断鼠标右键操作
    @Override
    public void keyPressed(KeyEvent e) {
        // ascii V 的十进制
        if (e.isControlDown() && e.getKeyCode() == 86) {
            Clipboard sysClb = Toolkit.getDefaultToolkit().getSystemClipboard();
            Transferable t = sysClb.getContents(null);

            if (null != t && t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                try {

                    String text = (String) t.getTransferData(DataFlavor.stringFlavor);
                    if (text == null || text.length() <= 0)
                        return;

                    String[] rows = text.split("\n");
                    if (rows == null || rows.length <= 0)
                        return;

                    if (this.getSelectedRows().length != rows.length) {
                        JOptionPane.showMessageDialog(null, "您所粘贴的行数和您界面上选择的行数不同");
                        return;
                    }
                    // 以下是设置值的操作
                    int dataIndex = 0;

                    for (int j : this.getSelectedRows()) {
                        // 数据是以行为代表的
                        String row = rows[dataIndex];
                        String[] datas = row.split("\t");
                        for (int i = 0; i < datas.length; i++) {
                            this.setValue(j, i, datas[i]);
                        }

                        dataIndex++;
                    }

                } catch (UnsupportedFlavorException | IOException e1) {
                    e1.printStackTrace();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    /**
     * @Author YueLifeng
     * @Description //根据单元个的位置 设置数据
     * @Date 下午 2:59 2019/4/18 0018
     * @param row
     *          行索引
     * @param column
     *          列索引
     * @param value
     *          文本数据
     * @return void
     */
    public void setValue(int row, int column, String value) throws Exception {

        Object original = this.getValueAt(row, column);
        if (value == null || original == null)
            return;

        Class<?> colType = original.getClass();
        JSTableColumn col = (JSTableColumn) this.getColumn(column);
        Object data = ((JSTableModel<?>) this.getModel()).getTableModelListener().getDataTranstor(col, value, colType);
        this.setValueAt(data, row, column);
    }
}
