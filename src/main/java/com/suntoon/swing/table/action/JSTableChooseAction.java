package com.suntoon.swing.table.action;

import javax.swing.*;

/**
 * @ProjectionName desktop
 * @ClassName JSTableChooseAction
 * @Description 选择按钮等editor控件用的到action对象
 * @Author YueLifeng
 * @Date 2019/4/18 0018下午 5:36
 * @Version 1.0
 */
public interface JSTableChooseAction extends Action {

    //执行的结果
    public enum Result {
        OK, Cancle;
    }

    //获取当前执行的结果
    public Result getResult();

    //设置当前执行结果
    public void setResult(Result result);

    //获取设置后的值
    public Object getValue();

    //设置值，用于初始化信息等
    public void setValue(Object vlaue);
}
