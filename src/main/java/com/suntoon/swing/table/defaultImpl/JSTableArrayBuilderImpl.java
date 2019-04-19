package com.suntoon.swing.table.defaultImpl;

import com.suntoon.swing.table.*;

import java.util.Collection;

/**
 * @ProjectionName desktop
 * @ClassName JSTableArrayBuilderImpl
 * @Description 默认的tablemodel和columnmodel实现
 * @Author YueLifeng
 * @Date 2019/4/19 0019上午 10:45
 * @Version 1.0
 */
public class JSTableArrayBuilderImpl implements JSTableBuilder<Collection<Object[]>> {

    //当前的column列表
    private JSTableColumn[] columns;

    //当前操作的column列表
    public JSTableColumn[] getColumns() {
        return columns;
    }

    public void setColumns(JSTableColumn[] columns) {
        this.columns = columns;
    }

    //缓存的colmodel对象
    private JSTableColumnModel colModel;

    private JSTableModel<Collection<Object[]>> tableModel;

    //带有列信息的构造器
    public JSTableArrayBuilderImpl(JSTableColumn... cols) {
        this.columns = cols;
    }


    @Override
    public JSTableColumnModel buildTableColumnModel() throws Exception {
        JSTableColumnModel colModel = new JSTableColumnModel();

        if (this.columns == null || this.columns.length <= 0)
            throw new Exception("no columns");

        int i = 0;
        for (JSTableColumn col : this.columns) {
            if (col.getModelIndex() < 0) {
                col.setModelIndex(i);
            }
            colModel.addColumn(col);
            i++;
        }
        this.colModel = colModel;

        return colModel;
    }

    @Override
    public JSTableModel<Collection<Object[]>> buildTableModel() throws Exception {
        JSTableArrayModel tableModel = new JSTableArrayModel();

        if (this.columns == null || this.columns.length <= 0)
            throw new Exception("no columns");

        for (JSTableColumn col : this.columns)
            tableModel.addColumn(col);

        //生成原始数据的列
        if (tableModel.findColumn(JSTableColumn.COLUMN_ORIGINAL) < 0) {
            JSTableColumn column = new JSTableColumn();
            column.setTitle(JSTableColumn.COLUMN_ORIGINAL);
            column.setHeaderValue(JSTableColumn.COLUMN_ORIGINAL);
            tableModel.addColumn(column);
        }

        this.tableModel = tableModel;

        return tableModel;
    }

    @Override
    public JSTableModel<Collection<Object[]>> getTableModel() throws Exception {
        if (this.tableModel == null)
            this.tableModel = buildTableModel();

        return this.tableModel;
    }

    @Override
    public JSTableColumnModel getTableColumnModel() throws Exception {
        if (this.colModel == null)
            this.colModel = buildTableColumnModel();

        return this.colModel;
    }

    @Override
    public JSTableModelListener<Collection<Object[]>> buildModelListener(JSTableModel<Collection<Object[]>> tableModel) throws Exception {
        JSTableModelArrayAdapter adapter = new JSTableModelArrayAdapter();
        tableModel.setTableModelListener(adapter);
        return adapter;
    }
}
