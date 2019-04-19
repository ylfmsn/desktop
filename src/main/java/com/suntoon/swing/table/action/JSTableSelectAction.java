package com.suntoon.swing.table.action;

import com.suntoon.swing.resource.ResourceLoader;
import org.jdesktop.swingx.JXTable;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;

/**
 * @ProjectionName desktop
 * @ClassName JSTableSelectAction
 * @Description 全选操作
 * @Author YueLifeng
 * @Date 2019/4/19 0019上午 9:49
 * @Version 1.0
 */
public class JSTableSelectAction extends JSTableBaseAction {

    private static final long serialVersionUID = -1903852879063133862L;

    //全选操作
    public JSTableSelectAction(JXTable table) {
        super(table);
        this.putValue(AbstractAction.NAME, "");
        this.putValue(AbstractAction.SMALL_ICON,
                new ImageIcon(ResourceLoader.getResource(ResourceLoader.IMAGE_SELECT)));
        this.putValue(AbstractAction.SHORT_DESCRIPTION, "全选");
    }

    //当前操作的列位置 默认下标1
    private int editColIndex = 1;

    public int getEditColIndex() {
        return editColIndex;
    }

    public void setEditColIndex(int editColIndex) {
        this.editColIndex = editColIndex;
    }

    //当前选中字段的值 默认是数字1
    private Object selectedValue = Integer.valueOf(1);

    public Object getSelectedValue() {
        return selectedValue;
    }

    public void setSelectedValue(Object selectedValue) {
        this.selectedValue = selectedValue;
    }

    //执行的按钮操作
    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            int[] selectedRows = this.getTable().getSelectedRows();

            TableModel tableModel = this.getTable().getModel();

            //在没有选中行的情况下的操作
            if (selectedRows == null || selectedRows.length <= 1) {
                int count = tableModel.getRowCount();
                for (int i = 0; i < count; i++) {
                    if (!tableModel.getValueAt(i, editColIndex).equals(selectedValue))
                        tableModel.setValueAt(selectedValue, i, editColIndex);
                }
            } else {
                //有选中行的情况下的操作
                for (int i = 0; i < selectedRows.length; i++) {
                    int iRow = table.convertRowIndexToModel(selectedRows[i]);
                    if (!tableModel.getValueAt(iRow, editColIndex).equals(selectedValue))
                        tableModel.setValueAt(selectedValue, iRow, editColIndex);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
