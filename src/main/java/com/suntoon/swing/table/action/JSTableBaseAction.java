package com.suntoon.swing.table.action;

import org.jdesktop.swingx.JXTable;

import javax.swing.*;

/**
 * @ProjectionName desktop
 * @ClassName JSTableBaseAction
 * @Description 所有action对象的抽象基类
 * @Author YueLifeng
 * @Date 2019/4/18 0018下午 3:03
 * @Version 1.0
 */
public abstract class JSTableBaseAction extends AbstractAction {

    private static final long serialVersionUID = -2137655328830377886L;

    /**
     * 当前操作的table对象
     */
    protected JXTable table;

    public JXTable getTable() {
        return table;
    }

    public void setTable(JXTable table) {
        this.table = table;
    }

    /**
     * 所有action对象的抽象基类
     */
    public JSTableBaseAction(JXTable table) {
        super();
        this.setTable(table);
    }
}
