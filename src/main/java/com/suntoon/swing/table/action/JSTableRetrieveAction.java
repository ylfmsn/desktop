package com.suntoon.swing.table.action;

import com.suntoon.swing.resource.ResourceLoader;
import com.suntoon.swing.table.JSTableModel;
import org.jdesktop.swingx.JXTable;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @ProjectionName desktop
 * @ClassName JSTableRetrieveAction
 * @Description 检索操作
 * @Author YueLifeng
 * @Date 2019/4/19 0019上午 9:28
 * @Version 1.0
 */
public class JSTableRetrieveAction extends JSTableBaseAction {

    private static final long serialVersionUID = -8948801795558420788L;

    //检索操作
    public JSTableRetrieveAction(JXTable table) {
        super(table);
        this.putValue(AbstractAction.NAME, "刷新");
        this.putValue(AbstractAction.SMALL_ICON,
                new ImageIcon(ResourceLoader.getResource(ResourceLoader.IMAGE_RETRIEVE)));
        this.putValue(SHORT_DESCRIPTION, "刷新数据");
    }

    //检索操作
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            ((JSTableModel<?>) this.getTable().getModel()).retrieve();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}