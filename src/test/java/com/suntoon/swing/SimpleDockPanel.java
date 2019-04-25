package com.suntoon.swing;

import com.javadocking.DockingManager;
import com.javadocking.dock.SingleDock;
import com.javadocking.dockable.DefaultDockable;
import com.javadocking.dockable.Dockable;
import com.javadocking.dockable.DraggableContent;
import com.javadocking.drag.DragListener;
import com.javadocking.model.FloatDockModel;

import javax.swing.*;
import java.awt.*;

/**
 * @ProjectionName desktop
 * @ClassName SimpleDockPanel
 * @Description 可拖拽脱离主面板/停靠主面板
 * @Author YueLifeng
 * @Date 2019/4/25 0025上午 10:09
 * @Version 1.0
 */
public class SimpleDockPanel extends JPanel {

    public static final int FRAME_WIDTH = 500;
    public static final int FRAME_HEIGHT = 400;

    //构造器
    public SimpleDockPanel(JFrame frame) {

        super(new BorderLayout());

        //为dock创建dock模型
        FloatDockModel dockModel = new FloatDockModel();
        dockModel.addOwner("frame0", frame);

        //将dock模型交给docking管理器
        DockingManager.setDockModel(dockModel);

        //创建内容组件
        TextPanel textPanel = new TextPanel("I am window 1.");

        // Create the dockable around the content component.
        Dockable dockable = new DefaultDockable("Window1", textPanel, "Window");

        // Create the single dock.
        SingleDock singleDock = new SingleDock();

        // Add the dockables to these tab docks.
        singleDock.addDockable(dockable, SingleDock.SINGLE_POSITION);

        // Add the root dock to the dock model.
        dockModel.addRootDock("singledock", singleDock, frame);

        // Add the dock to the panel.
        add(singleDock, BorderLayout.CENTER);
    }


    //This is the class for the content
    private class TextPanel extends JPanel implements DraggableContent {

        private JLabel label;

        public TextPanel(String text) {
            super(new FlowLayout());

            //the panel
            setMinimumSize(new Dimension(80, 80));
            setPreferredSize(new Dimension(150, 150));
            setBackground(Color.white);
            setBorder(BorderFactory.createLineBorder(Color.lightGray));

            //the label
            label = new JLabel(text);
            label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            add(label);
        }

        @Override
        public void addDragListener(DragListener dragListener) {
            addMouseListener(dragListener);
            addMouseMotionListener(dragListener);
            label.addMouseListener(dragListener);
            label.addMouseMotionListener(dragListener);
        }
    }

    public static void createAndShowGUI() {

        // Create the frame.
        JFrame frame = new JFrame("Simple Dockable");

        // Create the panel and add it to the frame.
        SimpleDockPanel panel = new SimpleDockPanel(frame);
        frame.getContentPane().add(panel);

        // Set the frame properties and show it.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((screenSize.width - FRAME_WIDTH) / 2, (screenSize.height - FRAME_HEIGHT) / 2);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        Runnable doCreateAndShowGUI = new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        };
        SwingUtilities.invokeLater(doCreateAndShowGUI);
    }
}
