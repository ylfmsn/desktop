package com.suntoon.swing.table.action;

import com.suntoon.swing.resource.ResourceLoader;
import org.jdesktop.swingx.JXTable;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;

/**
 * @ProjectionName desktop
 * @ClassName JSTableUnSelectAction
 * @Description 全不选操作
 * @Author YueLifeng
 * @Date 2019/4/19 0019上午 10:06
 * @Version 1.0
 */
public class JSTableUnSelectAction extends JSTableBaseAction {

    private static final long serialVersionUID = -1903852879063133862L;

    //全不选操作
    public JSTableUnSelectAction(JXTable table) {
        super(table);
        this.putValue(AbstractAction.NAME, "");
        this.putValue(AbstractAction.SMALL_ICON,
                new ImageIcon(ResourceLoader.getResource(ResourceLoader.IMAGE_UNSELECT)));
        this.putValue(AbstractAction.SHORT_DESCRIPTION, "全不选");
    }

    //当前操作的列位置 默认下标1
    private int editColIndex = 1;

    public int getEditColIndex() {
        return editColIndex;
    }

    public void setEditColIndex(int editColIndex) {
        this.editColIndex = editColIndex;
    }

    //当前选中字段的非选中值 默认是数字0
    private Object unSelectedValue = Integer.valueOf(0);

    public Object getUnSelectedValue() {
        return unSelectedValue;
    }

    public void setUnSelectedValue(Object unSelectedValue) {
        this.unSelectedValue = unSelectedValue;
    }

    //执行按钮的操作
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int[] selectedRows = this.getTable().getSelectedRows();

            TableModel tableModel = this.getTable().getModel();
            //没有选中行的情况下操作
            if (selectedRows == null || selectedRows.length <= 1) {
                int count = tableModel.getRowCount();
                for (int i = 0; i < count; i++) {
                    if (!tableModel.getValueAt(i, editColIndex).equals(unSelectedValue))
                        tableModel.setValueAt(unSelectedValue, i, editColIndex);
                }
            } else {
                //选中行操作
                for (int i = 0; i < selectedRows.length; i++) {
                    int iRow = table.convertRowIndexToModel(selectedRows[i]);
                    if (!tableModel.getValueAt(iRow, editColIndex).equals(unSelectedValue))
                        tableModel.setValueAt(unSelectedValue, iRow, editColIndex);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
