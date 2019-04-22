package com.suntoon.swing.table.tools;

import com.suntoon.swing.JSFindBar;
import com.suntoon.swing.JSPanelToolBar;
import com.suntoon.swing.table.JSTable;
import com.suntoon.swing.table.action.*;

import javax.swing.*;

/**
 * @ProjectionName desktop
 * @ClassName JSTableToolBar
 * @Description 给JSTable增加工具条
 * @Author YueLifeng
 * @Date 2019/4/22 0022下午 2:26
 * @Version 1.0
 */
public class JSTableToolBar extends JSPanelToolBar {

    private static final long serialVersionUID = -5793491647918673440L;

    //当前操作的table
    private JSTable table;

    public JSTable getTable() {
        return table;
    }

    public void setTable(JSTable table) {
        this.table = table;
    }

    public JSTableToolBar(JSTable table) {
        this.setTable(table);
    }

    //刷新
    public void enableRetrieve() {
        this.addAction(new JSTableRetrieveAction(table));
    }

    //全选，不全选 默认操作第一列 select：Integer.Valueof(1) unSelect：Integer.Valueof(0)
    public void enableSelect() {
        this.addAction(new JSTableSelectAction(table));
        this.addAction(new JSTableUnSelectAction(table));
        this.addAction(new JSTableInvertSelectAction(table));
    }

    /**
     * @Author YueLifeng
     * @Description //全选，不全选 select：Integer.Valueof(1) unSelect：Integer.Valueof(0)
     * @Date 下午 2:39 2019/4/22 0022
     * @param editColIndex
     *          操作列索引
     * @return void
     */
    public void enableSelect(int editColIndex) {
        JSTableSelectAction selectAction = new JSTableSelectAction(table);
        selectAction.setEditColIndex(editColIndex);
        this.addAction(selectAction);

        JSTableUnSelectAction unSelectAction = new JSTableUnSelectAction(table);
        unSelectAction.setEditColIndex(editColIndex);
        this.addAction(unSelectAction);

        JSTableInvertSelectAction invertSelectAction = new JSTableInvertSelectAction(table);
        invertSelectAction.setEditColIndex(editColIndex);
        this.addAction(invertSelectAction);
    }

    /**
     * @Author YueLifeng
     * @Description //全选 全不选
     * @Date 下午 3:09 2019/4/22 0022
     * @param editColIndex
     *          操作列索引
     * @param selectValue
     *          选中值
     * @param unselectValue
     *          非选中值
     * @return void
     */
    public void enableSelect(int editColIndex, Object selectValue, Object unselectValue) {
        JSTableSelectAction selectAction = new JSTableSelectAction(table);
        selectAction.setEditColIndex(editColIndex);
        selectAction.setSelectedValue(selectValue);
        this.addAction(selectAction);

        JSTableUnSelectAction unSelectAction = new JSTableUnSelectAction(table);
        unSelectAction.setEditColIndex(editColIndex);
        unSelectAction.setUnSelectedValue(unselectValue);
        this.addAction(unSelectAction);

        JSTableInvertSelectAction invertSelectAction = new JSTableInvertSelectAction(table);
        invertSelectAction.setEditColIndex(editColIndex);
        invertSelectAction.setSelectedValue(selectValue);
        invertSelectAction.setUnSelectedValue(unselectValue);
        this.addAction(invertSelectAction);
    }

    /**
     * @Author YueLifeng
     * @Description //导入操作
     * @Date 下午 3:11 2019/4/22 0022
     * @param fileImportActionListener
     *          回调接口
     * @return void
     */
    public void enableImport(JSFileImportActionListener fileImportActionListener) {
        JSTableImportAction importAction = new JSTableImportAction(table);
        importAction.addFileImportActionListener(fileImportActionListener);
        this.addAction(importAction);
    }

    /**
     * @Author YueLifeng
     * @Description //编辑
     * @Date 下午 4:30 2019/4/22 0022
     * @param
     * @return void
     */
    public void enableEdit() {
        this.addAction(new JSTableEditAction(table));
    }

    /**
     * @Author YueLifeng
     * @Description //新增
     * @Date 下午 4:31 2019/4/22 0022
     * @param
     * @return void
     */
    public void enableAdd() {
        this.addAction(new JSTableAppendAction(table));
    }

    /**
     * @Author YueLifeng
     * @Description //删除
     * @Date 下午 4:32 2019/4/22 0022
     * @param
     * @return void
     */
    public void enableDel() {
        this.addAction(new JSTableDeleteAction(table));
    }

    /**
     * @Author YueLifeng
     * @Description //保存
     * @Date 下午 4:33 2019/4/22 0022
     * @param
     * @return void
     */
    public void enableSave() {
        this.addAction(new JSTableSaveAction(table));
    }

    public void enableSearch() {
        this.add(buildSearchBar());
    }

    /**
     * @Author YueLifeng
     * @Description //使用jxtable自带的searchbar
     * @Date 下午 4:34 2019/4/22 0022
     * @param
     * @return javax.swing.JComponent
     */
    private JComponent buildSearchBar() {
        JSFindBar searchBar = new JSFindBar(table.getSearchable(), table.getModel(), table.getColumnModel());
        return searchBar;
    }
}
