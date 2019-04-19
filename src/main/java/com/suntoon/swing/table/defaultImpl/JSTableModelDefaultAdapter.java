package com.suntoon.swing.table.defaultImpl;

import com.suntoon.swing.table.JSTableModelEvent;
import com.suntoon.swing.table.JSTableModelListener;

import java.util.Collection;

/**
 * @ProjectionName desktop
 * @ClassName JSTableModelDefaultAdapter
 * @Description 默认的系统实现
 * @Author YueLifeng
 * @Date 2019/4/19 0019下午 4:09
 * @Version 1.0
 */
public class JSTableModelDefaultAdapter<E> implements JSTableModelListener<Collection<E>> {

    //带有构造器的tableModel
    public JSTableModelDefaultAdapter() {
        super();
    }

    @Override
    public void beforeRetrieve(JSTableModelEvent event) throws Exception {
        event.getJSTableModel().clear();
    }

    @Override
    public Collection<E> onRetrieve() throws Exception {
        return null;
    }

    @Override
    public void afterRetrieve(JSTableModelEvent event) throws Exception {

    }

    @Override
    public void beforeUpdate(JSTableModelEvent event) throws Exception {

    }

    @Override
    public boolean update(JSTableModelEvent event) throws Exception {
        return true;
    }

    @Override
    public void afterUpdate(JSTableModelEvent event) throws Exception {
        event.getJSTableModel().resetUpdate();
    }

    @Override
    public void beforeDelete(JSTableModelEvent event) throws Exception {

    }

    @Override
    public void afterDelete(JSTableModelEvent event) throws Exception {

    }

    @Override
    public void beforeAppend(JSTableModelEvent event) throws Exception {

    }

    @Override
    public void afterAppend(JSTableModelEvent event) throws Exception {

    }

    @Override
    public void beforeInsert(JSTableModelEvent event) throws Exception {

    }

    @Override
    public void afterInsert(JSTableModelEvent event) throws Exception {

    }
}
