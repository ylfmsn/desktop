package com.suntoon.swing;

import com.suntoon.swing.search.MuiltyPatternModel;
import com.suntoon.swing.table.JSTableColumn;
import org.jdesktop.swingx.JXDialog;
import org.jdesktop.swingx.search.PatternModel;
import org.jdesktop.swingx.search.Searchable;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ProjectionName desktop
 * @ClassName JSFindBar
 * @Description 扩展的增强搜索栏 主要增加字段选择列表：全部 + 可见字段列表 +号按钮，多条件窗口 apply按钮，接受条件
 * @Author YueLifeng
 * @Date 2019/4/16 0016上午 9:24
 * @Version 1.0
 */
public class JSFindBar extends JSFindPanel {

    private static final long serialVersionUID = 1594690163014862566L;

    protected Color previousBackgroundColor;

    protected Color previousForegroundColor;

    //从UIManager读取
    protected Color notFoundBackgroundColor = Color.decode("#FF6666");

    protected Color notFoundForegroundColor = Color.white;

    protected JButton findNext;

    protected JButton findPrevious;

    //当前的tablemodel
    private TableModel tabelModel;

    //当前列的model
    private TableColumnModel colModel;

    public TableModel getTabelModel() {
        return tabelModel;
    }

    public void setTabelModel(TableModel tabelModel) {
        this.tabelModel = tabelModel;
    }

    public TableColumnModel getColModel() {
        return colModel;
    }

    public void setColModel(TableColumnModel colModel) {
        this.colModel = colModel;
    }

    //字段列表
    protected JSComboBox<String, String> columsList;

    public JSFindBar() {
        this(null);
    }

    public JSFindBar(Searchable searchable) {
        this(searchable, null, null);
    }

    public JSFindBar(Searchable searchable, TableModel tabelModel, TableColumnModel colModel) {
        super(searchable);
        getPatternModel().setIncremental(true);
        getPatternModel().setWrapping(true);
        this.setTabelModel(tabelModel);
        this.setColModel(colModel);
    }

    @Override
    public void setSearchable(Searchable searchable) {
        super.setSearchable(searchable);
        match();
    }

    /**
     * @Author YueLifeng
     * @Description //文本字段设置为找不到的颜色时
     * @Date 上午 9:43 2019/4/16 0016
     * @param
     * @return void
     */
    @Override
    protected void showNotFoundMessage() {
        // JW: quick hack around #487-swingx - NPE in setSearchable
        if (searchField == null)
            return;
        searchField.setForeground(notFoundForegroundColor);
        searchField.setBackground(notFoundBackgroundColor);
    }

    /**
     * @Author YueLifeng
     * @Description //将文本字段的颜色设置为normal
     * @Date 上午 9:46 2019/4/16 0016
     * @param
     * @return void
     */
    @Override
    protected void showFoundMessage() {
        // JW: quick hack around #487-swingx - NPE in setSearchable
        if (searchField == null)
            return;
        searchField.setBackground(previousBackgroundColor);
        searchField.setForeground(previousForegroundColor);
    }

    @Override
    public void addNotify() {
        super.addNotify();
        if (previousBackgroundColor == null) {
            previousBackgroundColor = searchField.getBackground();
            previousForegroundColor = searchField.getForeground();
        } else {
            searchField.setBackground(previousBackgroundColor);
            searchField.setForeground(previousForegroundColor);
        }
    }

    // --------------------------- oldaction call back
    /**
     * Action callback method for bound oldaction JXDialog.CLOSE_ACTION_COMMAND.
     *
     * Here: does nothing. Subclasses can override to define custom "closing"
     * behaviour. Alternatively, any client can register a custom oldaction with
     * the actionMap.
     *
     *
     */
    public void cancel() {
    }

    // init


    @Override
    protected void initExecutables() {
        getActionMap().put(JXDialog.CLOSE_ACTION_COMMAND, createBoundAction(JXDialog.CLOSE_ACTION_COMMAND, "cancel"));
        super.initExecutables();
    }

    @Override
    protected void bind() {
        super.bind();
        searchField.addActionListener(getAction(JXDialog.EXECUTE_ACTION_COMMAND));
        findNext.setAction(getAction(FIND_NEXT_ACTION_COMMAND));
        findPrevious.setAction(getAction(FIND_PREVIOUS_ACTION_COMMAND));
        KeyStroke stroke = KeyStroke.getKeyStroke("ESCAPE");
        getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(stroke, JXDialog.CLOSE_ACTION_COMMAND);
    }

    @Override
    protected void build() {
        setLayout(new FlowLayout(SwingConstants.LEADING));
        add(searchLabel);
        add(new JLabel(":"));
        add(new JLabel("  "));

        //字段列表
        add(columsList);
        add(searchField);
        add(findNext);
        add(findPrevious);
    }

    //重写检索model对象
    @Override
    protected PatternModel createPatternModel() {
        return new MuiltyPatternModel();
    }

    @Override
    protected void initComponents() {
        super.initComponents();
        findNext = new JButton();
        findPrevious = new JButton();

        //初始化列表
        Map<String, String> colNames = new LinkedHashMap<>();
        colNames.put("", "全部");

        if (this.getColModel() != null) {
            Enumeration<TableColumn> columns = this.getColModel().getColumns();
            while (columns.hasMoreElements()) {
                TableColumn column = columns.nextElement();
                if (column == null)
                    continue;

                if (column instanceof JSTableColumn) {
                    JSTableColumn col = (JSTableColumn) column;
                    if (col.getIdentifier() == null || colNames.toString().length() <= 0)
                        continue;

                    colNames.put(col.toString(), col.getTitle());
                }
            }
        }

        columsList = new JSComboBox<>(colNames.values().toArray(new String[colNames.size()]),
                colNames.keySet().toArray(new String[colNames.size()]));
    }
}
