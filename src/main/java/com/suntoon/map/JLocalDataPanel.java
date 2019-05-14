package com.suntoon.map;

import com.suntoon.map.oldaction.OpenLayerAction;
import com.suntoon.map.localtree.JMapTree;
import org.geotools.map.MapContent;
import org.geotools.swing.JMapPane;

import javax.swing.*;
import java.awt.*;

/**
 * @ProjectionName desktop
 * @ClassName JMapPane
 * @Description TODO
 * @Author YueLifeng
 * @Date 2019/5/7 0007下午 2:39
 * @Version 1.0
 */
public class JLocalDataPanel extends JMapPane{

    private static final long serialVersionUID = 682799622168380767L;

    private JMapTree mapTree;

    //地图工具条
    private JToolBar toolBar;

    //地图面板
    private JMapPane mapPane;

    private MapContent mapContent;

    public JLocalDataPanel(JMapPane jMapPane) {
        super();
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        mapPane = jMapPane;
        mapContent = jMapPane.getMapContent();
        initCompenent();
    }

    private void initCompenent() {
        mapTree = new JMapTree();
        JButton btn;
        toolBar = new JToolBar();
        btn = new JButton(new OpenLayerAction(mapPane, mapTree));
        btn.setName("Import GIS Data");
        toolBar.add(btn);

        this.add(toolBar, BorderLayout.NORTH);
        toolBar.setFloatable(false);
        this.add(new JScrollPane(mapTree), BorderLayout.CENTER);
    }

    public MapContent getMapContent() {
        return mapContent;
    }

    public void setMapContent(MapContent mapContent) {
        this.mapContent = mapContent;
    }

    public JMapTree getMapTree() {
        return this.mapTree;
    }

    public void setMapTree(JMapTree mapTree) {
        this.mapTree = mapTree;
    }
}
