package com.suntoon.test.table;

import org.sam.swing.table.JSTable;
import org.sam.swing.table.JSTableColumn;
import org.sam.swing.table.JSTableColumnModel;
import org.sam.swing.table.JSTableModel;
import org.sam.swing.table.renderer.JSTableRowNumberRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.Collection;

/**
 * table表格方法
 * 
 * @author sam
 *
 */
public class JFrameTableDemo extends JFrame {

	private static final long serialVersionUID = 8818584079585682536L;

	/**
	 *
	 */
	public JFrameTableDemo() {
		super();
		initCompents();
	}

	/**
	 * 当前的table
	 */
	private JSTable table;

	/**
	 * tablemodel
	 */
	private JSTableModel<Collection<TestEntity>> tableModel;

	/**
	 * colmodel
	 */
	private JSTableColumnModel colModel;

	/**
	 * 初始化控件的操作
	 */
	/**
	 * 
	 */
	protected void initCompents() {
		JPanel panel = new JPanel(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		DefaultTableCellRenderer renderC = new DefaultTableCellRenderer();
		renderC.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

		DefaultTableCellRenderer renderR = new DefaultTableCellRenderer();
		renderR.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);

		Object[] columnNames = {"姓名", "语文", "数学", "英语", "总分"};

		for (int i = 0; i < columnNames.length; i++) {
			JSTableColumn col0 = new JSTableColumn();
			col0.setIdentifier(columnNames[i]);
			col0.setTitle(columnNames[i].toString());
			col0.setHeaderValue(columnNames[i].toString());
			col0.setModelIndex(0);
			col0.setEditable(false);
			col0.setMaxWidth(30);
			JSTableRowNumberRenderer rownNumRender = new JSTableRowNumberRenderer();
			rownNumRender.setHorizontalAlignment(JSTableRowNumberRenderer.CENTER);
			col0.setCellRenderer(rownNumRender); // 行号渲染器
		}



		panel.add(new JScrollPane(table), BorderLayout.CENTER);
		this.add(panel, BorderLayout.CENTER);



	}

	/**
	 * 生成操作菜单
	 * 
	 * @return
	 */
	public JPopupMenu createMenu() {

		JPopupMenu menu = new JPopupMenu();
		JMenuItem item1 = new JMenuItem("样式设置");
		menu.add(item1);

		return menu;
	}

}
