package com.suntoon.swing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * @ProjectionName desktop
 * @ClassName JSDateTimeDialog
 * @Description //弹出窗口形式的日期时间对话框
 * @Author YueLifeng
 * @Date 2019/4/15 0015下午 5:31
 * @Version 1.0
 */
public class JSDateTimeDialog extends JSDialog implements ActionListener {

    private static final long serialVersionUID = -4814693171348063462L;

    //日期时间区域
    private JSDateTimePanel dtPanel;

    //确定按钮
    private JButton okButton;

    //当前时间
    public Date currentDate;

    public Date getCurrentDate() {
        return currentDate;
    }

    private void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    /**
     * @Author YueLifeng
     * @Description //带有初始化日期的对话框
     * @Date 下午 5:54 2019/4/15 0015
     * @param date
     * @return
     */
    public JSDateTimeDialog(Date date) {
        super();
        this.setModal(true);
        this.setTitle("请选择日期");
        this.setSize(430, 330);
        this.setResizable(false);
        this.setCurrentDate(date);
        this.setLayout(new BorderLayout());
        dtPanel = new JSDateTimePanel(date);
        this.add(dtPanel, BorderLayout.CENTER);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new FlowLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.SOUTH);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);

            {
                okButton = new JButton("确定");
                okButton.setActionCommand(OK);
                buttonPane.add(okButton);
                okButton.addActionListener(this);
                getRootPane().setDefaultButton(okButton);
            }
            {
                JButton cancelButton = new JButton("取消");
                cancelButton.setActionCommand(CANCEL);
                cancelButton.addActionListener(this);
                buttonPane.add(cancelButton);
            }
        }

        this.setLocationRelativeTo(null); // 屏幕居中
    }

    /**
     * @Author YueLifeng
     * @Description //使用当前的日期对话框
     * @Date 下午 5:55 2019/4/15 0015
     * @param
     * @return
     */
    public JSDateTimeDialog() {
        this(Calendar.getInstance().getTime());
    }

    //按钮点击事件
    @Override
    public void actionPerformed(ActionEvent e) {
        if (OK.equals(e.getActionCommand())) {
            this.setAction(OK);
            try {
                this.setCurrentDate(dtPanel.getCurrentDate());
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
            this.setVisible(false);
            this.dispose();
        } else if (CANCEL.equals(e.getActionCommand())) {
            this.setAction(CANCEL);
            this.setVisible(false);
            this.dispose();
        }
    }
}
