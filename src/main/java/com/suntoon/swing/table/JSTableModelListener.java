package com.suntoon.swing.table;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.EventListener;

/**
 * @ProjectionName desktop
 * @InterfaceName JSTableModelListener
 * @Description tablemodle操作的事件对象
 * @Author ylf
 * @Date 2019/4/18 0018上午 9:19
 * @Version 1.0
 */
public interface JSTableModelListener<E> extends EventListener {

    /**
     * @Author YueLifeng
     * @Description //在加载数据之前执行的操作
     * @Date 上午 9:34 2019/4/18 0018
     * @param event
     *          事件对象
     * @throws Exception
     *          抛出所有异常
     * @return void
     */
    public void beforeRetrieve(JSTableModelEvent event) throws Exception;

    /**
     * @Author YueLifeng
     * @Description //加载数据的操作
     * @Date 上午 10:07 2019/4/18 0018
     * @param
     * @return E 加载完成后的数据
     */
    public E onRetrieve() throws Exception;

    /**
     * @Author YueLifeng
     * @Description //加载完成后执行的操作
     * @Date 上午 10:09 2019/4/18 0018
     * @param event
     *          事件对象
     * @return void
     */
    public void afterRetrieve(JSTableModelEvent event) throws Exception;

    /**
     * @Author YueLifeng
     * @Description //在更新前执行的操作，比如收集要处理的数据等
     * @Date 上午 10:10 2019/4/18 0018
     * @param event
     *          事件对象
     * @return void
     */
    public void beforeUpdate(JSTableModelEvent event) throws Exception;

    /**
     * @Author YueLifeng
     * @Description //update时候执行的操作
     * @Date 上午 10:10 2019/4/18 0018
     * @param event
     *          事件对象
     * @return boolean true成功 false失败
     */
    public boolean update(JSTableModelEvent event) throws Exception;

    /**
     * @Author YueLifeng
     * @Description //update成功之后的操作
     * @Date 上午 10:12 2019/4/18 0018
     * @param event
     *          事件对象
     * @return void
     */
    public void afterUpdate(JSTableModelEvent event) throws Exception;

    /**
     * @Author YueLifeng
     * @Description //在删除执行时的操作
     * @Date 上午 10:13 2019/4/18 0018
     * @param event
     *          事件对象
     * @return void
     */
    public void beforeDelete(JSTableModelEvent event) throws Exception;

    /**
     * @Author YueLifeng
     * @Description //删除成功后的操作
     * @Date 上午 10:14 2019/4/18 0018
     * @param event
     *          事件对象
     * @return void
     */
    public void afterDelete(JSTableModelEvent event) throws Exception;

    /**
     * @Author YueLifeng
     * @Description //在追加数据行前执行的操作
     * @Date 上午 10:15 2019/4/18 0018
     * @param event
     *          事件对象
     * @return void
     */
    public void beforeAppend(JSTableModelEvent event) throws Exception;

    /**
     * @Author YueLifeng
     * @Description //追加数据行后执行的操作
     * @Date 上午 10:15 2019/4/18 0018
     * @param event
     *          事件对象
     * @return void
     */
    public void afterAppend(JSTableModelEvent event) throws Exception;

    /**
     * @Author YueLifeng
     * @Description //插入数据前执行的操作
     * @Date 上午 10:17 2019/4/18 0018
     * @param event
     *          事件对象
     * @return void
     */
    public void beforeInsert(JSTableModelEvent event) throws Exception;

    /**
     * @Author YueLifeng
     * @Description //插入数据成功后执行的操作
     * @Date 上午 10:17 2019/4/18 0018
     * @param event
     *          事件对象
     * @return void
     */
    public void afterInsert(JSTableModelEvent event) throws Exception;

    /**
     * @Author YueLifeng
     * @Description //转换数据的方法
     * @Date 上午 10:19 2019/4/18 0018
     * @param col     表列
     * @param value  数值
     * @param targetCls   目标列
     * @return java.lang.Object
     */
    public default Object getDataTranstor(JSTableColumn col, Object value, Class<?> targetCls) throws ParseException {

        if (value == null)
            return null;

        if (targetCls == null)
            return value;

        if (targetCls.equals(Integer.class)) {
            if (value.toString().trim().length() <= 0) {
                return col.getCanBeNull() ? null : 0;
            } else if (value.equals("true") || value.equals("false")) {
                return value.equals("true") ? 1 : 0;
            } else if (value instanceof Boolean) {
                return value.equals(true) ? 1 : 0;
            } else {
                return Integer.parseInt(value.toString());
            }
        } else if (targetCls.equals(String.class)) {
            return value.toString();
        } else if (targetCls.equals(Double.class)) {
            if (value.toString().trim().length() <= 0) {
                return col.getCanBeNull() ? null : 0d;
            } else {
                return Double.parseDouble(value.toString());
            }
        } else if (targetCls.equals(Float.class)) {
            if (value.toString().trim().length() <= 0) {
                return col.getCanBeNull() ? null : 0f;
            } else {
                return Float.parseFloat(value.toString());
            }
        } else if (targetCls.equals(Long.class)) {
            if (value.toString().trim().length() <= 0) {
                return col.getCanBeNull() ? null : 0l;
            } else {
                if (value.toString().equals("true"))
                    return 1L;
                else if (value.toString().equals("false"))
                    return 0L;
                else
                    return Long.parseLong(value.toString());
            }
        } else if (targetCls.equals(Character.class)) {
            if (value.toString().trim().length() <= 0) {
                return col.getCanBeNull() ? null : ' ';
            } else {
                return value.toString().toCharArray()[0];
            }
        } else if (targetCls.equals(Boolean.class)) {
            if (value.toString().trim().length() <= 0) {
                return false;
            } else {
                return Boolean.parseBoolean(value.toString());
            }
        } else if (targetCls.equals(Short.class)) {
            if (value.toString().trim().length() <= 0) {
                return col.getCanBeNull() ? null : (short) 0;
            } else {
                return Short.parseShort(value.toString());
            }
        } else if (targetCls.equals(Date.class)) {
            if (value instanceof Date) {
                return value;
            } else if (value.toString().trim().equals("") || value.equals("0000-00-00") || value.equals("0000/00/00")
                    || value.equals("0000-00-00 00:00:00") || value.equals("0000-00-00-00-00-00")
                    || value.equals("0000/00/00 00:00:00")) {
                return null;
            } else {
                return col.getFormator().parseObject(value.toString());
            }
        } else if (targetCls.equals(BigDecimal.class)) {
            return new BigDecimal(value.toString());
        } else {
            return value;
        }
    }
}
