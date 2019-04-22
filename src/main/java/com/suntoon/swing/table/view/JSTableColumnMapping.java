package com.suntoon.swing.table.view;

import com.suntoon.swing.table.JSTableColumn;

import java.io.Serializable;

/**
 * @ProjectionName desktop
 * @InterfaceName JSTableColumnMapping
 * @Description 列映射关系接口
 * @Author ylf
 * @Date 2019/4/22 0022下午 4:37
 * @Version 1.0
 */
public interface JSTableColumnMapping extends Serializable {

    //获取列定义的列表
    public JSTableColumn[] getColumns();

    //设置当前列定义列表
    public void setColumns(JSTableColumn... columns);

    /**
     * @Author YueLifeng
     * @Description //增加一个列
     * @Date 下午 4:39 2019/4/22 0022
     * @param column
     *          列
     * @return void
     */
    public void addColumn(JSTableColumn column);

    //设置数据访问层对象
    public <Dao> void setDao(Dao dao);

    //获取实体层对象
    public <Dao> Dao getDao();

    //获取实体类型
    public Class<?> getEntityClass();

    //设置实体的类型
    public void setEntity(Class<?> cls);
}
