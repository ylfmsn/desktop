package com.suntoon.swing;

import java.util.LinkedList;
import java.util.List;

/**
 * @ProjectionName desktop
 * @ClassName JSFolderChooser
 * @Description //拓展的文件夹选择框
 * @Author YueLifeng
 * @Date 2019/4/16 0016上午 10:23
 * @Version 1.0
 */
public class JSFolderChooser {

    //文件后缀
    private String suffix = "";

    //打开后的文件列表
    private List<String> files = new LinkedList<>();

    //过滤后文件后缀
    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public List<String> getFiles() {
        return files;
    }
}
