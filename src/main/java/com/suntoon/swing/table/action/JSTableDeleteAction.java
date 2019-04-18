package com.suntoon.swing.table.action;

import com.suntoon.swing.resource.ResourceLoader;
import com.suntoon.swing.table.JSTableModel;
import org.jdesktop.swingx.JXTable;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @ProjectionName desktop
 * @ClassName JSTableDeleteAction
 * @Description 删除操作对象
 * @Author YueLifeng
 * @Date 2019/4/18 0018下午 5:43
 * @Version 1.0
 */
public class JSTableDeleteAction extends JSTableBaseAction {

    private static final long serialVersionUID = -3068593608658058519L;

    public JSTableDeleteAction(JXTable table) {
        super(table);
        this.putValue(AbstractAction.NAME, "");
        this.putValue(AbstractAction.SMALL_ICON,
                new ImageIcon(ResourceLoader.getResource(ResourceLoader.IMAGE_DELETE)));
        this.putValue(SHORT_DESCRIPTION, "删除选中的数据");
    }

    /**
     * @Author YueLifeng
     * @Description //删除操作
     * @Date 下午 5:49 2019/4/18 0018
     * @param e
     * @return void
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            int selectedRowCount = this.getTable().getSelectedRowCount();
            if (selectedRowCount <= 0) {
                JOptionPane.showMessageDialog(null, "请选择您要删除的数据行");
                return;
            } else if (selectedRowCount == 1) {
                int iRow = this.getTable().getSelectedRow();
                ((JSTableModel<?>) this.getTable().getModel()).delete(this.getTable().convertRowIndexToModel(iRow));
            } else {
                // > 1的情况
                int confim = JOptionPane.showConfirmDialog(null, "您将删除【" + selectedRowCount + "】行数据，是否删除？");
                if (confim == JOptionPane.OK_OPTION) {
                    int[] selectedRows = this.getTable().getSelectedRows();
                    for (int i = selectedRowCount - 1; i >= 0; i--) {
                        JSTableModel<?> model = (JSTableModel<?>) this.getTable().getModel();
                        model.delete(this.getTable().convertRowIndexToModel(selectedRows[i]));
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
