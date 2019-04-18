package com.suntoon.swing.table.action;

import com.suntoon.swing.resource.ResourceLoader;
import com.suntoon.swing.table.JSTableColumnModel;
import com.suntoon.swing.table.JSTableModel;
import org.jdesktop.swingx.JXTable;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @ProjectionName desktop
 * @ClassName JSTableAppendAction
 * @Description TODO
 * @Author YueLifeng
 * @Date 2019/4/18 0018下午 5:19
 * @Version 1.0
 */
public class JSTableAppendAction extends JSTableBaseAction {

    private static final long serialVersionUID = -3068593608658058519L;

    public JSTableAppendAction(JXTable table) {
        super(table);
        this.putValue(AbstractAction.NAME, "");
        this.putValue(AbstractAction.SMALL_ICON,
                new ImageIcon(ResourceLoader.getResource(ResourceLoader.IMAGE_ADD_SMALL)));
        this.putValue(SHORT_DESCRIPTION, "追加数据");
    }

    /**
     * @Author YueLifeng
     * @Description //追加数据
     * @Date 下午 5:22 2019/4/18 0018
     * @param e
     * @return void
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            JSTableModel<?> module = (JSTableModel<?>) this.getTable().getModel();
            boolean result = module.append();
            if (!result) {
                JOptionPane.showMessageDialog(null, "追加数据失败");
                return;
            }
            int iRow = this.getTable().convertRowIndexToView(this.getTable().getRowCount() - 1);
            this.getTable().scrollRowToVisible(iRow);
            if (!module.getEditable()) {
                module.setEditable(true);
            }

            JSTableColumnModel colModel = (JSTableColumnModel) this.getTable().getColumnModel();

            this.getTable().changeSelection(iRow, 0, false, false);
            this.getTable().setEditingRow(iRow);
            this.getTable().editCellAt(iRow,
                    getTable().convertColumnIndexToView(colModel.getFirstEditColumnModelIndex()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
