package com.suntoon.swing.utils;

import java.awt.*;

/**
 * @ProjectionName desktop
 * @ClassName FontUtil
 * @Description 系统默认的字体
 * @Author YueLifeng
 * @Date 2019/4/16 0016下午 3:51
 * @Version 1.0
 */
public class FontUtil {

    //系统默认字体
    public static final Font DEFAULT_FONT = new Font("宋体", Font.PLAIN, 11);

    //获取系统默认的字体
    public static Font getDefaultFont() {
        return DEFAULT_FONT;
    }
}
