package com.suntoon.swing.table.action;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @ProjectionName desktop
 * @ClassName JSTableDefaultAction
 * @Description 空操作action对象
 * @Author YueLifeng
 * @Date 2019/4/18 0018下午 5:40
 * @Version 1.0
 */
public class JSTableDefaultAction extends AbstractAction implements JSTableChooseAction {

    private static final long serialVersionUID = 2328363418795722955L;

    //空操作action对象
    public JSTableDefaultAction() {
        super();
        this.putValue(NAME, ".");
    }

    private Object value;

    @Override
    public Result getResult() {
        return Result.Cancle;
    }

    @Override
    public void setResult(Result result) {

    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public void setValue(Object vlaue) {
        this.value = value;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
