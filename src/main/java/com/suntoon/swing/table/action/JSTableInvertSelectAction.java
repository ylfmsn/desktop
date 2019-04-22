package com.suntoon.swing.table.action;

import com.suntoon.swing.resource.ResourceLoader;
import org.apache.commons.lang3.ArrayUtils;
import org.jdesktop.swingx.JXTable;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;

/**
 * @ProjectionName desktop
 * @ClassName JSTableInvertSelectAction
 * @Description 反选操作
 * @Author YueLifeng
 * @Date 2019/4/19 0019上午 9:04
 * @Version 1.0
 */
public class JSTableInvertSelectAction extends JSTableBaseAction {

    private static final long serialVersionUID = -1903852879063133862L;

    //全不选操作
    public JSTableInvertSelectAction(JXTable table) {
        super(table);
        this.putValue(AbstractAction.NAME, "");
        this.putValue(AbstractAction.SMALL_ICON,
                new ImageIcon(ResourceLoader.getResource(ResourceLoader.IMAGE_INVERT_SELECT)));
        this.putValue(SHORT_DESCRIPTION, "反选");
    }

    //当前操作的列位置 默认下标1
    private int editColIndex = 1;

    public int getEditColIndex() {
        return editColIndex;
    }

    public void setEditColIndex(int editColIndex) {
        this.editColIndex = editColIndex;
    }

    //当前选中字段的分选中值 默认是数字1
    private Object selectedValue = Integer.valueOf(1);


    public Object getSelectedValue() {
        return selectedValue;
    }

    public void setSelectedValue(Object selectedValue) {
        this.selectedValue = selectedValue;
    }

    //当前选中字段的非选中值 默认是数字0
    private Object unSelectedValue = Integer.valueOf(0);

    public Object getUnSelectedValue() {
        return unSelectedValue;
    }

    public void setUnSelectedValue(Object unSelectedValue) {
        this.unSelectedValue = unSelectedValue;
    }

    //执行的按钮操作
    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            int[] selectedRows = this.getTable().getSelectedRows();
            TableModel tableModel = this.getTable().getModel();
            Object selected = this.getSelectedValue();
            Object unselected = this.getUnSelectedValue();
            int count = tableModel.getRowCount();

            //转换成modelRow index
            for (int i = 0; i < selectedRows.length; i++) {
                selectedRows[i] = this.getTable().convertRowIndexToModel(selectedRows[i]);
            }

            //以选中的第一行的行状态为准
            for (int i = 0; i < count; i++) {
                if (ArrayUtils.indexOf(selectedRows, i) >= 0) {
                    if (!tableModel.getValueAt(i, editColIndex).equals(selected))
                        tableModel.setValueAt(selected, i, editColIndex);
                } else {
                    if (!tableModel.getValueAt(i, editColIndex).equals(unselected))
                        tableModel.setValueAt(unselected, i, editColIndex);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
