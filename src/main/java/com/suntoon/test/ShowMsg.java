package com.suntoon.test;

/**
 * @ProjectionName desktop
 * @ClassName ShowMsg
 * @Description TODO
 * @Author YueLifeng
 * @Date 2019/4/29 0029上午 11:22
 * @Version 1.0
 */
import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;

/**
 * 一个简单的拖放示例程序 将文件或文件夹拖放到文本区然后放下,就会显示这些文件的一些信息
 *
 * @author
 * @version
 */
public class ShowMsg {

    public static void main(String[] args) {
        new DropTargetFrame();
    }
}

class DropTargetFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 300;

    public DropTargetFrame() {
        super("文件信息");
        JTextArea textArea = new JTextArea("@@@@@@@@@@将文件拖放到编辑区@@@@@@@@@@@@\n");
        JScrollPane scrollPane =new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        add(scrollPane);
        new DropTarget(textArea, new TextDropTargetListener(textArea));
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}

class TextDropTargetListener extends DropTargetAdapter {
    private JTextArea textArea;

    public TextDropTargetListener(JTextArea aTextArea) {
        textArea = aTextArea;
    }

    public void drop(DropTargetDropEvent event) {
        event.acceptDrop(DnDConstants.ACTION_COPY);
        Transferable transferable = event.getTransferable();
        DataFlavor[] flavors = transferable.getTransferDataFlavors();
        for (int i = 0; i < flavors.length; i++) {
            DataFlavor d = flavors[i];
            try {
                if (d.equals(DataFlavor.javaFileListFlavor)) {
                    @SuppressWarnings("unchecked")
                    java.util.List<File> fileList = (java.util.List<File>) transferable.getTransferData(d);
                    for (File f : fileList) {
                        textArea.append("文件名: "+f.getName() + "\n");
                        textArea.append("文件属性: ");
                        String value=null;
                        if(f.isFile())
                            value ="文件, 文件大小: "+f.length()+"bytes";
                        else if(f.isDirectory())
                            value ="文件夹";
                        else
                            value ="未知";
                        textArea.append(value+"\n");
                    }
                }
            }
            catch (Exception e) {
                textArea.append(e + "\n");
            }
        }
        textArea.append("\n");
        event.dropComplete(true);
    }
}
