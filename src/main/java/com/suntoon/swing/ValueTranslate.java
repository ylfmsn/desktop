package com.suntoon.swing;

/**
 * @ProjectionName desktop
 * @InterfaceName ValueTranslate
 * @Description 值转换对象
 *              主要是8种类型 + string + font + color + Date + LocalTime + LocalDate + LocalDateTime
 * @Author ylf
 * @Date 2019/4/17 0017下午 3:24
 * @Version 1.0
 */
public interface ValueTranslate {
    /**
     * 获取字符串
     * @param value
     * @return
     */
    public default String getString(Object value){

        if (null == value)
            return null;

        return value.toString();
    }

    /**
     * 获取boolean类型
     * @param value
     * @return
     */
    public default Boolean getBoolean(Object value){
        return null;
    }
}
