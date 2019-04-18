package com.suntoon.swing;

import javax.swing.*;

/**
 * @ProjectionName desktop
 * @ClassName JSTextField
 * @Description 高级的文本输入框
 * @Author YueLifeng
 * @Date 2019/4/16 0016下午 3:55
 * @Version 1.0
 */
public class JSTextField extends JTextField {

    private static final long serialVersionUID = -7107438243257321277L;

    //原始值
    private String original = "";

    //后台绑定值
    private String tag = "";

    public JSTextField(String text) {
        super(text);
        this.original = text;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
