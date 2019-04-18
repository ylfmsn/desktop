package com.suntoon.swing.table.action;

import java.io.File;
import java.util.EventListener;

/**
 * @ProjectionName desktop
 * @InterfaceName JSFileImportActionListener
 * @Description 文件调用的action
 * @Author ylf
 * @Date 2019/4/18 0018下午 3:53
 * @Version 1.0
 */
public interface JSFileImportActionListener extends EventListener {

    /**
     * @Author YueLifeng
     * @Description //打开文件完成后传递出文件的回调方法
     * @Date 下午 3:54 2019/4/18 0018
     * @param file
     * @return void
     */
    public void afterOpen(File file) throws Exception;

    /**
     * @Author YueLifeng
     * @Description //导入成功后的操作
     * @Date 下午 3:55 2019/4/18 0018
     * @param
     * @return void
     */
    public void afterDone() throws Exception;
}
