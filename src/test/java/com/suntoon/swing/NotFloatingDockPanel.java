package com.suntoon.swing;

import com.javadocking.DockingManager;
import com.javadocking.dock.Position;
import com.javadocking.dock.TabDock;
import com.javadocking.dockable.DefaultDockable;
import com.javadocking.dockable.Dockable;
import com.javadocking.dockable.DockingMode;
import com.javadocking.dockable.DraggableContent;
import com.javadocking.drag.DragListener;
import com.javadocking.model.FloatDockModel;
import com.suntoon.swing.resource.ResourceLoader;

import javax.swing.*;
import java.awt.*;

/**
 * @ProjectionName desktop
 * @ClassName NotFloatingDockPanel
 * @Description 非浮动的停靠tab面板
 * @Author YueLifeng
 * @Date 2019/4/25 0025上午 10:21
 * @Version 1.0
 */
public class NotFloatingDockPanel extends JPanel {

    public static final int FRAME_WIDTH = 600;
    public static final int FRAME_HEIGHT = 450;

    //constructor
    public NotFloatingDockPanel(JFrame frame) {
        super(new BorderLayout());

        FloatDockModel dockModel = new FloatDockModel();
        dockModel.addOwner("frame0", frame);

        DockingManager.setDockModel(dockModel);

        // Create the content components.
        TextPanel textPanel1 = new TextPanel("I am window 1");
        TextPanel textPanel2 = new TextPanel("I am window 2");
        TextPanel textPanel3 = new TextPanel("I am window 3");
        TextPanel textPanel4 = new TextPanel("I am window 4");

        //we don't want the dockables to float.
        int dockingModes = DockingMode.ALL - DockingMode.FLOAT;

        //create the dockable around the content components.
        Icon icon = new ImageIcon(ResourceLoader.getResource(ResourceLoader.IMAGE_OPEN_LAYER));
        Dockable dockable1 = new DefaultDockable("Window1", textPanel1, "Window1", icon, dockingModes);
        Dockable dockable2 = new DefaultDockable("Window2", textPanel2, "Window2", icon, dockingModes);
        Dockable dockable3 = new DefaultDockable("Window3", textPanel3, "Window3", icon, dockingModes);
        Dockable dockable4 = new DefaultDockable("Window4", textPanel4, "Window4", icon, dockingModes);

        //create the tab docks.
        TabDock leftTabDock = new TabDock();
        TabDock rightTabDock = new TabDock();

        //Add the dockable to these tab docks.
        leftTabDock.addDockable(dockable1, new Position(0));
        leftTabDock.addDockable(dockable2, new Position(1));
        rightTabDock.addDockable(dockable3, new Position(0));
        rightTabDock.addDockable(dockable4, new Position(1));

        //Add the 2 root docks to the dock model.
        dockModel.addRootDock("leftTabDock", leftTabDock, frame);
        dockModel.addRootDock("rightTabDock", rightTabDock, frame);

        //create the split panes
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(300);

        //Add the root docks to the split pane.
        splitPane.setLeftComponent(leftTabDock);
        splitPane.setRightComponent(rightTabDock);

        //Add the split pane to the panel.
        add(splitPane, BorderLayout.CENTER);
    }

    //Main method.
    public static void createAndShowGUI() {
        //create the frame.
        JFrame frame = new JFrame("Not floating Dockables");

        //create the panel and add it to the frame.
        NotFloatingDockPanel panel = new NotFloatingDockPanel(frame);
        frame.getContentPane().add(panel);

        //Set the frame properties and show it.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screeSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((screeSize.width - FRAME_WIDTH) / 2, (screeSize.height - FRAME_HEIGHT) / 2);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setVisible(true);
    }

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

    public static void main(String[] args) {
        Runnable doCreateAndShowGUI = new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        };
        SwingUtilities.invokeLater(doCreateAndShowGUI);
    }
}
