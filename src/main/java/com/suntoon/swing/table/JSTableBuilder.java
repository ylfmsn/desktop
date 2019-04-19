package com.suntoon.swing.table;

/**
 * @ProjectionName desktop
 * @InterfaceName JSTableBuilder
 * @Description 表格生成器对象
 * @Author ylf
 * @Date 2019/4/19 0019上午 10:47
 * @Version 1.0
 */
public interface JSTableBuilder<E> {

    //构建columnModel
    public JSTableColumnModel buildTableColumnModel() throws Exception;

    //创建tablemodel的方法
    public JSTableModel<E> buildTableModel() throws Exception;

    //获取刚刚创建好的tablemodel
    public JSTableModel<E> getTableModel() throws Exception;

    //获取刚刚创建好的colmodel
    public JSTableColumnModel getTableColumnModel() throws Exception;

    /**
     * @Author YueLifeng
     * @Description //创建表格的方法
     * @Date 上午 10:51 2019/4/19 0019
     * @param tableModel
     * @param colModel
     * @return com.suntoon.swing.table.JSTable
     */
    public default JSTable buildTable(JSTableModel<E> tableModel, JSTableColumnModel colModel) {
        return new JSTable(tableModel, colModel);
    }

    /**
     * @Author YueLifeng
     * @Description //用之前2步创建好的对象，直接生成表格
     * @Date 上午 10:56 2019/4/19 0019
     * @param
     * @return com.suntoon.swing.table.JSTable
     */
    public default JSTable buildTable() throws Exception {
        JSTableModel<E> tableModel = this.getTableModel();
        JSTableColumnModel tableColumnModel = this.getTableColumnModel();
        JSTable table = this.buildTable(tableModel, tableColumnModel);
        JSTableModelListener<E> modelAdapter = this.buildModelListener();
        tableModel.setTableModelListener(modelAdapter);
        return table;
    }

    /**
     * @Author YueLifeng
     * @Description //用之前生成好的对象，直接生成表格
     * @Date 上午 10:56 2019/4/19 0019
     * @param
     * @return com.suntoon.swing.table.JSTableModelListener<E>
     */
    public default JSTableModelListener<E> buildModelListener() throws Exception {
        return this.buildModelListener(this.getTableModel());
    }

    /**
     * @Author YueLifeng
     * @Description //创建对应的数据操作对象
     * @Date 上午 10:54 2019/4/19 0019
     * @param tableModel
     * @return com.suntoon.swing.table.JSTableModelListener<E>
     */
    public JSTableModelListener<E> buildModelListener(JSTableModel<E> tableModel) throws Exception;
}
