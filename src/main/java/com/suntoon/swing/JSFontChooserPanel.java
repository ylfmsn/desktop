package com.suntoon.swing;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectionName desktop
 * @ClassName JSFontChooserPanel
 * @Description //字体属性设置对话框
 * @Author YueLifeng
 * @Date 2019/4/16 0016上午 10:27
 * @Version 1.0
 */
public class JSFontChooserPanel extends JPanel {

    private static final long serialVersionUID = -174157514297834539L;

    //当前的字体名称，默认宋体
    public static final String DEFAULT_FONT_NAME = "宋体";

    //当前的字样，默认常规
    public static int DEFAULT_FONT_STYLE = Font.PLAIN;

    //默认的字体大小，9号
    public static int DEFAULT_FONT_SIZE = 9;

    //字体样式列表
    private static final Map<Integer, String> FONT_STYLES = new HashMap<>();

    //字体样式中文名称
    private static final Map<Integer, String> FONT_STYLE_NAMES = new HashMap<>();

    //字号列表
    private static final Map<Integer, String> FONT_SIZES = new HashMap<>();

    //字体的名称类表
    private static final String[] FONT_SIZE_NAMES;

    //字体大小泪飚
    private static final Integer[] FONT_SIZE_VALUES;

    //静态构造
    static {
        FONT_STYLES.put(Font.PLAIN, "plain");
        FONT_STYLES.put(Font.BOLD, "bold");
        FONT_STYLES.put(Font.ITALIC, "italic");
        FONT_STYLES.put(Font.ITALIC | Font.BOLD, "bolditalic");

        FONT_STYLE_NAMES.put(Font.PLAIN, "常规");
        FONT_STYLE_NAMES.put(Font.BOLD, "粗体");
        FONT_STYLE_NAMES.put(Font.ITALIC, "斜体");
        FONT_STYLE_NAMES.put(Font.ITALIC | Font.BOLD, "粗斜体");

        FONT_SIZE_NAMES = new String[] { "8", "9", "10", "11", "12", "14", "16", "18", "20", "22", "24", "26", "28",
                "36", "48", "72", "初号", "小初", "一号", "小一", "二号", "小二", "三号", "小三", "四号", "小四", "五号", "小五", "六号", "小六",
                "七号", "八号" };

        FONT_SIZE_VALUES = new Integer[] { 8, 9, 10, 11, 12, 14, 16, 18, 20, 22, 24, 26, 28, 36, 48, 72, 42, 36, 26, 24,
                22, 18, 16, 15, 14, 12, 11, 9, 8, 7, 6, 5 };

        for (int i = 0; i < FONT_SIZE_NAMES.length; i++ ) {
            FONT_SIZES.put(FONT_SIZE_VALUES[i], FONT_SIZE_NAMES[i]);
        }
    }

    //属性
    //字体名称
    private String fontName;

    //字体样式
    private int fontStyle;

    //字体大小
    private int fontSize;

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public int getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(int fontStyle) {
        this.fontStyle = fontStyle;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }


    //控件
    private JLabel lblFont;    //选择字体的LBL（LBL <--> lable）
    private JLabel lblStyle;     //选择字型的LBL
    private JLabel lblSize;     //选择字体大小的LBL
    private JTextField txtFont;     //显示选择字体的TEXT
    private JTextField txtStyle;     //显示选择字型的TEXT
    private JTextField txtSize;     //显示选择字体的TEXT
    private JTextField showTF;     //展示框（输入框）
    private JList<String> lstFont;     //选择字体的列表
    private JList<String> lstStyle;     //选择字型的列表
    private JList<String> lstSize;     //选择字体大小的列表
    private JScrollPane spFont;
    private JScrollPane spSize;
    private JPanel showPan;     //显示框

    //带有字体的构造函数
    public JSFontChooserPanel(Font font) {
        init(font);
    }

    //使用默认字体创建
    public JSFontChooserPanel() {
        this(FlyWeightFonts.getInstance().getFont(DEFAULT_FONT_NAME, DEFAULT_FONT_STYLE, DEFAULT_FONT_SIZE));
    }

    //获取选中字体
    public Font getSelectFont() {
        return FlyWeightFonts.getInstance().getFont(fontName, fontStyle, fontSize);
    }

    //获取编码后的字符串
    public String getFontDecode() {
        return fontName + "-" + FONT_STYLES.get(fontStyle) + "-" + fontSize;
    }

    /**
     * @Author YueLifeng
     * @Description //初始化控件显示
     * @Date 上午 10:55 2019/4/16 0016
     * @param font
     * @return void
     */
    private void init(Font font) {

        //实例化变量
        lblFont = new JLabel("字体：");
        lblStyle = new JLabel("字型：");
        lblSize = new JLabel("大小：");
        txtFont = new JTextField(DEFAULT_FONT_NAME);
        txtStyle = new JTextField(FONT_STYLE_NAMES.get(DEFAULT_FONT_STYLE));
        txtSize = new JTextField(DEFAULT_FONT_SIZE);

        //取得当前环境可用字体
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontNames = ge.getAvailableFontFamilyNames();

        //可用字体列表
        lstFont = new JList<>(fontNames);

        //字型
        lstStyle = new JList<>(FONT_STYLE_NAMES.values().toArray(new String[FONT_STYLE_NAMES.size()]));

        //字号
        lstSize = new JList<>(FONT_SIZE_NAMES);
        spFont = new JScrollPane(lstFont);
        spSize = new JScrollPane(lstSize);

        showPan = new JPanel();
        showPan.setBackground(Color.WHITE);

        //布局控件
        //字体框
        this.setLayout(null);    //不用布局管理器
        this.add(lblFont);
        lblFont.setBounds(12, 10, 30, 20);    //移动和调整大小，相对左上角x=12， y=10， 大小 width=30， height=20
        txtFont.setEditable(false);
        this.add(txtFont);
        txtFont.setBounds(12, 30, 155, 20);

        this.add(spFont);
        spFont.setBounds(12, 50, 155, 80);

        //样式
        add(lblStyle);
        lblStyle.setBounds(175, 10, 30, 20);
        txtStyle.setEditable(false);
        this.add(txtStyle);
        txtStyle.setBounds(177,30, 130, 20);
        lstStyle.setBorder(BorderFactory.createLineBorder(Color.gray));
        this.add(lstStyle);
        lstStyle.setBounds(177, 50, 130, 80);

        //大小
        add(lblSize);
        lblSize.setBounds(320, 10, 30, 20);
        txtSize.setEditable(false);
        this.add(txtSize);
        txtSize.setBounds(320, 30, 60, 20);
        this.add(spSize);
        spSize.setBounds(320, 50, 60, 80);

        //展示框
        showTF = new JTextField();
        showTF.setBounds(10, 10, 300, 50);
        showTF.setHorizontalAlignment(JTextField.CENTER);
        showTF.setText("字体示例：AaBbYyZz");
        showTF.setBackground(Color.white);
        showTF.setEditable(false);
        showPan.setBorder(BorderFactory.createTitledBorder("示例"));
        add(showPan);
        showPan.setBounds(12, 140, 368, 80);
        showPan.setLayout(new BorderLayout());
        showPan.add(showTF);

        //设置字体
        this.setSelectFont(font);

        //字体下拉列表框选中事件
        lstFont.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                fontName = lstFont.getSelectedValue();
                txtFont.setText(fontName);
                showTF.setFont(FlyWeightFonts.getInstance().getFont(fontName, fontStyle, fontSize));
            }
        });

        //用字字形选择
        lstStyle.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String value = (String) ((JList<String>) e.getSource()).getSelectedValue();

                if (value.equals("常规")) {
                    fontStyle = Font.PLAIN;
                }

                if (value.equals("斜体")) {
                    fontStyle = Font.ITALIC;
                }

                if (value.equals("粗体")) {
                    fontStyle = Font.BOLD;
                }

                if (value.equals("粗斜体")) {
                    fontStyle = Font.BOLD | Font.ITALIC;
                }

                txtStyle.setText(value);
                showTF.setFont(FlyWeightFonts.getInstance().getFont(fontName, fontStyle, fontSize));
            }
        });

        //字号选择
        lstSize.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                fontSize = FONT_SIZE_VALUES[lstSize.getSelectedIndex()];
                txtSize.setText(String.valueOf(fontSize));
                showTF.setFont(FlyWeightFonts.getInstance().getFont(fontName, fontStyle, fontSize));
            }
        });
    }

    /**
     * @Author YueLifeng
     * @Description //设置字体属性的操作
     * @Date 上午 11:20 2019/4/16 0016
     * @param font
     * @return void
     */
    public void setSelectFont(Font font) {

        if (font == null) {
            font = FlyWeightFonts.getInstance().getFont(DEFAULT_FONT_NAME, DEFAULT_FONT_STYLE, DEFAULT_FONT_SIZE);
        }

        //发现一个大bug，如果font.decode的方法获取的字体没有内容的话，他就会把name设置为空
        //但是font还是存在的，所以这里判断一下
        if (font.getName() == null || font.getName().length() <= 0) {
            font = FlyWeightFonts.getInstance().getFont(DEFAULT_FONT_NAME, DEFAULT_FONT_STYLE, DEFAULT_FONT_SIZE);
        }

        showTF.setFont(font);
        lstStyle.setSelectedIndex(font.getStyle());
        txtStyle.setText(FONT_STYLE_NAMES.get(font.getStyle()));
        lstSize.setSelectedValue(Integer.toString(font.getSize()), true);
        txtSize.setText(Integer.toString(font.getSize()));
        lstFont.setSelectedValue(font.getName(), true);
        txtFont.setText(font.getName());
        lstFont.setSelectedValue(font.getName(), true);

        this.fontName = font.getName();
        this.fontStyle = font.getStyle();
        this.fontSize = font.getSize();
    }



    /**
     * @Author YueLifeng
     * @Description //字体缓存
     * @Date 上午 11:23 2019/4/16 0016

     * @return
     */
    protected static class FlyWeightFonts {

        //存储字体
        private Map<String, Font> font = new HashMap<>();

        private static final FlyWeightFonts instance = new FlyWeightFonts();

        public static FlyWeightFonts getInstance() {
            return instance;
        }

        /**
         * @Author YueLifeng
         * @Description //根据字体名称 类型 大小从像元里面获取字体实例 如果没有 则新建实例
         * @Date 上午 11:26 2019/4/16 0016
         * @param fontName     名称
         * @param fontStyle     类型
         * @param fontSize     大小
         * @return java.awt.Font
         */
        public Font getFont(String fontName, int fontStyle, int fontSize) {

            Font result = null;
            String key = (fontName + "-" + FONT_STYLES.get(fontStyle) + "-" + fontSize).toString();
            if (font.containsKey(key)) {
                result = font.get(key);
            } else {
                result = new Font(fontName, fontStyle, fontSize);
                font.put(key, result);
            }
            return result;
        }
    }









}
