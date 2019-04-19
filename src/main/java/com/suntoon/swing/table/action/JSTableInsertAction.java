package com.suntoon.swing.table.action;

import com.suntoon.swing.resource.ResourceLoader;
import com.suntoon.swing.table.JSTableColumnModel;
import com.suntoon.swing.table.JSTableModel;
import org.jdesktop.swingx.JXTable;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @ProjectionName desktop
 * @ClassName JSTableInsertAction
 * @Description 插入操作对象
 * @Author YueLifeng
 * @Date 2019/4/18 0018下午 6:14
 * @Version 1.0
 */
public class JSTableInsertAction extends JSTableBaseAction {

    private static final long serialVersionUID = -3068593608658058519L;

    public JSTableInsertAction(JXTable table) {
        super(table);
        this.putValue(AbstractAction.NAME, "");
        this.putValue(AbstractAction.SMALL_ICON,
                new ImageIcon(ResourceLoader.getResource(ResourceLoader.IMAGE_ADD_SMALL)));
        this.putValue(SHORT_DESCRIPTION, "当前行之前插入数据");
    }

    //插入数据
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            JSTableModel<?> module = (JSTableModel<?>) this.getTable().getModel();
            int iRow = this.getTable().getSelectedRow();
            if (iRow < 0 || iRow >= this.getTable().getRowCount())
                return;

            boolean result = module.insert(this.getTable().convertRowIndexToModel(iRow));
            if (!result) {
                JOptionPane.showMessageDialog(null, "插入数据失败");
                return;
            }

            JSTableColumnModel colModel = (JSTableColumnModel) this.getTable().getColumnModel();

            this.getTable().scrollRowToVisible(iRow);
            if (!module.getEditable()) {
                module.setEditable(true);
            }
            this.getTable().changeSelection(iRow, 0, false, false);
            this.getTable().setEditingRow(iRow);
            this.getTable().editCellAt(iRow, getTable().convertColumnIndexToView(colModel.getFirstEditColumnModelIndex()));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
