package com.suntoon.swing.table.editor;

import java.awt.event.*;
import java.io.Serializable;
import java.util.EventObject;

/**
 * @ProjectionName desktop
 * @ClassName JSEditorDelegateAdapter
 * @Description table.editor对应的事件代理对象
 * @Author YueLifeng
 * @Date 2019/4/23 0023上午 10:33
 * @Version 1.0
 */
public abstract class JSEditorDelegateAdapter implements ActionListener, ItemListener, Serializable {

    private static final long serialVersionUID = -4152162985273984109L;

    //单元格的值
    protected Object value;

    //是否初始化
    protected boolean init = false;

    //启动本操作的次数
    protected int clickCountToStart = 2;

    //当前操作行
    protected int row = -1;

    //当前操作列
    protected int column = -1;

    public int getClickCountToStart() {
        return clickCountToStart;
    }

    public void setClickCountToStart(int clickCountToStart) {
        this.clickCountToStart = clickCountToStart;
    }

    public Object getCellEditorValue() {
        return value;
    }

    public void setCellEditorValue(Object value) {
        this.value = value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * @Author YueLifeng
     * @Description 如果anEvent不是MouseEvent，则返回true。 否则，如果发生了必要的点击次数，则返回true，否则返回false。
     * @Date 下午 2:32 2019/4/23 0023
     * @param anEvent
     * @return boolean  如果单元格已准备好进行编辑，则为true，否则为false
     */
    public boolean isCellEditable(EventObject anEvent) {
        if (anEvent instanceof MouseEvent) {
            return ((MouseEvent) anEvent).getClickCount() >= clickCountToStart;
        }
        return true;
    }

    //返回true表示可以选择编辑单元格。
    public boolean shouldSelectCell(EventObject anEvent) {
        return true;
    }

    //返回true，表示编辑已经开始。
    public boolean startCellEditing(EventObject anEvent) {
        return true;
    }

    //停止编辑并返回true，表示编辑已停止。该方法调用的是fireEditingStopped
    public abstract boolean stopCellEditing();

    //取消编辑，该方法调用的是fireEditingCanceled
    public abstract void cancelCellEditing();

    /**
     * @Author YueLifeng
     * @Description //当某一行为执行时编辑结束
     * @Date 上午 11:37 2019/4/23 0023
     * @param e
     *          行为事件
     * @return void
     */
    public abstract void actionPerformed(ActionEvent e);

    /**
     * @Author YueLifeng
     * @Description //当某项状态改变时，编辑结束
     * @Date 上午 11:35 2019/4/23 0023
     * @param e
     *          行为事件
     * @return void
     */
    public abstract void itemStateChanged(ItemEvent e);
}
