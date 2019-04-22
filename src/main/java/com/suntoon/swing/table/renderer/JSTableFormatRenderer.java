package com.suntoon.swing.table.renderer;

import javax.swing.table.DefaultTableCellRenderer;
import java.text.Format;

/**
 * @ProjectionName desktop
 * @ClassName JSTableFormatRenderer
 * @Description 带有掩码格式的renderer对象
 * @Author YueLifeng
 * @Date 2019/4/22 0022上午 10:36
 * @Version 1.0
 */
public class JSTableFormatRenderer extends DefaultTableCellRenderer {

    private static final long serialVersionUID = -3981456436820431417L;

    //当前掩码的格式对象
    private Format format;

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    //空值显示文本
    private String nullText;

    public String getNullText() {
        return nullText;
    }

    public void setNullText(String nullText) {
        this.nullText = nullText;
    }

    /**
     * @Author YueLifeng
     * @Description //带参数的构造函数
     * @Date 上午 10:46 2019/4/22 0022
     * @param format
     *          掩码格式对象
     * @param nullText
     *          空值显示文本
     * @return
     */
    public JSTableFormatRenderer(Format format, String nullText) {
        super();
        this.setFormat(format);
        this.setNullText(nullText);
    }

    /**
     * @Author YueLifeng
     * @Description //带有掩码格式的构造函数
     * @Date 上午 10:47 2019/4/22 0022
     * @param format
     * @return
     */
    public JSTableFormatRenderer(Format format) {
        this(format, "");
    }

    //重写绘制方法
    @Override
    protected void setValue(Object value) {
        try {
            setText((null == value) ? (nullText == null ? "" : nullText) : format.format(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            setText("");
        }
    }
}
