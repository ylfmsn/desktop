package com.suntoon.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * @ProjectionName desktop
 * @InterfaceName FileImportActionListener
 * @Description TODO
 * @Author ylf
 * @Date 2019/4/15 0015下午 3:01
 * @Version 1.0
 */
public interface FileImportActionListener extends ActionListener {

    /**
     * action发生时调用此方法
     */
    public default void actionPerformed(ActionEvent e) {

        try {
            File file = JSFileDataStoreChooser.showOpenFile("csv", null);
            if (file != null && file.exists()) {
                afterOpen(file);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /**
     * 打开文件完成后传递出文件的回调方法
     *
     * @param file
     * @throws Exception
     */
    public void afterOpen(File file) throws Exception;
}
