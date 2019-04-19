package com.suntoon.swing.table.action;

import com.suntoon.swing.resource.ResourceLoader;
import com.suntoon.swing.table.JSTable;
import com.suntoon.swing.table.JSTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @ProjectionName desktop
 * @ClassName JSTableSaveAction
 * @Description 保存操作
 * @Author YueLifeng
 * @Date 2019/4/19 0019上午 9:38
 * @Version 1.0
 */
public class JSTableSaveAction extends JSTableBaseAction {

    private static final long serialVersionUID = 2834589546104965124L;

    //保存操作
    public JSTableSaveAction(JSTable table) {
        super(table);
        this.putValue(AbstractAction.NAME, "");
        this.putValue(AbstractAction.SMALL_ICON,
                new ImageIcon(ResourceLoader.getResource(ResourceLoader.IMAGE_SAVE)));
        this.putValue(SHORT_DESCRIPTION, "保存修改数据");
    }

    //保存操作
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            JSTableModel<?> module = (JSTableModel<?>) this.getTable().getModel();

            if (module.update()) {
                JOptionPane.showMessageDialog(null, "保存成功");
            } else {
                JOptionPane.showMessageDialog(null, "保存失败");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
