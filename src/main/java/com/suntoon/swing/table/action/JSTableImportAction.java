package com.suntoon.swing.table.action;

import com.suntoon.swing.JSFileDataStoreChooser;
import com.suntoon.swing.resource.ResourceLoader;
import org.jdesktop.swingx.JXTable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * @ProjectionName desktop
 * @ClassName JSTableImportAction
 * @Description 导入数据的操作
 * @Author YueLifeng
 * @Date 2019/4/18 0018下午 3:49
 * @Version 1.0
 */
public class JSTableImportAction extends JSTableBaseAction {

    private static final long serialVersionUID = -3068593608658058519L;

    public JSTableImportAction(JXTable table) {
        super(table);
        this.putValue(AbstractAction.NAME, "导入");
        this.putValue(AbstractAction.SMALL_ICON,
                new ImageIcon(ResourceLoader.getResource(ResourceLoader.IMAGE_IMPORT)));
        this.putValue(SHORT_DESCRIPTION, "导入数据");
    }

    //导入操作事件
    public List<JSFileImportActionListener> importListener = new LinkedList<>();

    /**
     * @Author YueLifeng
     * @Description //新增加一个事件操作对象
     * @Date 下午 3:58 2019/4/18 0018
     * @param l
     * @return void
     */
    public synchronized void addFileImportActionListener(JSFileImportActionListener l) {
        if (!importListener.contains(l))
            importListener.add(l);
    }

    /**
     * @Author YueLifeng
     * @Description //移除一个事件操作对象
     * @Date 下午 3:59 2019/4/18 0018
     * @param l
     * @return void
     */
    public synchronized void removeFileImportActionListener(JSFileImportActionListener l) {
        if (importListener.contains(l))
            importListener.remove(l);
    }

    /**
     * @Author YueLifeng
     * @Description //导入数据的操作
     * @Date 下午 4:00 2019/4/18 0018
     * @param e
     * @return void
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            File file = JSFileDataStoreChooser.showOpenFile("csv", null);
            if (file != null && file.exists()) {

                SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        for (JSFileImportActionListener l : importListener) {
                            l.afterOpen(file);
                        }
                        return null;
                    }

                    @Override
                    protected void done() {
                        try {
                            for (JSFileImportActionListener l : importListener) {
                                l.afterDone();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {

                        }
                    }
                };

                worker.execute();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
