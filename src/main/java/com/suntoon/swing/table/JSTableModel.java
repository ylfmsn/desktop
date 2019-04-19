package com.suntoon.swing.table;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @ProjectionName desktop
 * @ClassName JSTableModel
 * @Description 扩展的TableModule对象 最终生成类的父类 采用模版模式
 * @Author YueLifeng
 * @Date 2019/4/17 0017下午 4:46
 * @Version 1.0
 */
public abstract class JSTableModel<T> extends DefaultTableModel implements TableModelListener {

    private static final long serialVersionUID = 5129544725258528221L;

    //无数据行的构造函数
    public JSTableModel() {
        super();
    }

    //带参数的构造器
    public JSTableModel(JSTableColumn[] cols) {
        super(0, cols.length);
        this.setColumnIdentifiers(cols);
    }

    //原始的数据集合
    public abstract T getOriginal();

    public abstract void setOriginal(T original);

    //已删除的数据集合
    public abstract T getDeletes();

    public abstract void setDeletes(T deletes);

    //新增数据集合
    public abstract T getCreates();

    public abstract void setCreates(T creates);

    //更新的数据集合
    public abstract T getModified();

    public abstract void setModified(T modified);

    //清空所有缓存区
    public abstract void resetUpdate();

    /**
     * @Author YueLifeng
     * @Description //初始化数据的操作
     * @Date 下午 4:55 2019/4/17 0017
     * @param //0无数据 -1出错 成功返回行数
     * @return int
     */
    public abstract int onRetrieve() throws Exception;

    /**
     * @Author YueLifeng
     * @Description //删除时候执行的操作
     * @Date 下午 4:56 2019/4/17 0017
     * @param moduleRow
     * @return boolean
     */
    public abstract boolean onDelete(int moduleRow) throws Exception;

    /**
     * @Author YueLifeng
     * @Description //插入的时候执行的操作
     * @Date 下午 4:57 2019/4/17 0017
     * @param moduleRow
     * @return boolean
     */
    public abstract boolean onInsert(int moduleRow) throws Exception;

    /**
     * @Author YueLifeng
     * @Description //当执行追加的时候执行的操作
     * @Date 下午 4:58 2019/4/17 0017
     * @param
     * @return boolean
     */
    public abstract boolean onAppend() throws Exception;

    /**
     * @Author YueLifeng
     * @Description //获取列表
     * @Date 下午 4:59 2019/4/17 0017
     * @param
     * @return com.suntoon.swing.table.JSTableColumn[]
     */
    public JSTableColumn[] getTableColumns() {
        return (JSTableColumn[]) this.columnIdentifiers.toArray(new JSTableColumn[this.getColumnCount()]);
    }

    //快速检索模式，默认不开启，主要是retrieve的时候，不切换数据库
    private boolean quickRetrieveModel = false;

    //快速检索模式，默认不开启 主要是retrieve的时候，不切换数据库
    public boolean isQuickRetrieveModel() {
        return quickRetrieveModel;
    }

    //快速检索模式，默认不开启 主要是retrieve的时候，不切换数据库
    public void setQuickRetrieveModel(boolean quickRetrieveModel) {
        this.quickRetrieveModel = quickRetrieveModel;
    }

    //是否编辑状态
    private Boolean editable = true;

    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    //是否在retrieve的时候加入空白行
    private boolean retrieveWithEmptyRow = false;

    public boolean isRetrieveWithEmptyRow() {
        return retrieveWithEmptyRow;
    }

    public void setRetrieveWithEmptyRow(boolean retrieveWidthEmptyRow) {
        this.retrieveWithEmptyRow = retrieveWidthEmptyRow;
    }

    //保护起来的单元格 存储格式为行列
    private Map<Integer, Set<Integer>> protectCell = new LinkedHashMap<Integer, Set<Integer>>();

    public Map<Integer, Set<Integer>> getProtectCell() {
        return protectCell;
    }

    public void setProtectCell(Map<Integer, Set<Integer>> protectCell) {
        this.protectCell = protectCell;
    }

    //注入的tablemodel对象
    private JSTableModelListener<T> tableModelListener;

    public JSTableModelListener<T> getTableModelListener() {
        return tableModelListener;
    }

    public void setTableModelListener(JSTableModelListener<T> tableModelListener) {
        this.tableModelListener = tableModelListener;
    }

    /**
     * @Author YueLifeng
     * @Description //重写的返回字段是否可编辑的功能
     * @Date 上午 10:39 2019/4/18 0018
     * @param row
     * @param column
     * @return boolean
     */
    @Override
    public boolean isCellEditable(int row, int column) {

        if (!this.getEditable()) {
            return false;
        }

        if (protectCell != null && protectCell.containsKey(row)) {
            if (protectCell.get(row).contains(column)) {
                return false;
            }
        }

        if (this.columnIdentifiers != null && this.columnIdentifiers.size() > column) {
            if (this.columnIdentifiers.get(column) instanceof JSTableColumn) {
                return ((JSTableColumn) this.columnIdentifiers.get(column)).isEditable();
            }
        }

        return true;
    }

    //查询是否需要更新的操作
    public abstract boolean hasChange();

    /**
     * @Author YueLifeng
     * @Description //交换2行数据，无保存状态交互数据
     * @Date 上午 10:46 2019/4/18 0018
     * @param srcRow
     * @param tarRow
     * @return void
     */
    public abstract void moveRow(int srcRow, int tarRow) throws Exception;

    /**
     * @Author YueLifeng
     * @Description //收集当前变更后的数据集合
     * @Date 上午 10:49 2019/4/18 0018
     * @param
     * @return T
     */
    public abstract T getDatas() throws Exception;

    /**
     * @Author YueLifeng
     * @Description //重建索引
     * @Date 上午 10:51 2019/4/18 0018
     * @param colIndex
     *          列索引
     * @param begin
     *          开始值，比如1，0作为索引开始值
     * @param seed
     *          增速
     * @return void
     */
    public abstract void reBuildIndex(int colIndex, int begin, int seed) throws Exception;

    /**
     * @Author YueLifeng
     * @Description //返回当前选中行的数据
     * @Date 下午 2:02 2019/4/18 0018
     * @param modelRow
     *          模式行
     * @return java.lang.Object
     */
    public abstract Object getData(int modelRow) throws Exception;

    public Object[] getCellData(int modelRow) throws Exception {

        int iOriginal = this.findColumn(JSTableColumn.COLUMN_ORIGINAL);
        if (iOriginal < 0) {
            throw new Exception("Not include original data");
        }

        if (modelRow < 0 || modelRow >= this.getRowCount()) {
            throw new Exception("modelRow over index");
        }

        Object[] datas = new Object[this.getColumnCount()];

        for (int i = 0; i < this.getColumnCount(); i++) {
            datas[i] = this.getValueAt(modelRow, i);
        }

        return datas;
    }

    /**
     * @Author YueLifeng
     * @Description //新生成一个数据，但不插入集合
     * @Date 下午 2:07 2019/4/18 0018
     * @param
     * @return java.lang.Object[]  新生成一个数据集合
     */
    public abstract Object[] createNew() throws Exception;

    public int findIndexOf(Object data) throws Exception {
        int rowCount = this.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            if (this.getData(i) == data) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @Author YueLifeng
     * @Description //清空所有当前显示数据，并且清空所有缓冲区
     * @Date 下午 2:10 2019/4/18 0018
     * @param
     * @return void
     */
    public abstract void clear() throws Exception;

    /**
     * @Author YueLifeng
     * @Description //使用泛型类型插入一行数据
     * @Date 下午 2:11 2019/4/18 0018
     * @param row
     *          要插入行的位置
     * @param t
     *          数据
     * @return void
     */
    public abstract void insert(int row, Object t) throws Exception;

    /**
     * @Author YueLifeng
     * @Description //替换掉行的数据操作，但是不记录到系统里
     * @Date 下午 2:12 2019/4/18 0018
     * @param row
     *          行
     * @param t
     *          数据
     * @return void
     */
    public abstract void replace(int row, Object t) throws Exception;

    /**
     * @Author YueLifeng
     * @Description //清空数据显示
     * @Date 下午 2:13 2019/4/18 0018
     * @param
     * @return void
     */
    public void removeAll() throws Exception {
        for (int i = this.getColumnCount() - 1; i >= 0; i--) {
            this.removeRow(i);
        }
    }

    /**
     * @Author YueLifeng
     * @Description //更新一个单元格数据，但是不记录到系统里
     * @Date 下午 2:16 2019/4/18 0018
     * @param aValue
     *          新值
     * @param row
     *          行号
     * @param column
     *          列
     * @return void
     */
    public void updateValueAt(Object aValue, int row, int column) throws Exception {
        this.removeTableModelListener(this);
        try {
            this.setValueAt(aValue, row, column);
        } finally {
            this.addTableModelListener(this);
        }
    }

    /**
     * @Author YueLifeng
     * @Description //获取一列的数据
     * @Date 下午 2:21 2019/4/18 0018
     * @param colIndex
     *          列所在的model索引位置
     * @param beginRow
     *          开始行
     * @param endRow
     *          结束行
     * @return java.lang.Object[]
     */
    public Object[] getColData(int colIndex, int beginRow, int endRow) throws Exception {

        if (colIndex < 0 || colIndex >= this.getColumnCount())
            throw new Exception("column over index");

        if (beginRow < 0 || endRow >= this.getColumnCount())
            throw new Exception("beginRow over index");

        if (endRow < 0 || endRow >= this.getColumnCount())
            throw new Exception("endRow over index");

        if (beginRow > endRow)
            throw new Exception("beginRow more than endRow");

        Object[] result = new Object[endRow - beginRow + 1];
        for (int i = beginRow; i <= endRow; i++)
            result[i] = this.getValueAt(i, colIndex);

        return result;
    }

    /**
     * @Author YueLifeng
     * @Description //更新操作
     * @Date 下午 2:24 2019/4/18 0018
     * @param
     * @return boolean
     */
    public boolean update() throws Exception {

        JSTableModelEvent event = new JSTableModelEvent(this);

        this.getTableModelListener().beforeUpdate(event);
        if (event.isCancel() || !event.getResult()) {
            return false;
        }

        if (!this.getTableModelListener().update(event)) {
            return false;
        }

        event.setCancel(false);
        event.setResult(true);
        this.getTableModelListener().afterUpdate(event);

        return true;
    }

    /**
     * @Author YueLifeng
     * @Description //加载数据的操作
     * @Date 下午 2:27 2019/4/18 0018
     * @param
     * @return int  负数出错 其他返回的加载数据行数
     */
    public int retrieve() throws Exception {

        this.removeTableModelListener(this);
        try {
            JSTableModelEvent event = new JSTableModelEvent(this);

            this.getTableModelListener().beforeRetrieve(event);
            if (event.isCancel() || !event.getResult()) {
                return -1;
            }

            int iResult = onRetrieve();

            event.setCancel(false);
            event.setRow(iResult);
            event.setResult(iResult >= 0);
            this.getTableModelListener().afterRetrieve(event);

            return iResult;
        } finally {
            this.addTableModelListener(this);
        }
    }

    /**
     * @Author YueLifeng
     * @Description //删除操作
     * @Date 下午 2:29 2019/4/18 0018
     * @param modelRow
     *          要删除的table model数据行
     * @return boolean
     */
    public boolean delete(int modelRow) throws Exception {

        JSTableModelEvent event = new JSTableModelEvent(this);
        event.setRow(modelRow);

        this.getTableModelListener().beforeDelete(event);
        if (event.isCancel() || !event.getResult())
            return false;

        if (!this.onDelete(modelRow))
            return false;

        event.setCancel(false);
        event.setResult(true);
        this.getTableModelListener().afterDelete(event);

        return true;
    }

    /**
     * @Author YueLifeng
     * @Description //插入一行数据的操作
     * @Date 下午 2:32 2019/4/18 0018
     * @param
     * @return boolean  true插入成功  false插入失败
     */
    public boolean append() throws Exception {

        JSTableModelEvent event = new JSTableModelEvent(this);

        this.getTableModelListener().beforeAppend(event);
        if (event.isCancel() || !event.getResult()) {
            return false;
        }

        if (!this.onAppend()) {
            return false;
        }

        event.setCancel(false);
        event.setResult(true);
        event.setRow(this.getRowCount() - 1);
        this.getTableModelListener().afterAppend(event);

        return true;
    }

    /**
     * @Author YueLifeng
     * @Description //插入一行数据
     * @Date 下午 2:35 2019/4/18 0018
     * @param modelRow
     *          插入行的索引
     * @return boolean
     */
    public boolean insert(int modelRow) throws Exception {

        JSTableModelEvent event = new JSTableModelEvent(this);
        event.setRow(modelRow);

        this.getTableModelListener().beforeInsert(event);
        if (event.isCancel() || !event.getResult()) {
            return false;
        }

        event.setCancel(false);
        event.setResult(true);
        this.getTableModelListener().afterInsert(event);

        return true;
    }

    @Override
    public void tableChanged(TableModelEvent e) {

    }
}
