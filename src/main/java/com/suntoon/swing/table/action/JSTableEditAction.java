package com.suntoon.swing.table.action;

import com.suntoon.swing.resource.ResourceLoader;
import com.suntoon.swing.table.JSTableModel;
import org.jdesktop.swingx.JXTable;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @ProjectionName desktop
 * @ClassName JSTableEditAction
 * @Description 编辑操作
 * @Author YueLifeng
 * @Date 2019/4/18 0018下午 6:10
 * @Version 1.0
 */
public class JSTableEditAction extends JSTableBaseAction {

    private static final long serialVersionUID = 2834589546104965124L;

    public JSTableEditAction(JXTable table) {
        super(table);
        this.putValue(AbstractAction.NAME, "");
        this.putValue(AbstractAction.SMALL_ICON,
                new ImageIcon(ResourceLoader.getResource(ResourceLoader.IMAGE_MODIFY)));
        this.putValue(SHORT_DESCRIPTION, "编辑");
    }

    //编辑
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            JSTableModel<?> module = (JSTableModel<?>) this.getTable().getModel();
            module.setEditable(!module.getEditable());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
