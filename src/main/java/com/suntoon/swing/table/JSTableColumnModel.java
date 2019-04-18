package com.suntoon.swing.table;

import org.jdesktop.swingx.table.DefaultTableColumnModelExt;

import javax.swing.table.TableColumn;
import java.util.List;

/**
 * @ProjectionName desktop
 * @ClassName JSTableColumnModel
 * @Description 扩展的TableColumnModel
 * @Author YueLifeng
 * @Date 2019/4/18 0018下午 5:26
 * @Version 1.0
 */
public class JSTableColumnModel extends DefaultTableColumnModelExt {

    private static final long serialVersionUID = -3443304855739066731L;

    /**
     * @Author YueLifeng
     * @Description //获取第一个可编辑列
     * @Date 下午 5:33 2019/4/18 0018
     * @param
     * @return int
     */
    public int getFirstEditColumnModelIndex() {
        List<TableColumn> columns = this.getColumns(true);
        if (columns == null || columns.isEmpty())
            return -1;

        for (TableColumn column : columns) {
            JSTableColumn col = (JSTableColumn) column;
            if (col.isEditable())
                return column.getModelIndex();
        }

        return -1;
    }
}
