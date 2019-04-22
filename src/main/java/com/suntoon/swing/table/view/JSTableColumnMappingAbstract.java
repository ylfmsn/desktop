package com.suntoon.swing.table.view;

import com.suntoon.swing.table.JSTableColumn;

import java.util.LinkedList;
import java.util.List;

/**
 * @ProjectionName desktop
 * @ClassName JSTableColumnMappingAbstract
 * @Description 实现默认接口方法的抽象类
 * @Author YueLifeng
 * @Date 2019/4/22 0022下午 5:12
 * @Version 1.0
 */
public abstract class JSTableColumnMappingAbstract implements JSTableColumnMapping {
    /**
     *
     */
    private static final long serialVersionUID = 6355696734931125001L;

    /**
     * 当前对象的类定义列表
     */
    private List<JSTableColumn> cols = new LinkedList<>();

    /**
     * 当前的实体class类型
     */
    private Class<?> entityCls;

    /**
     * {@inheritDoc}
     */
    @Override
    public JSTableColumn[] getColumns() {
        if (cols == null || cols.isEmpty())
            return null;

        return cols.toArray(new JSTableColumn[cols.size()]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setColumns(JSTableColumn... columns) {
        this.cols.clear();

        if (columns != null && columns.length > 0)
        {
            for(JSTableColumn col : columns)
            {
                this.cols.add(col);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addColumn(JSTableColumn column) {

        if (column != null)
            this.cols.add(column);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<?> getEntityClass() {
        return this.entityCls;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEntity(Class<?> cls) {
        this.entityCls = cls;
    }

    /**
     * 继承类必须实现这个方法
     */
    public abstract void initCols();
}
