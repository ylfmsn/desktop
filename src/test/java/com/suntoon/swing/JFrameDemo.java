package com.suntoon.swing;

import org.jdesktop.swingx.JXFrame;
import org.jdesktop.swingx.JXRadioGroup;
import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;

import javax.swing.*;
import java.awt.*;

/**
 * @ProjectionName desktop
 * @ClassName JFrameDemo
 * @Description TODO
 * @Author YueLifeng
 * @Date 2019/4/25 0025上午 9:29
 * @Version 1.0
 */
public class JFrameDemo extends JXFrame {

    private static final long serialVersionUID = -5144680553069404438L;

    //初始化窗口对象
    public JFrameDemo() {
        super();
        this.initCompents();
    }

    //分屏对象
    private JSplitPane splitPane;

    //表格panel
    private JXTaskPane tablePane;

    //控件panel
    private JXTaskPane controlPane;

    //关于panel
    private JXTaskPane aboutPane;

    //主界面窗口
    private JPanel mainPane;

    //单选按钮组控件
    private JXRadioGroup<String> rgGroup;

    //初始化所有的控件信息
    protected void initCompents() {
        //初始化窗口
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("SUNTOON desktop Demos");
        this.setSize(1024, 768);
        this.setLocationRelativeTo(null);    //居中显示
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);     //默认最大化

        //outlook bar对象容器
        JXTaskPaneContainer tpc = new JXTaskPaneContainer();

        //第一个分栏
        tablePane = new JXTaskPane("表格演示");
        tpc.add(tablePane);

        //控件分隔条
        controlPane = new JXTaskPane("控件测试");
        tpc.add(controlPane);

        //关于
        aboutPane = new JXTaskPane("关于");
        JEditorPane area = new JEditorPane("text/html", "<html>");
        area.setText("<html> <span>本程序基于swingx 1.6.5-1 为基础进行的二次封装</span> </html>");
        aboutPane.add(area);
        aboutPane.setCollapsed(true);    //不展开显示
        tpc.add(aboutPane);

        // 套入分隔条
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(tpc),
                mainPane = new JPanel(new BorderLayout()));

        //加入一个单选按钮组
        rgGroup = new JXRadioGroup<>(new String[]{"已婚", "未婚", "离异", "丧偶"});
        rgGroup.setLayout(new BoxLayout(rgGroup, BoxLayout.Y_AXIS));
        mainPane.add(rgGroup, BorderLayout.CENTER);
        this.add(splitPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        JFrameDemo frame = new JFrameDemo();
        frame.setVisible(true);
    }
}
