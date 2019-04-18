package com.suntoon.swing.table;

import java.util.EventObject;

/**
 * @ProjectionName desktop
 * @ClassName JSTableModelEvent
 * @Description JXTableModle的时间参数对象
 * @Author YueLifeng
 * @Date 2019/4/18 0018上午 9:20
 * @Version 1.0
 */
public class JSTableModelEvent extends EventObject {

    private static final long serialVersionUID = -2264933133557562986L;

    //是否取消下一步骤操作
    private boolean cancel = false;

    //当前操作的行号
    private int row = -1;

    //操作结果
    private boolean result = true;

    //附加的消息
    private String msg;

    //当前操作的tablemodel对象
    private JSTableModel<?> tableModel;

    public JSTableModel<?> getJSTableModel() {
        return tableModel;
    }

    public void setJSTableModel(JSTableModel<?> tableModel) {
        this.tableModel = tableModel;
    }

    //是否取消下一步的操作
    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

    //当前操作的行号
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    //操作的结果
    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    //附加的消息
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @Author YueLifeng
     * @Description //JXTableModel的参数对象
     * @Date 上午 9:33 2019/4/18 0018
     * @param source
     * @return
     */
    public JSTableModelEvent(Object source) {
        super(source);
        this.setJSTableModel((JSTableModel<?>) source);
    }
}
